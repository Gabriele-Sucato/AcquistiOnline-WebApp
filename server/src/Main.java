
// Main.java
import java.util.List;
import java.util.stream.Collectors;

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
            // AppConfig init
            AppConfig appConfig = new AppConfig();

            // Repositories and service init
            ArticleRepository articleRepository = appConfig.getArticleRepository();
            CartRepository cartRepository = appConfig.getCartRepository();
            ClientRepository clientRepository = appConfig.getClientRepository();
            PurchaseRepository purchaseRepository = appConfig.getPurchaseRepository();
            WarehouseRepository warehouseRepository = appConfig.getWarehouseRepository();

            // Service and Controller init
            PurchaseController purchaseController = appConfig.purchaseController(
                    articleRepository,
                    cartRepository,
                    purchaseRepository);

            PurchaseService purchaseService = appConfig.purchaseService(
                    cartRepository,
                    articleRepository,
                    purchaseRepository);

            // PurchaseService usage
            String clientCode = "123";
            String articleCode = "ABC";
            int quantity = 2;

            List<Article> allArticles = articleRepository.getAllArticles();
            double unitPrice = 1.0;

            for (Article article : allArticles) {
                if (article.getCodeArticle().equals(articleCode)) {
                    unitPrice = article.getPrice();
                    break;
                }
            }

            String paymentType = "Credit Card";
            // addToCart
            purchaseService.addToCart(clientCode, articleCode, paymentType, quantity, unitPrice);

            // PurchaseController usage
            List<String> articleCodes = extractArticleCodes(allArticles);
            // Esegui l'acquisto
            purchaseController.processPurchase(clientCode, paymentType, articleCodes);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<String> extractArticleCodes(List<Article> articles) {
        // retrieve article codes from list of article
        return articles.stream()
                .map(Article::getCodeArticle)
                .collect(Collectors.toList());
    }

}
