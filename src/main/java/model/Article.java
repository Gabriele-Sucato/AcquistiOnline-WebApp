package main.java.model;

public class Article {


    private int codeOperation;
     private int codeClient;
     private String typePayment;
     private int quantityArticle;
     private String nameArticle;



     public Article(int codeOperation, int codeClient, String typePaymant,int quantityArticle, String nameArticle){
        this.codeOperation = codeOperation;
        this.codeClient = codeClient;
        this.typePaymant = typePaymant;
        this.quantityArticle = quantityArticle;
        this.nameArticle = nameArticle;
     }

    public Article(){}

    public void setTypePayment(String typePayment) {
        this.typePayment = typePayment;
    }


     public String getTypePayment() {
        return typePayment;
    }

    public String getNameArticle() {
        return nameArticle;
    }

    public void setNameArticle(String nameArticle) {
        this.nameArticle = nameArticle;
    }
    
     public int getCodeOperation() {
        return codeOperation;
    }
    public void setCodeOperation(int codeOperation) {
        this.codeOperation = codeOperation;
    }
    public int getCodeClient() {
        return codeClient;
    }
    public void setCodeClient(int codeClient) {
        this.codeClient = codeClient;
    }
    public Private getString() {
        return String;
    }
    public void setString(Private string) {
        String = string;
    }
    public int getQuantityArticle() {
        return quantityArticle;
    }
    public void setQuantityArticle(int quantityArticle) {
        this.quantityArticle = quantityArticle;
    }

     @Override
     public String toString() {
        return "Article - Code Operation: " +  codeOperation + " Code Client:" + codeClient + "Type of payment: " + typePayment + "Quantity: " + quantityArticle;
     }

     




}
