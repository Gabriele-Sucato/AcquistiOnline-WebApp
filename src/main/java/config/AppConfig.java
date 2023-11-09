package main.java.config;

import main.java.controller.PurchaseController;
import main.java.repository.ArticleRepoClass;
import main.java.repository.ArticleRepository;
import main.java.repository.CartRepoClass;
import main.java.repository.CartRepository;
import main.java.repository.ClientRepoClass;
import main.java.repository.ClientRepository;
import main.java.repository.PurchaseRepoClass;
import main.java.repository.PurchaseRepository;
import main.java.repository.WarehouseRepoClass;
import main.java.repository.WarehouseRepository;
import main.java.service.PurchaseService;

public class AppConfig {

    public ArticleRepository articleRepository() {
        return new ArticleRepoClass();
    }

    public CartRepository cartRepository() {
        return new CartRepoClass();
    }

    public ClientRepository clientRepository() {
        return new ClientRepoClass();
    }

    public PurchaseRepository purchaseRepository() {
        return new PurchaseRepoClass();
    }

    public WarehouseRepository warehouseRepository() {
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
