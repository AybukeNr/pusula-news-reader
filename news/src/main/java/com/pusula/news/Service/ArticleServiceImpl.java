package com.pusula.news.Service;

import com.pusula.news.DAO.ArticleDao;
import com.pusula.news.DTO.ArticleDTO;
import com.pusula.news.Entity.Article;

import com.pusula.news.Entity.Category;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService{

    private ArticleDao articleDao;

    @Autowired
    public ArticleServiceImpl(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }


    @Override
    public List<Article> getAllArticles() {
        return articleDao.getAllArticles();
    }

    @Override
    public List<Article> getArticlesPrivate() {
        return articleDao.getArticlesPrivate();
    }

    @Override
    public Article getArticleById(int id) {
        return articleDao.getArticleById(id);
    }


    @Override
    public Article getArticleByTitle(String title) {
        return articleDao.getArticleByTitle(title);
    }

    @Override
    public Article getArticleByCategory(Category category) {
        return articleDao.getArticleByCategory(category);
    }

    @Override
    public void CreateArticle(Article article) {
        articleDao.CreateArticle(article);

    }
    @Override
    public void updateArticle(Article article) {
        articleDao.updateArticle(article);

    }

    @Override
    public void deleteArticle(int id) {
        articleDao.deleteArticle(id);
    }
}
