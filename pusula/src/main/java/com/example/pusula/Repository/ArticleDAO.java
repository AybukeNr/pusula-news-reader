package com.example.pusula.Repository;

import com.example.pusula.Entity.Article;

import java.util.List;

public interface ArticleDAO {
    List<Article> getAllArticles();
    List<Article> getAllArticlesPublic();
    Article findById(int id);
    List<Article> getSports();
    List<Article> getPolitics();
    List<Article> getHealth();
    List<Article> getTechnology();
    List<Article> getAllArticlesPrivate();
    Article getPublicArticleById(int id);
    Article getPrivateArticleById(int id);
    void insertArticle(Article article);
    void updateArticle(Article article);
    void deleteArticle(int id);

}
