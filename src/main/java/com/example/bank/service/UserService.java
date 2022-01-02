package com.example.bank.service;

import com.example.bank.model.User;

public interface UserService {

    void save(User user);

    User getUserByEmail(String email);

    boolean userExists(String login);

    User getUser(String login);

    void update(User user);
}
