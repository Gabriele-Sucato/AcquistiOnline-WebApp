package main.java.repository;

import java.util.ArrayList;
import java.util.List;

import main.java.model.Article;

public class ArticleRepoClass implements ArticleRepository {

    private Map<String, Article> articles = new HashMap<>();

    @Override
    public Article findArticleByCode(String code) {
        return articles.get(code);
    }

    @Override
    public void saveArticle(Article article) {
        articles.put(article.getCodeArticle(), article);
    }

    @Override
    public void updateArticle(Article article) {
        // Aggiorna l'articolo nel repository, se necessario
        // (ad esempio, se si utilizza un database)
        // Non è necessario per l'implementazione in memoria con Map
    }
}