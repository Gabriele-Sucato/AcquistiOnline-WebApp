import java.util.List;

import main.java.config.AppConfig;
import main.java.controller.PurchaseController;
import main.java.model.Article;
import main.java.repository.interfaces.ArticleRepository;
import main.java.repository.interfaces.CartRepository;
import main.java.repository.interfaces.ClientRepository;
import main.java.repository.interfaces.PurchaseRepository;
import main.java.repository.interfaces.WarehouseRepository;
import main.java.service.PurchaseService;

public class Main {

    public static void main(String[] args) {
        try {
            // Init AppConfig
            AppConfig appConfig = new AppConfig();

            // Initialize repositories and service
            ArticleRepository articleRepository = appConfig.getArticleRepository();
            CartRepository cartRepository = appConfig.getCartRepository();
            ClientRepository clientRepository = appConfig.getClientRepository();
            PurchaseRepository purchaseRepository = appConfig.getPurchaseRepository();
            WarehouseRepository warehouseRepository = appConfig.getWarehouseRepository();

            // Initialize controller and service
            PurchaseController purchaseController = appConfig.purchaseController(
                    articleRepository,
                    cartRepository,
                    purchaseRepository);

            PurchaseService purchaseService = appConfig.purchaseService(
                    cartRepository,
                    articleRepository,
                    purchaseRepository);

            // Usage of PurchaseService
            String clientCode = "123";
            String articleCode = "ABC";
            int quantity = 2;

            // Add To Cart
            purchaseService.addToCart(clientCode, articleCode, quantity);

            // Usage of PurchaseController
            List<Article> articles = articleRepository.getAllArticles();
            String paymentType = "Credit Card";

            // Do the purchase
            purchaseController.processPurchase(clientCode, paymentType, articles);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
