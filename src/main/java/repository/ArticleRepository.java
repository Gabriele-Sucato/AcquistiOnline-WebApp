package main.java.repository;

import java.util.List;

import main.java.model.Article;

public interface ArticleRepository{
 
        Article findArticleByCode(String code);
        void saveArticle(Article article);
        void updateArticle(Article article);
    

}
