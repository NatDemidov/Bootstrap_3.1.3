package ru.kata.spring.boot_security.demo.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserRepository;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, @Lazy PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = encoder;
    }

    @Override
    @Transactional
    public List<User> listUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void add(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void update(User user) {
        if (!user.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        } else {
            user.setPassword(userRepository.getById(user.getId()).getPassword());
            userRepository.save(user);
        }
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public User getById(Integer id) {
        User user = userRepository.findById(id).orElse(null);
        if(user == null) {
            throw new RuntimeException("No user with this id");
        } else {
            return user;
        }
    }

    @Override
    @Transactional
    public User findByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username);
    }
}
