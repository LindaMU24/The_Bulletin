package com.examination.the_bulletin;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO userToUserDTO(User user);
    User userDTOToUser(UserDTO userDTO);

    PostDTO postToPostDTO(Post post);
    List<PostDTO> postsToPostDTOs(List<Post> posts);

    ChannelDTO channelDTO(Channel channel);
    List<ChannelDTO> channelsToChannelDTOs(List<Channel> channel);

}
