package mymanga.user.service;

import mymanga.user.model.User;

public interface UserService {

    User getUserByEmail(String email);
}
