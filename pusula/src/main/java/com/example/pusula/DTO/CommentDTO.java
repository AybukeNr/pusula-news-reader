package com.example.pusula.DTO;


import com.example.pusula.Entity.Article;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {

    private int id;
    private String body;
    private LocalDateTime commentedAt;
    private int userId;
    private int articleId;
    private boolean visible;

}
