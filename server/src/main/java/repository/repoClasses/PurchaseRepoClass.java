package main.java.repository.repoClasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.java.config.DatabaseConfig;
import main.java.model.Purchase;
import main.java.repository.interfaces.PurchaseRepository;

public class PurchaseRepoClass implements PurchaseRepository {

    @Override
    public void savePurchase(Purchase purchase) {
        String query = "INSERT INTO purchases (code_client, code_article, payment_type, purchase_qty, unit_price) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, purchase.getClientCode());
            preparedStatement.setString(2, purchase.getArticleCode());
            preparedStatement.setString(3, purchase.getPaymentType());
            preparedStatement.setInt(4, purchase.getPurchaseQty());
            preparedStatement.setDouble(5, purchase.getUnitPrice());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Purchase> getPurchasesByClientCode(String clientCode) {
        List<Purchase> purchases = new ArrayList<>();
        String query = "SELECT * FROM purchases WHERE code_client = ?";

        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, clientCode);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Purchase purchase = mapResultSetToPurchase(resultSet);
                    purchases.add(purchase);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return purchases;
    }

    @Override
    public void updatePurchase(Purchase purchase) {
        String query = "UPDATE purchases SET code_article = ?, payment_type = ?, purchase_qty = ? WHERE operation_code = ?";

        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, purchase.getArticleCode());
            preparedStatement.setString(2, purchase.getPaymentType());
            preparedStatement.setInt(3, purchase.getPurchaseQty());
            preparedStatement.setInt(4, purchase.getOperationCode());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePurchase(int operationCode) {
        String query = "DELETE FROM purchases WHERE operation_code = ?";

        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, operationCode);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Purchase> getAllPurchases() {
        List<Purchase> purchases = new ArrayList<>();
        String query = "SELECT * FROM purchases";

        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Purchase purchase = mapResultSetToPurchase(resultSet);
                purchases.add(purchase);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return purchases;
    }

    private Purchase mapResultSetToPurchase(ResultSet resultSet) throws SQLException {
        Purchase purchase = new Purchase();
        purchase.setClientCode(resultSet.getString("code_client"));
        purchase.setArticleCode(resultSet.getString("code_article"));
        purchase.setPaymentType(resultSet.getString("payment_type"));
        purchase.setPurchaseQty(resultSet.getInt("purchase_qty"));
        return purchase;
    }
}
