package main.java.repository;

import java.util.List;

import main.java.model.Article;

public interface ArticleRepository

{

    Article findArticleByName(String name);

    List<Article> findAllArticles();

    void saveArticle(Article article);

    void updateArticle(Article article);

    void deleteArticle(String name);

}
