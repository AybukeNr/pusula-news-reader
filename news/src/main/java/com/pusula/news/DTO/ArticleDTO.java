package com.pusula.news.DTO;

import com.pusula.news.Entity.Category;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ArticleDTO {

    @NotNull
    @Size(min = 1, max = 50,message = "is required")
    private String title;
    @NotNull
    @Size(min = 1, max = 255,message = "is required")
    private String body;

    @Size(min = 1)
    private String image_url;

    @NotNull
    private boolean private_article;

}
