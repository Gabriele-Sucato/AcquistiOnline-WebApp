package main.java.model;

public class Warehouse {
    private int id;
    private int availableQty;
    private String articleCode;

    public Warehouse(int id, int availableQty, String articleCode) {
        this.id = id;
        this.availableQty = availableQty;
        this.articleCode = articleCode;
    }

    public Warehouse() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAvailableQty() {
        return availableQty;
    }

    public void setAvailableQty(int availableQty) {
        this.availableQty = availableQty;
    }

    public String getArticleCode() {
        return articleCode;
    }

    public void setArticleCode(String articleCode) {
        this.articleCode = articleCode;
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "id=" + id +
                ", availableQty=" + availableQty +
                ", articleCode='" + articleCode + '\'' +
                '}';
    }
}
