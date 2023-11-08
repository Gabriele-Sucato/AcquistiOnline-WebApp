package main.java.repository;

import java.util.*;
import main.java.model.Cart;
import main.java.model.Article;

public class CartRepoClass implements CartRepository{

    private Map<String,Cart> carts= new HashMap<>();

    @Override
    public Cart findCartByClientCode(String clientCode) {
    return carts.get(clientCode); 
    }

    @Override
    public void saveCart(Cart cart) {
        carts.put(cart.getNameCl(), cart);
    }

    @Override
    public void updateCart(Cart cart) {
        // Aggiorna il carrello nel repository, se necessario
        // (ad esempio, se si utilizza un database)
        // Non è necessario per l'implementazione in memoria con Map
  
    }

    @Override
    public void addItem(Article article, int quantity, String clientCode) {
        Cart cart= carts.get(clientCode);
        if(cart==null){
            cart=new Cart();
            cart.setNameCl(clientCode);
            carts.put(clientCode,cart);
        }  
        cart.addArticle(article, quantity);
    }

    @Override
    public void removeItem(Article article, String clientCode) {
        Cart cart= carts.get(clientCode);
        if (cart != null){
            cart.getArticles().remove(a -> a.getNameArticle().equals(article.getNameArticle()));
        }    
    }

    @Override
    public void updateQuantity(Article article, int newQuantity, String clientCode) {
        Cart cart = carts.get(clientCode);
        if (cart != null) {
            for (Article existingArticle : cart.getArticles()) {
                if (existingArticle.getNameArticle().equals(article.getNameArticle())) {
                    existingArticle.setQuantity(newQuantity);
                    break;
                }
            }
        }
 }

    @Override
    public void clearCart(String clientCode) {
        carts.remove(clientCode);
    }

    @Override
    public void listArticle(List<Article> articles, String clientCode) {
        Cart cart = carts.get(clientCode);
        if (cart != null) {
            articles.addAll(cart.getArticles());
        }
    }


}
