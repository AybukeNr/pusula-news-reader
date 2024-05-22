package com.example.pusula.DTO;

import com.example.pusula.Entity.Category;
import com.example.pusula.Entity.Comment;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ArticleDTO {

    private int id;

    @Size(min = 1, max = 50,message = "is required")
    private String title;

    @Size(min = 1, max = 255,message = "is required")
    private String body;

    private List<Comment> comments;


    private LocalDateTime publishDate;
    @Size(min = 1)
    private String image_url;
    private String category;
    private boolean ozel;




}
