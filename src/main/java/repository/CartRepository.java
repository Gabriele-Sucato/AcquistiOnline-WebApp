package main.java.repository;

import java.util.List;

import main.java.model.Article;
import main.java.model.Cart;

public interface CartRepository {

    public void addItem(Article article, int quantity);

    public void addItem(Article article, int quantity, int id);

    public void removeItem(Article article);

    public void updateQuantity(Article article, int newQuantity);

    public void clearCart();

    public void listArticle(List<Article> articles);

    public Cart findCartByClientCode(String clientCode);

    public void saveCart(Cart cart);

    public void updateCart(Cart cart);
}
