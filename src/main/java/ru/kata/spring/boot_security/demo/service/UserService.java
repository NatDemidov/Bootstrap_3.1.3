package ru.kata.spring.boot_security.demo.service;


import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import java.util.List;
import java.util.Set;

public interface UserService extends UserDetailsService {

    List<User> listUsers();

    void add(User user);

    void update(User user);

    void delete(Long id);

    User getById(Long id);

    User findByUsername(String username);
}
