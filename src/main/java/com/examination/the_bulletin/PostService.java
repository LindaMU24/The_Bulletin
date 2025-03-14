package com.examination.the_bulletin;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final ChannelRepository channelRepository;
    private final PostRepository postRepository;

    @Autowired
    public PostService(ChannelRepository channelRepository, PostRepository postRepository) {
        this.channelRepository = channelRepository;
        this.postRepository = postRepository;
    }

    public Post createPost(Post post) {
        if (post.getChannel() == null) {
            throw new IllegalArgumentException("The post must have a channel. Please use the correct way of making posts");
        }
        return postRepository.save(post);
    }

    public Post getPostById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }
    public Page<Post> getPostsByUserId(Long userId, Pageable pageable) {
        return postRepository.findByUserId(userId, pageable);
    }
    public Page<Post> getPostsByChannelId(Long channelId, Pageable pageable) {
        return postRepository.findByChannelId(channelId, pageable);
    }
    public long getPostCountForChannel(Long channelId) {
        return postRepository.countByChannelId(channelId);
    }

    public Post updatePost(Long id, Post updatedPost) {
        Optional<Post> existingPostOptional = postRepository.findById(id);
        if (existingPostOptional.isPresent()) {
            Post existingPost = existingPostOptional.get();
            existingPost.setTitle(updatedPost.getTitle());
            existingPost.setContent(updatedPost.getContent());

            return postRepository.save(existingPost);
        } else {
            return null;
        }
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
