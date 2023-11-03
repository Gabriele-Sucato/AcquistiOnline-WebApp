package main.java.model;

public class Cart {
    private string nameCl;
    private string lastnameCl;
    private string paymentMethod;
    private string nameArticle;
    private int quantity;
    private double priceSingle;
    private double priceTot;


public Cart(string nameCl, string lastnameCl, string paymentMethod, string nameArticle, int quantity,
            double priceSingle, double priceTot) {
        this.nameCl = nameCl;
        this.lastnameCl = lastnameCl;
        this.paymentMethod = paymentMethod;
        this.nameArticle = nameArticle;
        this.quantity = quantity;
        this.priceSingle = priceSingle;
        this.priceTot = priceTot;
    }

    public string getNameCl() {
        return nameCl;
    }
    public void setNameCl(string nameCl) {
        this.nameCl = nameCl;
    }


    public string getLastnameCl() {
        return lastnameCl;
    }
    public void setLastnameCl(string lastnameCl) {
        this.lastnameCl = lastnameCl;
    }
    

    public string getPaymentMethod() {
        return paymentMethod;
    }
    public void setPaymentMethod(string paymentMethod) {
        this.paymentMethod = paymentMethod;
    }


    public string getNameArticle() {
        return nameArticle;
    }
    public void setNameArticle(string nameArticle) {
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
    

    @Override
    public String toString() {
        return "Cart [nameCl=" + nameCl + ", lastnameCl=" + lastnameCl + ", paymentMethod=" + paymentMethod
                + ", nameArticle=" + nameArticle + ", quantity=" + quantity + ", priceSingle=" + priceSingle
                + ", priceTot=" + priceTot + "]";
    }
public static void main(String[] args) {
   
}
}
