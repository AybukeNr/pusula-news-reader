package com.example.pusula.Service;

import com.example.pusula.DTO.ArticleDTO;
import com.example.pusula.Entity.Article;

import java.util.List;

public interface ArticleService {
    ArticleDTO convertToDTO(Article article);
    List<ArticleDTO> getAllArticlesPublic();
    List<ArticleDTO> getAllArticlesPrivate();
    ArticleDTO getPublicArticleById(int id);
    ArticleDTO getPrivateArticleById(int id);
    void insertArticle(ArticleDTO article);
    void deleteArticle(int id);
    void updateArticle(int id, ArticleDTO articleDTO);

}
