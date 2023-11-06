package main.java.repository;

import java.util.ArrayList;
import java.util.List;

import main.java.model.Article;

public class ArticleRepoClass implements ArticleRepository{
    
    private List<Article> articles = new ArrayList<>();


    @Override
    public Article findArticleByName(String name){
        for (Article article : articles) {
            if(article.getNameArticle() == name ){
                return article;
            }
        }
        return null;
    }

}
