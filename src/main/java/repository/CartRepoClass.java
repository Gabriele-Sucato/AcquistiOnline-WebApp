package main.java.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;
import main.java.model.Cart;
import main.java.model.Article;

public class CartRepoClass implements CartRepository{

    private Map<Integer,Cart> carts = new HashMap<Integer,Cart>();

    @Override
    public void addItem(Article article, int quantity, int id) {
       Cart cart = carts.get(id);
       if (cart == null){

        cart = new Cart();
        carts.put(id, cart);
       }
       cart.addItem(article, quantity, id);
        throw new UnsupportedOperationException("Unimplemented method 'addItem'");
    }

    @Override
    public void removeItem(Article article) {
        
        throw new UnsupportedOperationException("Unimplemented method 'removeItem'");
    }

    @Override
    public void updateQuantity(Article article, int newQuantity) {
        
        throw new UnsupportedOperationException("Unimplemented method 'updateQuantity'");
    }

    @Override
    public void clearCart() {
        
        throw new UnsupportedOperationException("Unimplemented method 'clearCart'");
    }

    @Override
    public void listArticle(List<Article> articles) {
       
        throw new UnsupportedOperationException("Unimplemented method 'listArticle'");
    }
    
}
