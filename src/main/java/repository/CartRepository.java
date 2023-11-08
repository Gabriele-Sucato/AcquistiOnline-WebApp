package main.java.repository;

import java.util.List;

import main.java.model.Article;
import main.java.model.Cart;

public interface CartRepository {

    public Cart findCartByClientCode (String clientCode);
    public void saveCart (Cart cart);
    public void updateCart (Cart cart);
    public void addItem (Article article, int quantity, String clientCode);
    public void removeItem(Article article, String clientCode);
    public void updateQuantity(Article article, int newQuantity, String clientCode);
    public void clearCart(String clientCode);
    public void listArticle(List<Article> articles, String clientCode);
}
