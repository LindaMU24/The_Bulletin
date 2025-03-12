package com.examination.the_bulletin;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/channels")
public class ChannelController {
    private final ChannelService channelService;

    public ChannelController(ChannelService channelService) {
        this.channelService = channelService;
    }

    @PostMapping
    public Channel createNewChannel(@Valid @RequestBody Channel channel) {
        return channelService.createChannel(channel);

    }

    @GetMapping
    public List<Channel> getAllChannels() {
        return channelService.showAllChannels();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Channel> getChannel(@PathVariable Long channelId) {
        Channel channel = channelService.getChannelById(channelId);
        if (channel != null) {
            return ResponseEntity.ok(channel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public void deleteChannelById(@PathVariable Long channelId) {
        channelService.deleteChannel(channelId);
    }
}
