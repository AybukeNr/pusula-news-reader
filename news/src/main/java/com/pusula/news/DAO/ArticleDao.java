package com.pusula.news.DAO;


import com.pusula.news.Entity.Article;
import com.pusula.news.Entity.Category;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ArticleDao{
    List<Article> getAllArticles();
    List<Article> getArticlesPrivate();
    Article getArticleById(int id);
    Article getPrivateArticleById(int id);
    Article getArticleByTitle(String title);
    Article getArticleByCategory(Category category);
    void CreateArticle(Article article);
    void updateArticle(Article article);
    void deleteArticle(int id);


}
