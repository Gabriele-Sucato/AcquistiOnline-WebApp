package main.java.api.servlets;

import main.java.model.Article;
import main.java.repository.interfaces.ArticleRepository;

import javax.json.*;
import javax.json.stream.JsonCollectors;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/api/articles")
public class ArticleServlet extends HttpServlet {
    private final ArticleRepository articleRepository; // Assicurati di inizializzare questo repository

    // Costruttore che inizializza il repository
    public ArticleServlet(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    // Gestisce le richieste GET per l'elenco degli articoli
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            // Ottieni l'elenco degli articoli dal repository
            List<Article> articles = articleRepository.getAllArticles();

            // Crea un oggetto JSON che rappresenta l'elenco degli articoli
            JsonArray jsonArray = articles.stream()
                    .map(this::articleToJson)
                    .collect(JsonCollectors.toJsonArray());

            // Imposta la risposta HTTP con il contenuto JSON
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            // Scrivi il JSON nella risposta
            try (PrintWriter out = response.getWriter()) {
                out.print(jsonArray.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "Errore durante il recupero degli articoli");
        }
    }

    // Converte un oggetto Article in un oggetto JSON
    private JsonObject articleToJson(Article article) {
        return Json.createObjectBuilder()
                .add("codeArticle", article.getCodeArticle())
                .add("name", article.getArticleName())
                .add("price", article.getPrice())
                .add("availableQty", article.getAvailableQty())
                .build();
    }
}
