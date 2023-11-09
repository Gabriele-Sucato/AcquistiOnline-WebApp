package main.java.repository;

import main.java.model.Article;

public interface ArticleRepository {

        Article findArticleByCode(String code);

        void saveArticle(Article article);

        void updateArticle(Article article);

}
