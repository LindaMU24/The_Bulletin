package com.examination.the_bulletin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;
    private final PostRepository postRepository;

    public PostController(PostService postService, PostRepository postRepository) {
        this.postService = postService;
        this.postRepository = postRepository;
    }

    @PostMapping
    public Post createPost(@RequestBody Post post) {
        return postService.createPost(post);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPost(@PathVariable Long id) {
        Post post = postService.getPostById(id);
        if (post != null) {
            return ResponseEntity.ok(post);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/user/{userId}")
    public Page<Post> getPostsByUserId(@PathVariable Long userId, Pageable pageable) {
        return postService.getPostsByUserId(userId, pageable);
    }

    @GetMapping("/{id}/messages")
    public Page<Post> getMessagesInChannel(@PathVariable Long id, Pageable pageable) {
        return postService.getPostsByChannelId(id, pageable);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post updatedPost) {
      Post post = postService.updatePost(id, updatedPost);
      if (post != null) {
          return ResponseEntity.ok(post);
      } else {
          return ResponseEntity.notFound().build();
      }
    }

    @DeleteMapping("/{id}")
    public void deletePostById(@PathVariable Long id) {
        postService.deletePost(id);
    }

}
