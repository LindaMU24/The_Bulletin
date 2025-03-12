package com.examination.the_bulletin;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChannelService {

    private ChannelRepository channelRepository;

    public ChannelService(ChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
    }
    public Channel createChannel(Channel channel) {
        return channelRepository.save(channel);
    }

    public List<Channel> showAllChannels() {
        return channelRepository.findAll();
    }
    public Channel getChannelById(Long channelId) {
        return channelRepository.findById(channelId).orElse(null);
    }
    public void deleteChannel(Long channelId) {
        channelRepository.deleteById(channelId);
    }
}
