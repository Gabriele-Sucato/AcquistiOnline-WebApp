package main.java.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Article> articles = new ArrayList<>();
    private String nameCl;
    private String lastnameCl;
    private String paymentMethod;
    private String nameArticle;
    private int quantity;
    private double priceSingle;
    private double priceTot;

    public Cart(String nameCl, String lastnameCl, String paymentMethod, String nameArticle, int quantity,
            double priceSingle, double priceTot) {
        this.nameCl = nameCl;
        this.lastnameCl = lastnameCl;
        this.paymentMethod = paymentMethod;
        this.nameArticle = nameArticle;
        this.quantity = quantity;
        this.priceSingle = priceSingle;
        this.priceTot = priceTot;
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

    public String getNameArticle() {
        return nameArticle;
    }

    public void setNameArticle(String nameArticle) {
        this.nameArticle = nameArticle;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPriceSingle() {
        return priceSingle;
    }

    public void setPriceSingle(double priceSingle) {
        this.priceSingle = priceSingle;
    }

    public double getPriceTot() {
        return priceTot;
    }

    public void setPriceTot(double priceTot) {
        this.priceTot = priceTot;
    }

    public void addArticle(Article article, int quantity) {
        for (Article exArticle : articles) {
            if (exArticle.getNameArticle().equals(article.getNameArticle())) {
                {

                }
            }
        }
    }

    @Override
    public String toString() {
        return "Cart [nameCl=" + nameCl + ", lastnameCl=" + lastnameCl + ", paymentMethod=" + paymentMethod
                + ", nameArticle=" + nameArticle + ", quantity=" + quantity + ", priceSingle=" + priceSingle
                + ", priceTot=" + priceTot + "]";
    }

    public static void main(String[] args) {

    }
}
