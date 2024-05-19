package com.example.pusula.Service;

import com.example.pusula.DTO.CommentDTO;
import com.example.pusula.DTO.CommentVisibilityDTO;

import java.util.List;

public interface CommentService {
    List<CommentDTO> findAll();
    void saveComment(CommentDTO comment);
    void updateCommentVisibility(int commentId, CommentVisibilityDTO  commentVisibilityDTO);
    void deleteComment(int id);
}
