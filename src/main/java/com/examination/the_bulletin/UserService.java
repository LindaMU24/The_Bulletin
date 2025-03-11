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

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public User addUser(User user) {
        return userRepo.save(user);
    }
    public List<UserDTO> getAllUserDTOs() {
        return userRepo.findAll()
                .stream()
                .map(UserMapper.INSTANCE::userToUserDTO)
                .collect(Collectors.toList());
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
//    public Page<UserDTO> getAllUserDTOs(Pageable pageable) {   //Väntar på Channel och Post
//
//    }
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
