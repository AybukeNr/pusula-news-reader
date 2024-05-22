package com.example.pusula.Service;

import com.example.pusula.DTO.ArticleDTO;
import com.example.pusula.DTO.CategoryDTO;
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
        dto.setId(article.getId());
        dto.setTitle(article.getTitle());
        dto.setBody(article.getBody());
        dto.setImage_url(article.getImageUrl());
        dto.setPublishDate(article.getPublishedAt());
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName(article.getCategory().getName());
        dto.setCategory(categoryDTO.getName());
        dto.setComments(article.getComments());
        return dto;
    }

    @Override
    public List<ArticleDTO> getAllArticles() {
        List<Article> articles = articleDAO.getAllArticles();
        return articles.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
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
        dto.setId(article.getId());
        dto.setTitle(article.getTitle());
        dto.setBody(article.getBody());
        dto.setImage_url(article.getImageUrl());
        dto.setCategory(article.getCategory().getName());
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
    public List<ArticleDTO> getArticleByCategory(CategoryDTO category) {
        int id = category.getId();
        List<Article> article = articleDAO.getArticleByCategoryID(id);
        return article.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleDTO> getSports() {
        List<Article> articles = articleDAO.getSports();
        return articles.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleDTO> getPolitics() {
        List<Article> articles = articleDAO.getPolitics();
        return articles.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleDTO> getHealth() {
        List<Article> articles = articleDAO.getHealth();
        return articles.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleDTO> getTechnology() {
        List<Article> articles = articleDAO.getTechnology();
        return articles.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    @Override
    public void insertArticle(ArticleDTO articleDTO,int categoryID) {
        Article article = new Article();
        Category category = categoryDAO.findById(categoryID);
        article.setCategory(category);
        article.setBody(articleDTO.getBody());
        article.setTitle(articleDTO.getTitle());
        article.setImageUrl(articleDTO.getImage_url());
        article.setPublishedAt(LocalDateTime.now());
        article.setPrivate(articleDTO.isOzel());
        articleDAO.insertArticle(article);
    }



    @Override
    @Transactional
    public void deleteArticle(ArticleDTO articleDTO) {
        Article article=articleDAO.findById(articleDTO.getId());
        articleDAO.deleteArticle(article.getId());

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
