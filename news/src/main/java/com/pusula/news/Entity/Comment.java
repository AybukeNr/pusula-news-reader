package com.pusula.news.Entity;

import java.security.Timestamp;

import jakarta.annotation.Generated;
import jakarta.persistence.*;
import lombok.Data;


@Table(name ="comment")
@Entity
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "content", nullable = false)
    private String content;

    @OneToOne(fetch = FetchType.LAZY,cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "article_id")
    private Article article_id;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE,CascadeType.PERSIST, CascadeType.REFRESH,CascadeType.DETACH})
    @JoinColumn(name= "user_id")
    private User user_id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "commended_at", nullable = false)
    private Timestamp commended_at;

   


}
