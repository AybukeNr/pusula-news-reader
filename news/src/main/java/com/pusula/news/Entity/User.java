package com.pusula.news.Entity;

import jakarta.annotation.Generated;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Table(name="users")
@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "full_name", nullable = false)
    private String full_name;

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH})
    @JoinColumn(name="role_id")
    private List<Authority> authority;


    @OneToOne(mappedBy = "user_id", optional = false)
    private Comment comment;

}
