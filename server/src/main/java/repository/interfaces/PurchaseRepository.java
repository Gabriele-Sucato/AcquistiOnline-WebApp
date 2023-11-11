package main.java.repository.interfaces;

import java.util.List;

import main.java.model.Purchase;

public interface PurchaseRepository {
    void savePurchase(Purchase purchase);

    List<Purchase> getPurchasesByClientCode(String clientCode);

    void updatePurchase(Purchase purchase);

    void deletePurchase(int operationCode);

    List<Purchase> getAllPurchases();
}
