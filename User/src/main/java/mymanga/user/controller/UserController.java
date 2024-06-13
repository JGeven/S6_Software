package mymanga.user.controller;

import mymanga.user.model.User;
import mymanga.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RequestMapping("user")
public class UserController {

    PasswordEncoder passwordEncoder;
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @GetMapping()
    public ResponseEntity<User> getUserByEmail(@RequestBody User user) {
        User dbUser = userService.getUserByEmail(user.getEmail());
        System.out.println(dbUser);
        return ResponseEntity.ok(dbUser);
    }

    @PostMapping()
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        User dbUser = new User(user.getName(), user.getEmail(), encodedPassword);
        userService.registerUser(dbUser);
        return ResponseEntity.ok(dbUser);
    }

}
