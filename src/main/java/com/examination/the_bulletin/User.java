package com.examination.the_bulletin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "name can not be blank")
    @Size(min = 2, max = 16, message = "name must be between 2 and 16 characters long")
    private String username;

    @Email(message = "Please provide a valid email address")
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Post> posts = new ArrayList<>();

    public User() {
    }

    public User(Long id, String username, String email, List<Post> posts) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.posts = posts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "name can not be blank") @Size(min = 2, max = 16, message = "name must be between 2 and 16 characters long") String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank(message = "name can not be blank") @Size(min = 2, max = 16, message = "name must be between 2 and 16 characters long") String name) {
        this.username = name;
    }

    public @Email(message = "Please provide a valid email address") String getEmail() {
        return email;
    }

    public void setEmail(@Email(message = "Please provide a valid email address") String email) {
        this.email = email;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", name='" + username + '\'' +
                ", email='" + email + '\'';
    }
}