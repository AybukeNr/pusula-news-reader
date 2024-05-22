package com.example.pusula.Controller;


import com.example.pusula.DTO.ArticleDTO;
import com.example.pusula.Entity.Article;
import com.example.pusula.Service.ArticleService;
import com.example.pusula.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/mod")
public class ModeratorController {

    private ArticleService articleService;
    private CommentService commentService;

    @Autowired
    public ModeratorController(ArticleService articleService, CommentService commentService) {
        this.articleService = articleService;
        this.commentService = commentService;
    }

    @GetMapping("/news")
    public String showAllArticles(Model model) {
        model.addAttribute("articles", articleService.getAllArticles());
        List<ArticleDTO> articles = articleService.getAllArticles();
        return "moderator/news";

    }





}
