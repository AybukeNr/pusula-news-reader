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
    @GetMapping("/{id}")
    public String getArticle(Model theModel, @PathVariable("id") int id) {
        // Retrieve article details based on the provided ID
        ArticleDTO article = articleService.getPublicArticleById(id);

        // Add the article object to the model
        theModel.addAttribute("article", article);

        // Return the view name
        return "article";
    }
    @GetMapping("/private/{id}")
    public String getPrivateArticle(Model theModel, @PathVariable("id") int id) {
        // Retrieve article details based on the provided ID
        ArticleDTO article = articleService.getPrivateArticleById(id);

        // Add the article object to the model
        theModel.addAttribute("article", article);

        // Return the view name
        return "articles";
    }

    @GetMapping("/sport")
    public String getSports(Model theModel){
        List<ArticleDTO> articles = articleService.getSports();
        theModel.addAttribute("articles", articles);
        return  "sport";
    }
    @GetMapping("/technology")
    public String getTech(Model model){
        List<ArticleDTO> articles = articleService.getTechnology();
        model.addAttribute("articles", articles);
        return  "technology";
    }
    @GetMapping("/politics")
    public String getPolitics(Model theModel){
        List<ArticleDTO> articles = articleService.getPolitics();
        theModel.addAttribute("articles", articles);
        return  "politics";
    }
    @GetMapping("/health")
    public String getHeatlh(Model theModel){
        List<ArticleDTO> articles = articleService.getHealth();
        theModel.addAttribute("articles", articles);
        return  "health";
    }
    @GetMapping("/author")
    public String getAuthors(Model theModel){
        List<ArticleDTO> articles = articleService.getAllArticlesPrivate();
        theModel.addAttribute("articles", articles);
        return  "author";
    }



}
