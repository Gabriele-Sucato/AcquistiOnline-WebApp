package main.java.api.servlets;

import main.java.model.Cart;
import main.java.repository.interfaces.CartRepository;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/api/cart")
public class CartServlet extends HttpServlet {
    private final CartRepository cartRepository;

    public CartServlet(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    // Gestisce le richieste GET per il contenuto del carrello
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String clientCode = getClientCodeFromRequest(request);

            // Ottieni il carrello dal repository
            Cart cart = cartRepository.findCartByClientCode(clientCode);

            // Crea un oggetto JSON che rappresenta il contenuto del carrello
            JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
            if (cart != null) {
                cart.getArticles().forEach(article -> {
                    jsonArrayBuilder.add(Json.createObjectBuilder()
                            .add("codeArticle", article.getCodeArticle())
                            .add("name", article.getArticleName())
                            .add("quantity", cart.getQuantityByArticle(article))
                            .add("subtotal", cart.getSubtotalByArticle(article))
                            .build());
                });
            }

            // Imposta la risposta HTTP con il contenuto JSON
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            // Scrivi il JSON nella risposta
            try (PrintWriter out = response.getWriter()) {
                out.print(jsonArrayBuilder.build().toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore durante il recupero del carrello");
        }
    }

    private String getClientCodeFromRequest(HttpServletRequest request) {
        return request.getParameter("clientCode");
    }
}
