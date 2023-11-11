package main.java.repository.interfaces;

import java.util.List;

import main.java.model.Article;

public interface ArticleRepository {

        List<Article> getAllArticles();

        Article findArticleByCode(String code);

        void saveArticle(Article article);

        void updateArticle(Article article);

}
