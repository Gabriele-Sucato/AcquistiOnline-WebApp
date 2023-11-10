package main.java.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private String codeClient;
    private String paymentMethod;
    private List<Article> articles;

    public String getCodeClient() {
        return codeClient;
    }

    public void setCodeClient(String codeClient) {
        this.codeClient = codeClient;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public Cart(String codeClient, String paymentMethod, List<Article> articles) {
        this.codeClient = codeClient;
        this.paymentMethod = paymentMethod;
        this.articles = articles;
    }

    public Cart() {
    }

    public void addToCart(Article article, int quantity) {
        if (articles == null) {
            articles = new ArrayList<>();
        }

        // Verifica se l'articolo è già presente nel carrello
        Article existingArticle = findArticleInCart(article);
        if (existingArticle != null) {
            // Se presente, aggiorna la quantità
            existingArticle.setAvailableQty(existingArticle.getAvailableQty() + quantity);
        } else {
            // Altrimenti, aggiungi l'articolo al carrello
            Article newArticle = new Article(article.getCodeArticle(), article.getArticleName(),
                    article.getPrice(), article.getDescription(), quantity);
            articles.add(newArticle);
        }
    }

    public void removeFromCart(Article article) {
        if (articles != null) {
            // Rimuovi l'articolo dal carrello
            articles.removeIf(a -> a.getCodeArticle().equals(article.getCodeArticle()));
        }
    }

    public void clearCart() {
        // Svuota completamente il carrello
        if (articles != null) {
            articles.clear();
        }
    }

    // Aggiunta del metodo findArticleInCart per cercare un articolo nel carrello
    private Article findArticleInCart(Article article) {
        if (articles != null) {
            for (Article cartArticle : articles) {
                if (cartArticle.getCodeArticle().equals(article.getCodeArticle())) {
                    return cartArticle;
                }
            }
        }
        return null;
    }

    // Aggiunta del metodo toString
    @Override
    public String toString() {
        return "Cart{" +
                "codeClient='" + codeClient + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", articles=" + articles +
                '}';
    }
}
