package com.codeflu.Dao;

import com.codeflu.Model.User;

import java.util.*;

public class UserDAO {
    private final Map<Long, User> users = new HashMap<>();
    private long idCounter = 1;

    public User create(User user) {
        user.setId(idCounter++);
        users.put(user.getId(), user);
        return user;
    }

    public Optional<User> getById(Long id) {
        return Optional.ofNullable(users.get(id));
    }

    public List<User> getAll() {
        return new ArrayList<>(users.values());
    }

    public boolean delete(Long id) {
        return users.remove(id) != null;
    }
}
