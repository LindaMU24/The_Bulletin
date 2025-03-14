package com.examination.the_bulletin;

import java.time.LocalDateTime;

public class ChannelDTO {
    private Long id;
    private String name;
    private LocalDateTime date;
    private Long postCount;

    public ChannelDTO(Long id, String name, LocalDateTime date, Long postCount) {
        this.id = id;
        this.name = name;
        this.date = date;
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Long getPostCount() {
        return postCount;
    }

    public void setPostCount(Long postCount) {
        this.postCount = postCount;
    }
}
