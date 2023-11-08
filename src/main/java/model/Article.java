package main.java.model;

public class Article {
    private String codeArticle;
    private String nameArticle;
    private double price;
    private String description;
    private int availableQty;

    public Article(String codeArticle, String nameArticle, double price, String description, int availableQty) {
        this.codeArticle = codeArticle;
        this.nameArticle = nameArticle;
        this.price = price;
        this.description = description;
        this.availableQty = availableQty;
    }

    public Article() {
    }

    public String getCodeArticle() {
        return codeArticle;
    }

    public void setCodeArticle(String codeArticle) {
        this.codeArticle = codeArticle;
    }

    public String getNameArticle() {
        return nameArticle;
    }

    public void setNameArticle(String nameArticle) {
        this.nameArticle = nameArticle;
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

    @Override
    public String toString() {
        return "Article{" +
                "codeArticle='" + codeArticle + '\'' +
                ", nameArticle='" + nameArticle + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", availableQty=" + availableQty +
                '}';
    }
}