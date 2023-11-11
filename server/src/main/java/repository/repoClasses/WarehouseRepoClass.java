package main.java.repository.repoClasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.java.config.DatabaseConfig;
import main.java.model.Warehouse;
import main.java.repository.interfaces.WarehouseRepository;

public class WarehouseRepoClass implements WarehouseRepository {

    @Override
    public Warehouse findWarehouseByArticleCode(String articleCode) {
        Warehouse warehouse = null;
        String query = "SELECT * FROM warehouses WHERE code_article = ?";

        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, articleCode);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    warehouse = mapResultSetToWarehouse(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gestisci l'eccezione in modo appropriato
        }

        return warehouse;
    }

    @Override
    public void updateWarehouse(Warehouse warehouse) {
        String query = "UPDATE warehouses SET available_qty = ? WHERE code_article = ?";

        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, warehouse.getAvailableQty());
            preparedStatement.setString(2, warehouse.getArticleCode());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Gestisci l'eccezione in modo appropriato
        }
    }

    private Warehouse mapResultSetToWarehouse(ResultSet resultSet) throws SQLException {
        Warehouse warehouse = new Warehouse();
        warehouse.setId(resultSet.getInt("id"));
        warehouse.setAvailableQty(resultSet.getInt("available_qty"));
        warehouse.setArticleCode(resultSet.getString("code_article"));
        return warehouse;
    }
}
