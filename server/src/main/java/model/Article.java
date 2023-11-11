package main.java.model;

public class Article {
    private String codeArticle;
    private String articleName;
    private double price;
    private String description;
    private int availableQty;

    // Costruttore vuoto
    public Article() {
    }

    // Costruttore con tutti i campi
    public Article(String codeArticle, String articleName, double price, String description, int availableQty) {
        this.codeArticle = codeArticle;
        this.articleName = articleName;
        this.price = price;
        this.description = description;
        this.availableQty = availableQty;
    }

    // Metodi getter e setter

    public String getCodeArticle() {
        return codeArticle;
    }

    public void setCodeArticle(String codeArticle) {
        this.codeArticle = codeArticle;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAvailableQty() {
        return availableQty;
    }

    public void setAvailableQty(int availableQty) {
        this.availableQty = availableQty;
    }

    // Altri metodi di classe o di istanza, se necessario

    @Override
    public String toString() {
        return "Article{" +
                "codeArticle='" + codeArticle + '\'' +
                ", articleName='" + articleName + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", availableQty=" + availableQty +
                '}';
    }
}
