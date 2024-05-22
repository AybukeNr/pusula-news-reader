package com.example.pusula.Repository;

import com.example.pusula.Entity.Category;
import com.example.pusula.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


import java.util.Optional;

public interface CommentDAO {
    Comment findById(int id);
    List<Comment> findByArticleCategory(Category category);
    void save(Comment comment);
    void delete(Comment comment);
    void deleteByUserId(int userId);
    List<Comment> findAll();  // Add this method if you need to fetch all comments
}