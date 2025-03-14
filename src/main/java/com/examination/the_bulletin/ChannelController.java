package com.examination.the_bulletin;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/channels")
public class ChannelController {
    private final ChannelService channelService;
    private final ChannelRepository channelRepository;
    private final PostService postService;
    private final UserRepository userRepository;

    @Autowired
    public ChannelController(ChannelService channelService, ChannelRepository channelRepository, PostService postService, UserRepository userRepository) {
        this.channelService = channelService;
        this.channelRepository = channelRepository;
        this.postService = postService;
        this.userRepository = userRepository;

    }

    @PostMapping
    public Channel createNewChannel(@Valid @RequestBody Channel channel) {
        return channelService.createChannel(channel);

    }

    @GetMapping
    public Page<ChannelDTO> getAllChannelsWithPostCount(Pageable pageable) {
        return channelService.showAllChannels (pageable);
    }

    @GetMapping("/{id}/messages")
    public Page<Post> getMessagesInChannel(@PathVariable Long id, Pageable pageable) {
        return postService.getPostsByChannelId(id, pageable);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Channel> updateChannel(@PathVariable Long id, @Valid @RequestBody Channel updatedChannel) {
        Channel channel = channelService.updateChannel(id, updatedChannel);
        if (channel != null) {
            return ResponseEntity.ok(channel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{channelId}/messages")
    public ResponseEntity<?> createMessageInChannel(@PathVariable Long channelId, @RequestBody Post post) {
        if (post.getUser() == null || post.getUser().getId() == null) {
            return ResponseEntity.badRequest().body("Please provide your user ID.");
        }

        Optional<Channel> channelOptional = channelRepository.findById(channelId);
        if (!channelOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Channel channel = channelOptional.get();
        post.setChannel(channel);

        Post createdPost = postService.createPost(post);
        return ResponseEntity.ok(createdPost);
    }

    @DeleteMapping("/{id}")
    public void deleteChannelById(@PathVariable Long id) {
        channelService.deleteChannel(id);
    }
}
