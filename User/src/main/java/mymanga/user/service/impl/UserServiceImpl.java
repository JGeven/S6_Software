package mymanga.user.service.impl;

import mymanga.user.model.User;
import mymanga.user.repository.UserRepository;
import mymanga.user.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user;
    }

    public User registerUser(User user) {
        return userRepository.save(user);
    }
}
