package com.example.pusula.Service;

import com.example.pusula.DTO.ArticleDTO;
import com.example.pusula.DTO.CategoryDTO;
import com.example.pusula.Entity.Article;
import com.example.pusula.Entity.Category;

import java.util.List;

public interface ArticleService {
    ArticleDTO convertToDTO(Article article);
    List<ArticleDTO> getAllArticles();
    List<ArticleDTO> getAllArticlesPublic();
    List<ArticleDTO> getAllArticlesPrivate();
    ArticleDTO getPublicArticleById(int id);
    ArticleDTO getPrivateArticleById(int id);
    List<ArticleDTO> getArticleByCategory(CategoryDTO category);
    List<ArticleDTO> getSports();
    List<ArticleDTO> getPolitics();
    List<ArticleDTO> getHealth();
    List<ArticleDTO> getTechnology();
    void insertArticle(ArticleDTO article,int categoryID);
    void deleteArticle(ArticleDTO articleDTO);
    void updateArticle(int id, ArticleDTO articleDTO);

}
