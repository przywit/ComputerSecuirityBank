package com.example.bank.service.serviceImpl;

import com.example.bank.model.User;
import com.example.bank.repository.UserRepository;
import com.example.bank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }

    @Override
    public boolean userExists(String login) {
        Optional<User> user = userRepository.findById(login);
        return user.isPresent();
    }

    @Override
    public User getUser(String login) {
        return userRepository.getById(login);
    }

    @Override
    @Transactional
    public void update(User user) {
        User userFromDataBase = userRepository.getById(user.getLogin());

        userFromDataBase.setPassword(user.getPassword());
    }
}
