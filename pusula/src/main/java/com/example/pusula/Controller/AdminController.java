package com.example.pusula.Controller;


import com.example.pusula.DTO.ArticleDTO;
import com.example.pusula.DTO.CategoryDTO;
import com.example.pusula.DTO.UserDTO;
import com.example.pusula.Entity.User;
import com.example.pusula.Service.ArticleService;
import com.example.pusula.Service.CategoryService;
import com.example.pusula.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private ArticleService articleService;
    private CategoryService categoryService;
    private UserService userService;
    @Autowired
    public AdminController(ArticleService articleService) {
        this.articleService = articleService;
        this.categoryService = categoryService;
        this.userService = userService;
    }
    @GetMapping("/adminp")
    public String adminPanel(Model model) {
        return "admin";
    }


    @GetMapping("/allnews")
    public String allNews(Model model) {
        List<ArticleDTO> articleDTOS = articleService.getAllArticles();
        model.addAttribute("articles", articleDTOS);
        return "allnews";
    }
    @PostMapping("/deleteArticle")
    public String deleteArticle(@ModelAttribute ArticleDTO articleDTO) {
        articleService.deleteArticle(articleDTO);
        return "redirect:allnews";
    }
    @PostMapping("/addArticle")
    public String addArticle(@ModelAttribute ArticleDTO articleDTO, @RequestParam("categoryId") int categoryId) {
        articleService.insertArticle(articleDTO, categoryId);
        return "redirect:allnews";
    }

    @GetMapping("/addnews")
    public String addNews(Model model) {
        model.addAttribute("article", new ArticleDTO());
        return "/addnews";
    }
    @GetMapping("/yoneticiler")
    public String yoneticiler(Model model) {
        List<User> mods = userService.findYoneticiler();
        model.addAttribute("yonetici", mods);
        return "/yoneticiler";
    }
    @GetMapping("/addyonetici")
    public String addyonetici(Model model) {
        return "addyonetici";
    }
    @GetMapping("/kullanicilar")
    public String kullanicilar(Model model) {
        return "kullanicilar";
    }
    @GetMapping("/comments")
    public String comments(Model model) {
        return "comments";
    }


}
