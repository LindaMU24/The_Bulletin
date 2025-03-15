package com.examination.the_bulletin;

import java.time.LocalDateTime;

public class ChannelDTO {
    private Long id;
    private String name;
    private LocalDateTime createdAt;
    private Long postCount;

    public ChannelDTO(Long id, String name, LocalDateTime createdAt, Long postCount) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.postCount = postCount;
    }

    public ChannelDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getPostCount() {
        return postCount;
    }

    public void setPostCount(Long postCount) {
        this.postCount = postCount;
    }
}
