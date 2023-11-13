package main.java.controller;

import main.java.model.Article;
import main.java.model.Purchase;
import main.java.repository.interfaces.ArticleRepository;
import main.java.repository.interfaces.CartRepository;
import main.java.repository.interfaces.PurchaseRepository;
import main.java.service.PurchaseService;

import java.util.List;

public class PurchaseController {
    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    public PurchaseController(
            ArticleRepository articleRepository,
            CartRepository cartRepository,
            PurchaseRepository purchaseRepository) {
        this.purchaseService = new PurchaseService(cartRepository, articleRepository, purchaseRepository);
    }

    public void addToCart(String clientCode, String articleCode, String paymentType, int quantity, double unitPrice) {
        try {
            purchaseService.addToCart(clientCode, articleCode, paymentType, quantity, unitPrice);
            System.out.println("Article added to cart successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void checkout(String clientCode) {
        try {
            purchaseService.checkout(clientCode);
            System.out.println("Checkout successful!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void getCartDetails(String clientCode) {
        try {
            List<Article> cartDetails = purchaseService.getCartDetails(clientCode).getArticles();
            if (cartDetails == null || cartDetails.isEmpty()) {
                System.out.println("The client's cart is empty!");
            } else {
                System.out.println("Cart details:\n" + cartDetails);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void processPurchase(String clientCode, String paymentType, List<String> articleCodes) {
        try {
            purchaseService.processPurchase(clientCode, paymentType, articleCodes);
            System.out.println("Purchase completed successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void getAllPurchases(String clientCode) {
        try {
            List<Purchase> purchases = purchaseService.getAllPurchases(clientCode);
            if (purchases == null || purchases.isEmpty()) {
                System.out.println("The client has no purchases!");
            } else {
                System.out.println("All purchases:\n" + purchases);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
