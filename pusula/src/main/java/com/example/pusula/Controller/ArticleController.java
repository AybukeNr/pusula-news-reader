package com.example.pusula.Controller;

import com.example.pusula.DTO.*;
import com.example.pusula.Entity.Article;
import com.example.pusula.Entity.Category;
import com.example.pusula.Entity.User;

import com.example.pusula.Service.ArticleService;
import com.example.pusula.Service.CategoryService;
import com.example.pusula.Service.CommentService;
import com.example.pusula.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;
import com.example.pusula.Entity.Article;
import com.example.pusula.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ArticleController {

    private ArticleService articleService;
    private UserService userService;
    private CategoryService categoryService;
    private CommentService commentService;

    @Autowired
    public ArticleController(ArticleService articleService, UserService userService, CategoryService categoryService, CommentService commentService) {
        this.articleService = articleService;
        this.userService = userService;
        this.categoryService = categoryService;
        this.commentService = commentService;
    }

    @GetMapping("/news")
    public String getPublicArticles(Model theModel){

        List<ArticleDTO> articles = articleService.getAllArticlesPublic();
        theModel.addAttribute("articles", articles);
        return  "index";
    }
    @GetMapping("/sport")
    public String getSports(Model theModel){
        List<ArticleDTO> articles = articleService.getSports();
        theModel.addAttribute("articles", articles);
        return  "sport";
    }



}
