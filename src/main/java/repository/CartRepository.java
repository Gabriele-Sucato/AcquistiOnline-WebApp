package main.java.repository;

import java.util.List;

import main.java.model.Article;

public interface CartRepository {

    public void addItem (Article article, int quantity);
    public void addItem (Article article, int quantity, int id);
    public void removeItem (Article article);
    public void updateQuantity (Article article, int newQuantity);
    public void clearCart();
    public void listArticle(List<Article> articles);
    
}
