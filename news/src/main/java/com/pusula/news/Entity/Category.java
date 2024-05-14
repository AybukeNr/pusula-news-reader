package com.pusula.news.Entity;

import jakarta.annotation.Generated;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Table(name="categories")
@Entity
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name="author_id")
    private List<User> Moderators;




}
