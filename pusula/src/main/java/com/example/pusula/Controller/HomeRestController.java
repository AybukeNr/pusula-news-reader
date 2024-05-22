package com.example.pusula.Controller;


import com.example.pusula.DTO.*;

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

@RestController
@RequestMapping("/home")
public class HomeRestController {

    private ArticleService articleService;
    private UserService userService;
    private CategoryService categoryService;
    private CommentService commentService;

    @Autowired
    public HomeRestController(ArticleService articleService, UserService userService, CategoryService categoryService, CommentService commentService) {
        this.articleService = articleService;
        this.userService = userService;
        this.categoryService = categoryService;
        this.commentService = commentService;
    }

    @GetMapping("/")
    public List<ArticleDTO> getPublicArticles(){
        return articleService.getAllArticlesPublic();
    }

    @GetMapping("/private")
    public List<ArticleDTO> getPrivateArticles(){
        return articleService.getAllArticlesPrivate();
    }

    @GetMapping("/article/{id}")
    public ArticleDTO getPublicArticleById(@PathVariable int id){
        return articleService.getPublicArticleById(id);
    }
    @GetMapping("/private/{id}")
    public ArticleDTO getPrivateArticles(@PathVariable int id){
        return articleService.getPrivateArticleById(id);
    }

//    @PostMapping("/createArticle")
//    public ResponseEntity<ArticleDTO> createArticle(@RequestBody ArticleDTO articleDTO) {
//        articleService.insertArticle(articleDTO);
//        return ResponseEntity.status(HttpStatus.CREATED).body(articleDTO);
//    }
    @PostMapping("/updateArticle/{id}")
    public ResponseEntity<ArticleDTO> updateArticle(@PathVariable int id, @Valid @RequestBody ArticleDTO articleDTO) {
        articleService.updateArticle(id, articleDTO);
        return ResponseEntity.ok(articleDTO);
    }

    @GetMapping("/getCategory/{id}")
    public CategoryDTO getCategory(@PathVariable int id){
        return categoryService.findById(id);
    }
    @GetMapping("/getUser/{id}")
    public UserDTO getUser(@PathVariable int id){
        return userService.findById(id);
    }

    @PostMapping("/updateCategory/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable int id, @Valid @RequestBody CategoryDTO categoryDTO) {
        categoryService.updateCategory(id, categoryDTO);
        return ResponseEntity.ok(categoryDTO);
    }

    @PostMapping("/createUser")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
        userService.save(userDTO, 3);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
    }
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        try {
            userService.delete(id);
            return ResponseEntity.ok("User deleted successfully");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (UnsupportedOperationException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

//    @PostMapping("/createCategory")
//    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
//        categoryService.createCategory(categoryDTO, 11);
//        return ResponseEntity.status(HttpStatus.CREATED).body(categoryDTO);
//    }
    @GetMapping("/getAllComments")
    public ResponseEntity<List<CommentDTO>> getAllComments() {
        List<CommentDTO> comments = commentService.findAll();
        return ResponseEntity.ok(comments);
    }
    @PostMapping("/saveComment")
    public ResponseEntity<String> saveComment(@Valid @RequestBody CommentDTO commentDTO) {
        commentService.saveComment(commentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Comment saved successfully");
    }

    @PostMapping("/{id}/visibility")
    public ResponseEntity<String> updateCommentVisibility(@PathVariable int id, @RequestBody CommentVisibilityDTO commentVisibilityDTO) {
        try {
            commentService.updateCommentVisibility(id, commentVisibilityDTO);
            return ResponseEntity.ok("Comment visibility updated successfully");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/deleteComment/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable int id) {
        try {
            commentService.deleteComment(id);
            return ResponseEntity.ok("Comment deleted successfully");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }



}
