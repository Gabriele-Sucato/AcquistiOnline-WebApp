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

    public void addToCart(String clientCode, String articleCode, String paymentType, int quantity, double unitPrice) {
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

        Purchase purchase = new Purchase(clientCode, articleCode, paymentType, unitPrice, quantity);
        purchaseRepository.savePurchase(purchase);

        // Update article available quantity in warehouse
        article.setAvailableQty(article.getAvailableQty() - quantity);
        articleRepository.updateArticle(article);

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

    public double calculateTotalAmount(List<Article> articles) {
        double totalAmount = 0;

        for (Article article : articles) {
            totalAmount += article.getPrice() * article.getAvailableQty();
        }
        return totalAmount;
    }

    public void updateStockQuantity(List<Article> articles) {
        for (Article article : articles) {
            Article storedArticle = articleRepository.findArticleByCode(article.getCodeArticle());
            if (storedArticle != null) {
                storedArticle.setAvailableQty(storedArticle.getAvailableQty() - article.getAvailableQty());
                articleRepository.updateArticle(storedArticle);
            }
        }
    }

    public Cart getCartDetails(String clientCode) {
        return cartRepository.findCartByClientCode(clientCode);
    }

    public List<Purchase> getAllPurchases(String clientCode) {
        return purchaseRepository.getPurchasesByClientCode(clientCode);
    }

    public void processPurchase(String clientCode, String paymentType, List<String> articleCodes) {
        Cart cart = cartRepository.findCartByClientCode(clientCode);
        if (cart == null || cart.getArticles().isEmpty()) {
            throw new IllegalArgumentException("Cart is empty!");
        }

        // Calculate the total amount of the purchase
        double totalAmount = calculateTotalAmount(cart.getArticles());

        System.out.println("Processing purchase for client: " + clientCode);
        System.out.println("Payment type: " + paymentType);
        System.out.println("Total amount: " + totalAmount);

        // Clear the cart after purchase
        cart.clearCart();
        cartRepository.updateCart(cart);
    }
}
