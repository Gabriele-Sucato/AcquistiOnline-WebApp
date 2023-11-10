package main.java.model;

public class Purchase {
    private int operationCode;
    private String clientCode;
    private String articleCode;
    private double unitPrice;
    private int purchaseQty;
    private String paymentType;

    public Purchase(int operationCode, String clientCode, String articleCode, double unitPrice, int purchaseQty) {
        this.operationCode = operationCode;
        this.clientCode = clientCode;
        this.articleCode = articleCode;
        this.unitPrice = unitPrice;
        this.purchaseQty = purchaseQty;
    }

    public Purchase(String clientCode, String articleCode, double unitPrice, int purchaseQty) {
        this.clientCode = clientCode;
        this.articleCode = articleCode;
        this.unitPrice = unitPrice;
        this.purchaseQty = purchaseQty;
    }

    public Purchase() {
    }

    public int getOperationCode() {
        return operationCode;
    }

    public void setOperationCode(int operationCode) {
        this.operationCode = operationCode;
    }

    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public String getArticleCode() {
        return articleCode;
    }

    public void setArticleCode(String articleCode) {
        this.articleCode = articleCode;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getPurchaseQty() {
        return purchaseQty;
    }

    public void setPurchaseQty(int purchaseQty) {
        this.purchaseQty = purchaseQty;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "operationCode=" + operationCode +
                ", clientCode='" + clientCode + '\'' +
                ", articleCode='" + articleCode + '\'' +
                ", unitPrice=" + unitPrice +
                ", purchaseQty=" + purchaseQty +
                '}';
    }

}
