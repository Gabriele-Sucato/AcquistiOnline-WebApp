package main.java.service;

import java.util.List;

import main.java.model.Article;
import main.java.model.Cart;
import main.java.repository.ArticleRepository;
import main.java.repository.CartRepository;

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

        // Check the availability of the item in the warehouse
        Article article = articleRepository.findArticleByCode(articleCode);
        if (article == null || article.getAvailableQty() < quantity) {
            throw new IllegalArgumentException("This article is out of stock or is missing!");
        }

        // Add the purchase to cart
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

        // Doing the purchase
        List<Article> articlesInCart = cart.getArticles();
        for (Article article : articlesInCart) {
            Purchase purchase = new Purchase(clientCode, article.getCodeArticle(), article.getPrice(),
                    article.getQuantity());
            purchaseRepository.savePurchase(purchase);

            // Update article available quantity in warehouse
            article.setAvailableQuantity(article.getAvailableQty() - article.getQuantity());
            articleRepository.updateArticle(article);
        }

        // Clear the cart
        cart.clearCart();
        cartRepository.updateCart();
    }
}
