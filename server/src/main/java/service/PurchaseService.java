package main.java.service;

import java.util.List;

import main.java.model.Article;
import main.java.model.Cart;
import main.java.model.Purchase;
import main.java.repository.interfaces.ArticleRepository;
import main.java.repository.interfaces.CartRepository;
import main.java.repository.interfaces.PurchaseRepository;

public class PurchaseService {
    private final CartRepository cartRepository;
    private final ArticleRepository articleRepository;
    private final PurchaseRepository purchaseRepository;

    public PurchaseService(CartRepository cartRepository, ArticleRepository articleRepository,
            PurchaseRepository purchaseRepository) {
        this.cartRepository = cartRepository;
        this.articleRepository = articleRepository;
        this.purchaseRepository = purchaseRepository;
    }

    public void addToCart(String clientCode, String articleCode, int quantity) {
        Article article = articleRepository.findArticleByCode(articleCode);
        if (article == null || article.getAvailableQty() < quantity) {
            throw new IllegalArgumentException("This article is out of stock or is missing!");
        }

        Cart cart = cartRepository.findCartByClientCode(clientCode);
        if (cart == null) {
            cart = new Cart();
            cart.setCodeClient(clientCode);
            cartRepository.saveCart(cart);
        }
        cart.addToCart(article, quantity);
        cartRepository.updateCart(cart);
    }

    public void checkout(String clientCode) {
        Cart cart = cartRepository.findCartByClientCode(clientCode);
        if (cart == null || cart.getArticles().isEmpty()) {
            throw new IllegalArgumentException("Cart is empty!");
        }

        List<Article> articlesInCart = cart.getArticles();
        for (Article article : articlesInCart) {
            Purchase purchase = new Purchase(clientCode, article.getCodeArticle(), article.getPrice(),
                    article.getAvailableQty());
            purchaseRepository.savePurchase(purchase);

            // Update article available quantity in warehouse
            article.setAvailableQty(article.getAvailableQty() - article.getAvailableQty());
            articleRepository.updateArticle(article);
        }

        cart.clearCart();
        cartRepository.updateCart(cart);
    }
}