package com.pusula.news.DTO;

import com.pusula.news.Entity.Category;
import com.pusula.news.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetArticleDTO {
    private int id;
    private String title;
    private String body;
    private User author;
    private Timestamp published_at;
    private String image_url;
    private Category category;
    private boolean private_article;
}
