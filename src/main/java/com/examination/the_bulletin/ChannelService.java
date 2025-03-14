package com.examination.the_bulletin;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChannelService {

    private final ChannelRepository channelRepository;
    private final PostService postService;

    @Autowired
    public ChannelService(ChannelRepository channelRepository, PostService postService) {
        this.channelRepository = channelRepository;
        this.postService = postService;
    }
    public Channel createChannel(Channel channel) {
        return channelRepository.save(channel);
    }

    public Page<ChannelDTO> showAllChannels(Pageable pageable) {
        Page<Channel> channels = channelRepository.findAll(pageable);
        channels.forEach(channel -> {
            long postCount = postService.getPostCountForChannel(channel.getId());
            System.out.println("Channel: " + channel.getTitle() + ", Post Count: " + postCount);
        });
        return channels.map(channel -> {
            long postCount = postService.getPostCountForChannel(channel.getId());
            return new ChannelDTO(channel.getId(), channel.getTitle(), channel.getCreatedAt(), postCount);
        });
    }

    public Channel getChannelById(Long channelId) {
        return channelRepository.findById(channelId).orElse(null);
    }

    public Channel updateChannel(Long id, Channel newChannel) {
        return channelRepository.findById(id).map(channel -> {
            channel.setTitle(newChannel.getTitle());
            return channelRepository.save(channel);
        }).orElse(null);
    }

    public void deleteChannel(Long channelId) {
        channelRepository.deleteById(channelId);
    }
}
