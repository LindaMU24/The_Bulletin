package com.examination.the_bulletin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findByUserId(Long userId, Pageable pageable);

    List<Post> findByChannelId(Long channelId);

    Page<Post> findByChannelId(Long channelId, Pageable pageable);

    void deleteByChannelId(Long channelId);

    long countByChannelId(Long channelId);
}
