package main.java.repository.repoClasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.java.config.DatabaseConfig;
import main.java.model.Article;
import main.java.model.Cart;
import main.java.repository.interfaces.CartRepository;

public class CartRepoClass implements CartRepository {

    private Map<String, Cart> carts = Collections.synchronizedMap(new HashMap<>());

    @Override
    public void addItem(Article article, int quantity, int id) {
        Cart cart = carts.get(id);
        if (cart == null) {
            cart = new Cart();
            String stringId = String.valueOf(id);
            carts.put(stringId, cart);
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
    public void clearCart(String clientCode) {

        String query = "DELETE FROM carts WHERE code_client = ?";
        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, clientCode);
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

    @Override
    public void addItem(Article article, int quantity) {
        // Aggiungi l'articolo al carrello
        // Assicurati di gestire correttamente la quantità e di aggiornare la
        // persistenza, se necessario
        // (esempio, salvataggio nel database)
        Cart cart = carts.computeIfAbsent(article.getCodeArticle(), k -> new Cart());
        cart.addToCart(article, quantity);
        carts.put(article.getCodeArticle(), cart);

        // db implementation
        String query = "INSERT INTO carts (code_client, payment_method, article_qty, code_article) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, cart.getCodeClient());
            preparedStatement.setString(2, cart.getPaymentMethod());
            preparedStatement.setInt(3, quantity);
            preparedStatement.setString(4, article.getCodeArticle());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void listArticle(List<Article> articles) {
        // Restituisci la lista degli articoli nel carrello
        // Puoi popolare la lista con gli articoli presenti nel carrello
        for (Cart cart : carts.values()) {
            articles.addAll(cart.getArticles());
        }

        // db implementation
        String query = "SELECT * FROM carts";
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
    }

    @Override
    public Cart findCartByClientCode(String clientCode) {
        // Restituisci il carrello del cliente in base al codice cliente
        // Puoi implementare questa logica se hai associato i carrelli ai clienti
        for (Cart cart : carts.values()) {
            if (cart.getCodeClient().equals(clientCode)) {
                return cart;
            }
        }
        return null; // Ritorna null se il carrello non è stato trovato
    }

    @Override
    public void saveCart(Cart cart) {
        // Salvare il carrello
        // Implementa la logica per salvare il carrello nel database o in un sistema di
        // archiviazione
        // Puoi utilizzare un'operazione di scrittura nel database o nella memoria
        // Ad esempio, puoi memorizzare i carrelli in una mappa con la chiave del codice
        // cliente
        carts.put(cart.getCodeClient(), cart);
    }

    @Override
    public void updateCart(Cart cart) {
        // Aggiorna il carrello
        // Implementa la logica per aggiornare il carrello nel database o nella memoria
        // Ad esempio, puoi utilizzare un'operazione di scrittura nel database o nella
        // memoria
        carts.put(cart.getCodeClient(), cart);
    }

    @Override
    public void clearCart() {
        throw new UnsupportedOperationException("Unimplemented method 'clearCart'");
    }

}
