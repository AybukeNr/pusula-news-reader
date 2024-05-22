package com.example.pusula.Repository;


import com.example.pusula.Entity.Article;
import com.example.pusula.Entity.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ArticleDAOImpl implements ArticleDAO{

    private EntityManager em;
    private CategoryDAO categoryDAO;

    @Autowired
    public ArticleDAOImpl(EntityManager em,CategoryDAO categoryDAO) {
        this.em = em;
        this.categoryDAO=categoryDAO;
    }

    @Override
    public List<Article> getAllArticles() {
        TypedQuery<Article> query = em.createQuery("select a from Article a order by publishedAt desc", Article.class);
        return query.getResultList();
    }

    @Override
    public List<Article> getAllArticlesPublic() {
        TypedQuery<Article> query = em.createQuery("SELECT a FROM Article a where isPrivate=false order by publishedAt desc " , Article.class);
        List<Article> articles = query.getResultList();
        return articles;
    }

    @Override
    public List<Article> getSports() {//2
        TypedQuery<Article> query = em.createQuery("SELECT a FROM Article a WHERE a.category.id = :categoryId AND isPrivate=false order by publishedAt desc", Article.class);
        query.setParameter("categoryId", 2);
        return query.getResultList();
    }

    @Override
    public List<Article> getPolitics() {//1
        TypedQuery<Article> query = em.createQuery("SELECT a FROM Article a WHERE a.category.id = :categoryId AND isPrivate=false order by publishedAt desc", Article.class);
        query.setParameter("categoryId", 1);
        return query.getResultList();
    }

    @Override
    public List<Article> getHealth() {//4
        TypedQuery<Article> query = em.createQuery("SELECT a FROM Article a WHERE a.category.id = :categoryId AND isPrivate=false order by publishedAt desc", Article.class);
        query.setParameter("categoryId", 4);
        return query.getResultList();
    }

    @Override
    public List<Article> getTechnology() {//3
        TypedQuery<Article> query = em.createQuery("SELECT a FROM Article a WHERE a.category.id = :categoryId AND isPrivate=false order by publishedAt desc", Article.class);
        query.setParameter("categoryId", 3);
        return query.getResultList();
    }

    @Override
    public List<Article> getAllArticlesPrivate() {
        TypedQuery<Article> query = em.createQuery("SELECT a FROM Article a where isPrivate=true order by publishedAt desc" , Article.class);
        List<Article> articles = query.getResultList();
        return articles;
    }

    @Override
    public Article getPublicArticleById(int id) {
        try {
            TypedQuery<Article> query = em.createQuery("SELECT a FROM Article a WHERE a.id = :id AND a.isPrivate = false order by publishedAt desc", Article.class);
            query.setParameter("id", id);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null; // No article found with the given ID
        }
    }

    @Override
    public Article getPrivateArticleById(int id) {
        TypedQuery<Article> query = em.createQuery("SELECT a FROM Article a WHERE a.id = :id AND a.isPrivate = true", Article.class);
        query.setParameter("id", id);
        Article article = query.getSingleResult();
        try{
            if (article != null){
                return article;
            }
        }
        catch (NoResultException e) {
            article = null;

        }
        return article;
    }


    @Override
    @Transactional
    public void insertArticle(Article article) {
        em.persist(article);

    }


    @Override
    @Transactional
    public void deleteArticle(int id) {
        Article article = em.find(Article.class, id);
        em.remove(article);
    }

    @Override
    public Article findById(int id) {
        return em.find(Article.class, id);
    }



    @Override
    public List<Article> getArticleByCategoryID(int id) {
        TypedQuery<Article> query = em.createQuery("from Article where category.id = :categoryID", Article.class);
        query.setParameter("categoryID",id);
        List<Article> articles = query.getResultList();
        return articles;
    }

    @Override
    @Transactional
    public void updateArticle(Article article) {
        em.merge(article);
    }
}
