package com.pusula.news.DAO;

import com.pusula.news.Entity.Article;
import com.pusula.news.Entity.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ArticleDaoImpl implements ArticleDao {

    private EntityManager entityManager;

    @Autowired
    public ArticleDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Article> getAllArticles() {
        TypedQuery<Article> query = entityManager.createQuery("from Article WHERE private_article=false", Article.class);
        List<Article> articles = query.getResultList();
        return articles;
    }

    @Override
    public List<Article> getArticlesPrivate() {
        TypedQuery<Article> query = entityManager.createQuery("from Article WHERE private_article=true", Article.class);
        List<Article> articles = query.getResultList();
        return articles;
    }


    @Override
    public Article getArticleById(int id) {
        TypedQuery<Article> query = entityManager.createQuery("from Article where id=:id and private_article=false", Article.class);
        query.setParameter("id", id);
        Article article = query.getSingleResult();
        return article;
    }

    @Override
    public Article getPrivateArticleById(int id) {
        TypedQuery<Article> query = entityManager.createQuery("from Article where id=:id and private_article=true", Article.class);
        query.setParameter("id", id);
        Article article = query.getSingleResult();
        return article;
    }

    @Override
    public Article getArticleByTitle(String title) {
        TypedQuery<Article> query = entityManager.createQuery("from Article where title=:title and private_article=false", Article.class);
        query.setParameter("title", title);
        Article article = query.getSingleResult();
        return article;
    }



    @Override
    public Article getArticleByCategory(Category category) {
        TypedQuery<Article> query = entityManager.createQuery("from Article where category=:category and private_article=false", Article.class);
        query.setParameter("category", category);
        Article article = query.getSingleResult();
        return article;
    }

    @Override
    @Transactional
    public void CreateArticle(Article article) {
        entityManager.persist(article);
    }

    @Override
    @Transactional
    public void updateArticle(Article article) {
        entityManager.merge(article);
    }

    @Override
    @Transactional
    public void deleteArticle(int id) {
        Article article = getArticleById(id);
        entityManager.remove(article);
    }
}
