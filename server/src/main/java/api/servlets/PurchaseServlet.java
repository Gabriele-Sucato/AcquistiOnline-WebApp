package main.java.api.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.config.AppConfig;
import main.java.controller.PurchaseController;
import main.java.model.Article;
import main.java.repository.interfaces.ArticleRepository;
import main.java.repository.interfaces.CartRepository;
import main.java.repository.interfaces.PurchaseRepository;
import main.java.service.PurchaseService;

@WebServlet("/api/purchase")
public class PurchaseServlet extends HttpServlet {

    private final AppConfig appConfig = new AppConfig();
    private final ArticleRepository articleRepository = appConfig.getArticleRepository();
    private final CartRepository cartRepository = appConfig.getCartRepository();
    private final PurchaseRepository purchaseRepository = appConfig.getPurchaseRepository();
    private final PurchaseController purchaseController = appConfig.purchaseController(articleRepository,
            cartRepository, purchaseRepository);
    private final PurchaseService purchaseService = appConfig.purchaseService(cartRepository, articleRepository,
            purchaseRepository);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "addToCart":
                addToCart(request, response);
                break;
            case "processPurchase":
                processPurchase(request, response);
                break;
            default:
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("Action not supported!");
        }
    }

    private void addToCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String clientCode = request.getParameter("clientCode");
        String articleCode = request.getParameter("articleCode");
        String paymentType = request.getParameter("paymentType");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        double unitPrice = Double.parseDouble(request.getParameter("unitPrice"));

        try {
            purchaseService.addToCart(clientCode, articleCode, paymentType, quantity, unitPrice);
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.println("{ \"message\": \"Add article into cart succeeded!\" }");
        } catch (IllegalArgumentException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{ \"error\": \"" + e.getMessage() + "\" }");
        }
    }

    private void processPurchase(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String clientCode = request.getParameter("clientCode");
        String paymentType = request.getParameter("paymentType");
        String[] articleCodes = request.getParameterValues("articleCodes");

        try {
            if (articleCodes == null || articleCodes.length == 0) {
                throw new IllegalArgumentException("No articles provided for purchase");
            }

            List<String> articleCodesList = Arrays.asList(articleCodes);
            purchaseController.processPurchase(clientCode, paymentType, articleCodesList);

            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.println("{ \"message\": \"Purchase completed successfully\" }");
        } catch (IllegalArgumentException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{ \"error\": \"" + e.getMessage() + "\" }");
        }
    }
}
