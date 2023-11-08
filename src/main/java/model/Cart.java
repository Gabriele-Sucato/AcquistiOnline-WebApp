package main.java.model;

import java.util.ArrayList;
import java.util.List;

import main.java.repository.CartRepository;

public class Cart {
   
    private List<Article> articles = new ArrayList<>();
    private String nameCl;
    private String lastnameCl;
    private String paymentMethod;

    public Cart(String nameCl, String lastnameCl, String paymentMethod) {
        this.nameCl = nameCl;
        this.lastnameCl = lastnameCl;
        this.paymentMethod = paymentMethod;
    }

    public Cart() {
    }

    public String getNameCl() {
        return nameCl;
    }

    public void setNameCl(String nameCl) {
        this.nameCl = nameCl;
    }

    public String getLastnameCl() {
        return lastnameCl;
    }

    public void setLastnameCl(String lastnameCl) {
        this.lastnameCl = lastnameCl;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void addArticle(Article article, int quantity) {
        boolean articleExists = false;

        for (Article existingArticle : articles) {
            if (existingArticle.getNameArticle().equals(article.getNameArticle())) {
                existingArticle.setQuantity(existingArticle.getQuantity() + quantity);
                articleExists = true;
                break;
            }
        }

        if (!articleExists) {
            article.setQuantity(quantity);
            articles.add(article);
        }
    }

    @Override
    public String toString() {
        return "Cart{" +
                "nameCl='" + nameCl + '\'' +
                ", lastnameCl='" + lastnameCl + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", articles=" + articles +
                '}';
    }











public static void main(String[] args) {
   
}
}
