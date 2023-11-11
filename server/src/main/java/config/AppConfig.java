package main.java.config;

import main.java.repository.interfaces.ArticleRepository;
import main.java.repository.interfaces.CartRepository;
import main.java.repository.interfaces.ClientRepository;
import main.java.repository.interfaces.PurchaseRepository;
import main.java.repository.interfaces.WarehouseRepository;
import main.java.repository.repoClasses.ArticleRepoClass;
import main.java.repository.repoClasses.CartRepoClass;
import main.java.repository.repoClasses.ClientRepoClass;
import main.java.repository.repoClasses.PurchaseRepoClass;
import main.java.repository.repoClasses.WarehouseRepoClass;
import main.java.controller.PurchaseController;
import main.java.service.PurchaseService;

public class AppConfig {

    public ArticleRepository getArticleRepository() {
        return new ArticleRepoClass();
    }

    public CartRepository getCartRepository() {
        return new CartRepoClass();
    }

    public ClientRepository getClientRepository() {
        return new ClientRepoClass();
    }

    public PurchaseRepository getPurchaseRepository() {
        return new PurchaseRepoClass();
    }

    public WarehouseRepository getWarehouseRepository() {
        return new WarehouseRepoClass();
    }

    public PurchaseController purchaseController(
            ArticleRepository articleRepository,
            CartRepository cartRepository,
            PurchaseRepository purchaseRepository) {
        return new PurchaseController(articleRepository, cartRepository, purchaseRepository);
    }

    public PurchaseService purchaseService(
            CartRepository cartRepository,
            ArticleRepository articleRepository,
            PurchaseRepository purchaseRepository) {
        return new PurchaseService(cartRepository, articleRepository, purchaseRepository);
    }
}
