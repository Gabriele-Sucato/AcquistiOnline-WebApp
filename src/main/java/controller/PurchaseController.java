package main.java.controller;

import java.util.List;

import main.java.model.Article;
import main.java.model.Cart;
import main.java.model.Purchase;
import main.java.repository.interfaces.ArticleRepository;
import main.java.repository.interfaces.CartRepository;
import main.java.repository.interfaces.PurchaseRepository;

public class PurchaseController {
    private final ArticleRepository articleRepository;
    private final CartRepository cartRepository;
    private final PurchaseRepository purchaseRepository;

    public PurchaseController(ArticleRepository articleRepository, CartRepository cartRepository,
            PurchaseRepository purchaseRepository) {
        this.articleRepository = articleRepository;
        this.cartRepository = cartRepository;
        this.purchaseRepository = purchaseRepository;
    }

    public void processPurchase(String clientCode, String paymentType, List<Article> articles) {
        Cart cart = cartRepository.findCartByClientCode(clientCode);
        if (cart == null || cart.getArticles().isEmpty()) {
            System.out.println("The client's cart is empty!");
            return;
        }

        articles = cart.getArticles();
        for (Article article : articles) {
            Article storedArticle = articleRepository.findArticleByCode(article.getCodeArticle());
            if (storedArticle == null || storedArticle.getAvailableQty() < article.getAvailableQty()) {
                System.out.println("This article " + article.getArticleName() + " is not available");
                return;
            }
        }

        double totalAmount = calculateTotalAmount(articles);

        Purchase purchase = new Purchase();
        purchase.setClientCode(clientCode);
        purchase.setPaymentType(paymentType);
        purchase.setPurchaseQty(articles.size());

        purchaseRepository.savePurchase(purchase);

        updateStockQuantity(articles);

        cartRepository.clearCart(clientCode);

        System.out.println("Congratulations!!! Purchase completed! Total: " + totalAmount);
    }

    private double calculateTotalAmount(List<Article> articles) {
        double totalAmount = 0;

        for (Article article : articles) {
            totalAmount += article.getPrice() * article.getAvailableQty();
        }
        return totalAmount;
    }

    private void updateStockQuantity(List<Article> articles) {
        for (Article article : articles) {
            Article storedArticle = articleRepository.findArticleByCode(article.getCodeArticle());
            if (storedArticle != null) {
                storedArticle.setAvailableQty(storedArticle.getAvailableQty() - article.getAvailableQty());
                articleRepository.updateArticle(storedArticle);
            }
        }
    }
}
