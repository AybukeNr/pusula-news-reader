package com.example.pusula.Service;

import com.example.pusula.DTO.ArticleDTO;
import com.example.pusula.Entity.Article;
import com.example.pusula.Entity.Category;
import com.example.pusula.Repository.ArticleDAO;
import com.example.pusula.Repository.ArticleDAOImpl;
import com.example.pusula.Repository.CategoryDAO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ArticleServiceImpl implements ArticleService{

    private ArticleDAOImpl articleDAO;
    private CategoryDAO categoryDAO;


    @Autowired
    public ArticleServiceImpl(ArticleDAOImpl articleDAO, CategoryDAO categoryDAO) {
        this.articleDAO = articleDAO;
        this.categoryDAO = categoryDAO;
    }


    @Override
    public ArticleDTO convertToDTO(Article article) {
        ArticleDTO dto = new ArticleDTO();
        dto.setTitle(article.getTitle());
        dto.setBody(article.getBody());
        dto.setImage_url(article.getImageUrl());
        // Assuming you have a getCategory() method in your Article class
        return dto;
    }

    @Override
    public List<ArticleDTO> getAllArticlesPublic() {
        List<Article> articles = articleDAO.getAllArticlesPublic();
        return articles.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleDTO> getAllArticlesPrivate() {
        List<Article> articles = articleDAO.getAllArticlesPrivate();
        return articles.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ArticleDTO getPublicArticleById(int id) {
        Article article = articleDAO.getPublicArticleById(id);
        ArticleDTO dto = new ArticleDTO();
        dto.setTitle(article.getTitle());
        dto.setBody(article.getBody());
        dto.setImage_url(article.getImageUrl());
        return dto;
    }

    @Override
    public ArticleDTO getPrivateArticleById(int id) {
        Article article = articleDAO.getPrivateArticleById(id);
        ArticleDTO dto = new ArticleDTO();
        dto.setTitle(article.getTitle());
        dto.setBody(article.getBody());
        dto.setImage_url(article.getImageUrl());
        return dto;
    }

    @Override
    public void insertArticle(ArticleDTO articleDTO) {
        Article article = new Article();
        Category category = categoryDAO.findById(3);
        article.setCategory(category);
        article.setBody(articleDTO.getBody());
        article.setTitle(articleDTO.getTitle());
        article.setImageUrl(articleDTO.getImage_url());
        article.setPublishedAt(LocalDateTime.now());
        article.setPrivate(false);
        articleDAO.insertArticle(article);
    }



    @Override
    public void deleteArticle(int id) {

    }

    @Override
    @Transactional
    public void updateArticle(int id, ArticleDTO articleDTO) {
        Article article = articleDAO.findById(id);
        if (article != null) {

            article.setBody(articleDTO.getBody());
            article.setTitle(articleDTO.getTitle());
            article.setImageUrl(articleDTO.getImage_url());
            articleDAO.updateArticle(article);
        }
    }
}
