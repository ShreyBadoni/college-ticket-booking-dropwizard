package com.codeflu.Services;


import com.codeflu.Dao.UserDAO;
import com.codeflu.Model.User;

import java.util.List;
import java.util.Optional;

public class UserService {
    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User createUser(User user) {
        return userDAO.create(user);
    }

    public Optional<User> getUserById(Long id) {
        return userDAO.getById(id);
    }

    public List<User> getAllUsers() {
        return userDAO.getAll();
    }

    public boolean deleteUser(Long id) {
        return userDAO.delete(id);
    }
}
