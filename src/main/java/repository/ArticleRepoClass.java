package main.java.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import main.java.config.DatabaseConfig;
import main.java.model.Article;

public class ArticleRepoClass implements ArticleRepository {

    private Map<String, Article> articles = new HashMap<>();

    @Override
    public Article findArticleByCode(String code) {

        String query = "SELECT * FROM articles WHERE code_article = ?";
        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, code);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Article article = new Article();
                    article.setCodeArticle(resultSet.getString("code_article"));
                    article.setArticleName(resultSet.getString("article_name"));
                    article.setPrice(resultSet.getDouble("price"));
                    article.setDescription(resultSet.getString("description"));
                    article.setAvailableQty(resultSet.getInt("available_qty"));
                    return article;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void saveArticle(Article article) {

        String query = "INSERT INTO articles (code_article, article_name, price, description, available_qty) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, article.getCodeArticle());
            preparedStatement.setString(2, article.getArticleName());
            preparedStatement.setDouble(3, article.getPrice());
            preparedStatement.setString(4, article.getDescription());
            preparedStatement.setInt(5, article.getAvailableQty());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateArticle(Article article) {

        String query = "UPDATE articles SET article_name = ?, price = ?, description = ?, available_qty = ? WHERE code_article = ?";
        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, article.getArticleName());
            preparedStatement.setDouble(2, article.getPrice());
            preparedStatement.setString(3, article.getDescription());
            preparedStatement.setInt(4, article.getAvailableQty());
            preparedStatement.setString(5, article.getCodeArticle());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
