package com.example.pusula.Service;

import com.example.pusula.DTO.CommentDTO;
import com.example.pusula.DTO.CommentVisibilityDTO;
import com.example.pusula.Entity.Article;
import com.example.pusula.Entity.Comment;
import com.example.pusula.Entity.User;
import com.example.pusula.Repository.ArticleDAO;
import com.example.pusula.Repository.CommentDAO;
import com.example.pusula.Repository.UserDAO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentDAO commentDAO;
    private final UserDAO userDAO;
    private final ArticleDAO articleDAO;

    @Autowired
    public CommentServiceImpl(CommentDAO commentDAO, UserDAO userDAO,ArticleDAO articleDAO) {
        this.commentDAO = commentDAO;
        this.userDAO = userDAO;
        this.articleDAO = articleDAO;
    }

    @Override
    @Transactional
    public void updateCommentVisibility(int id, CommentVisibilityDTO commentVisibilityDTO) {
        Comment comment;
        try {
            comment = commentDAO.findById(id);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Comment not found");
        }
        comment.setVisible(commentVisibilityDTO.isVisible());
        commentDAO.save(comment);
    }

    @Override
    @Transactional
    public void deleteComment(int id) {
        Comment comment;
        try {
            comment = commentDAO.findById(id);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Comment not found");
        }
        commentDAO.delete(comment);
    }

    @Override
    public List<CommentDTO> findAll() {
        List<Comment> comments = commentDAO.findAll();
        return comments.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void saveComment(CommentDTO commentDTO) {
        Comment comment = new Comment();
        User user = userDAO.findById(commentDTO.getUserId());
        Article article = articleDAO.findById(commentDTO.getArticleId());
        comment.setBody(commentDTO.getBody());
        comment.setUser(user);
        comment.setArticle(article);
        comment.setVisible(commentDTO.isVisible());
        comment.setCommentedAt(LocalDateTime.now());
        commentDAO.save(comment);
    }

    private CommentDTO convertToDTO(Comment comment) {
        CommentDTO dto = new CommentDTO();
        dto.setId(comment.getId());
        dto.setBody(comment.getBody());
        dto.setCommentedAt(comment.getCommentedAt());
        dto.setArticleId(comment.getArticle().getId());
        dto.setUserId(comment.getUser().getId());
        dto.setVisible(comment.isVisible());
        return dto;
    }

}
