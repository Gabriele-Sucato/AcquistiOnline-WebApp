package main.java.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.java.config.DatabaseConfig;
import main.java.model.Article;
import main.java.model.Cart;

public class CartRepoClass implements CartRepository {

    private Map<Integer, Cart> carts = new HashMap<Integer, Cart>();

    @Override
    public void addItem(Article article, int quantity, int id) {
        Cart cart = carts.get(id);
        if (cart == null) {
            cart = new Cart();
            carts.put(id, cart);
        }

        // db implementation
        String query = "INSERT INTO carts (code_client, payment_method, article_qty, code_article) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, cart.getPaymentMethod());
            preparedStatement.setInt(3, quantity);
            preparedStatement.setString(4, article.getCodeArticle());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeItem(Article article) {

        // db implementation

        String query = "DELETE FROM carts WHERE code_article = ?";
        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, article.getCodeArticle());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateQuantity(Article article, int newQuantity) {

        // db implementation
        String query = "UPDATE carts SET article_qty = ? WHERE code_article = ?";
        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, newQuantity);
            preparedStatement.setString(2, article.getCodeArticle());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void clearCart() {

        // db implementation

        String query = "DELETE FROM carts";
        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Article> listArticle() {

        String query = "SELECT * FROM carts";
        List<Article> articles = new ArrayList<>();
        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Article article = new Article();
                article.setCodeArticle(resultSet.getString("code_article"));

                articles.add(article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articles;
    }

}
