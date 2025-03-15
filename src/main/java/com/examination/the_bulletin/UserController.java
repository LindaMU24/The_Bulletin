package com.examination.the_bulletin;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/users")
@RestController
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User result = userService.addUser(user);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/all")
    public Page<UserDTO> showAllUsers(Pageable pageable) {
        return userService.getAllUserDTOs(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> showUserById(@PathVariable Long id) {
        UserDTO user = userService.getUserById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        return ResponseEntity.ok(user);
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserDTO>> findByUsernameContainingIgnoreCase(@RequestParam String username) {
        return ResponseEntity.ok(userService.getUserByName(username));

    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody User newUser) {
        newUser.setId(id);

        User user = userService.updateUser(newUser);

        if (user != null) {
            return ResponseEntity.accepted().body(user);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
