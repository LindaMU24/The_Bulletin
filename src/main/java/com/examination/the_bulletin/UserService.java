package com.examination.the_bulletin;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepo;

    @Autowired
    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public User addUser(User user) {
        return userRepo.save(user);
    }


    public Page<UserDTO> getAllUserDTOs(Pageable pageable) {
        return userRepo.findAll(pageable)
                .map(user -> {
                    user.getPosts().forEach(post -> {
                        System.out.println("Post ID: " + post.getId() + ", CreatedAt: " + post.getCreatedAt());
                    });

                    UserDTO userDTO = UserMapper.INSTANCE.userToUserDTO(user);
                    userDTO.setPosts(UserMapper.INSTANCE.postsToPostDTOs(user.getPosts()));
                    return userDTO;
                });
    }


    public Optional<UserDTO> getUserById(Long id) {
        return userRepo.findById(id)
                .map(UserMapper.INSTANCE::userToUserDTO);
    }
    public List<UserDTO> getUserByName(String username) {
        return userRepo.findByUsernameContainingIgnoreCase(username ).stream()
                .map(UserMapper.INSTANCE::userToUserDTO)
                .collect(Collectors.toList());
    }

    public User updateUser(User newUser) {
        return userRepo.findById(newUser.getId()).map(user -> {
            user.setUsername(newUser.getUsername());
            user.setEmail(newUser.getEmail());
            return userRepo.save(user);
        }).orElse(null);
    }
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }
}
