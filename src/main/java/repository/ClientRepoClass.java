package main.java.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.java.config.DatabaseConfig;
import main.java.model.Client;

public class ClientRepoClass implements ClientRepository {
    private List<Client> clients = new ArrayList<>();

    @Override
    public Client findById(int clientCode) {

        // old implementation
        // for (Client clients : client) {
        // if (client.getClientCode() == clientCode) {
        // return client;
        // } else {
        // return null;
        // }
        // }

        // DB implementation
        String query = "SELECT * FROM clients WHERE code_client = ?";

        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, clientCode);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToClient(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Client> findAll() {

        // Old implementation
        // return new ArrayList<>(clients);

        // DB implementation
        String query = "SELECT * FROM clients";

        List<Client> clients = new ArrayList<>();

        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                clients.add(mapResultSetToClient(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    @Override
    public void update(Client client) {

        // old implementation

        // for (int i = 0; i < clients.size(); i++) {
        // if (clients.get(i).getClientCode() == client.getClientCode()) {
        // clients.set(i, client);
        // return;
        // }
        // }

        // DB implementation
        String query = "UPDATE clients SET name=?, last_name=? WHERE code_client=?";

        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, client.getName());
            preparedStatement.setString(2, client.getLastName());
            preparedStatement.setInt(3, client.getClientCode());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int clientCode) {

        // old implementation
        // clients.removeIf(client -> client.getClientCode() == clientCode);

        // DB implementation
        String query = "DELETE FROM clients WHERE code_client=?";

        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, clientCode);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // this method is now overrided because missing

    @Override
    public void save(Client client) {
        // DB implementation
        String query = "INSERT INTO clients (code_client, name, last_name) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, client.getClientCode());
            preparedStatement.setString(2, client.getName());
            preparedStatement.setString(2, client.getLastName());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // new method
    private Client mapResultSetToClient(ResultSet resultSet) throws SQLException {
        int code = resultSet.getInt("code_client");
        String name = resultSet.getString("name");
        String last_name = resultSet.getString("last_name");

        return new Client(code, name, last_name);
    }
}
