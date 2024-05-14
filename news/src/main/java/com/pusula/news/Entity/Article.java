package com.pusula.news.Entity;
import java.util.*;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@Table(name="article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "body")
    private String body;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "published_at")
    private Timestamp published_at;

    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "author_id" )
    private User Author;

    @Column(name = "image_url")
    private String image_url;

    @OneToOne(fetch = FetchType.EAGER ,cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "private_article")
    private boolean private_article;


}
