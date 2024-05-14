package com.pusula.news.Controller;


import com.pusula.news.DTO.GetArticleDTO;
import com.pusula.news.Entity.Article;
import com.pusula.news.Service.ArticleService;
import com.pusula.news.Service.ArticleServiceImpl;
import jakarta.persistence.Column;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
public class ArticeRestController {

    private ArticleService articleService;
    private ModelMapper modelMapper;

    @Autowired
    public ArticeRestController(ArticleService articleService, ModelMapper modelMapper) {
        this.articleService = articleService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("")
    public List<GetArticleDTO> getAllArticles() {
        return articleService.getAllArticles().stream().map(article -> modelMapper.map(
                article,GetArticleDTO.class)).collect(Collectors.toList());
    }


}
