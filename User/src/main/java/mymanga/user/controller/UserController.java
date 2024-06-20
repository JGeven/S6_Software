package mymanga.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import mymanga.user.model.User;
import mymanga.user.service.UserService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import java.util.Map;


@RequestMapping("user")
@RestController
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

    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public void listen(String message) {
        try {
            // Parse the JSON message
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> messageMap = objectMapper.readValue(message, Map.class);

            if (messageMap.containsKey("mangaId")) {
                int mangaId = (int) messageMap.get("mangaId");
                String mangaTitle = (String) messageMap.get("mangaTitle");
                int mangaChapter = (int) messageMap.get("mangaChapter");

                System.out.printf("Received message with mangaId %s: %s has just released a new chapter %d!%n", mangaId, mangaTitle, mangaChapter);
            }

            if (messageMap.containsKey("reviewId")) {
                int reviewId = (int) messageMap.get("reviewId");
                int userId = (int) messageMap.get("userId");
                String mangaTitle = (String) messageMap.get("mangaTitle");

                System.out.printf("Received message with reviewId %s and user id %d: Your review for %s has received a new like!", reviewId, userId, mangaTitle);
            }

        } catch (Exception e) {
            // Handle the exception (e.g., logging)
            e.printStackTrace();
        }
    }
}
