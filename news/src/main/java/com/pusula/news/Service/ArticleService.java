package com.pusula.news.Service;

import com.pusula.news.DTO.ArticleDTO;
import com.pusula.news.DTO.GetArticleDTO;
import com.pusula.news.Entity.Article;
import com.pusula.news.Entity.Category;

import java.util.List;
import java.util.Optional;

public interface ArticleService {

    List<Article> getAllArticles();
    List<Article> getArticlesPrivate();
    Article getArticleById(int id);
    Article getArticleByTitle(String title);
    Article getArticleByCategory(Category category);
    void CreateArticle(Article article);
    void updateArticle(Article article);
    void deleteArticle(int id);
}
