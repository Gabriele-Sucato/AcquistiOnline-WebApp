package main.java.controller;

import java.util.List;

import main.java.model.Article;
import main.java.model.Cart;
import main.java.model.Purchase;
import main.java.repository.ArticleRepository;
import main.java.repository.CartRepository;
import main.java.repository.PurchaseRepository;

public class PurchaseController {
    private ArticleRepository articleRepository;
    private CartRepository cartRepository;
    private PurchaseRepository purchaseRepository;

    public PurchaseController(ArticleRepository articleRepository, CartRepository cartRepository,
            PurchaseRepository purchaseRepository) {
        this.articleRepository = articleRepository;
        this.cartRepository = cartRepository;
        this.purchaseRepository = purchaseRepository;
    }

    public void processPurchase(String clientCode, List<Article> articles, String paymentType) {

        // verify if client got a cart
        Cart cart = cartRepository.findCartByClientCode(clientCode);
        if (cart == null) {
            System.out.println("The client's cart is empty!");
            return;
        }

        // verify the availability in warehouse
        for (Article article : articles) {
            Article storedArticle = articleRepository.findArticleByCode(article.getCodeArticle());
            if (storedArticle == null || storedArticle.getAvailableQty() < article.getQuantity()) {
                System.out.println("This article" + article.getNameArticle() + "is not available");
                return;
            }
        }

        // calculate total amount
        double totalAmount = calculateTotalAmount(cart.getArticles());

        // create and save the purchase
        Purchase purchase = new Purchase();
        purchase.setClientCode(clientCode);
        purchase.setPaymentType(paymentType);
        purchase.setPurchaseQty(cart.getArticles().size());
        purchaseRepository.savePurchase(purchase);

        // update available quantity in warehouse to purchased articles
        updateStockQuantity(articles);

        // clear the cart
        cartRepository.clearCart(clientCode);

        System.out.println("Congratualtions!!! Purchase completed! Total: ", totalAmount);
    }

    // private method that can calculate Total amount
    private double calculateTotalAmount(List<Article> articles) {
        double totalAmount = 0;

        for (Article article : articles) {
            totalAmount += article.getPrice() * article.getQuantity();
        }
        return totalAmount;
    }

    private void updateStockQuantity(List<Article> articles) {
        for (Article article : articles) {
            Article storedArticle = articleRepository.findArticleByCode(article.getcodeArticle());
            if (storedArticle != null) {
                storedArticle.setAvailableQty(storedArticle.getAvailableQty() - article.getQuantity());
                articleRepository.updateArticle(storedArticle);
            }
        }
    }
}
