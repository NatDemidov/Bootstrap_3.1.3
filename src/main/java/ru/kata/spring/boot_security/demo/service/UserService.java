package ru.kata.spring.boot_security.demo.service;


import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;
import java.util.List;

public interface UserService {

    List<User> listUsers();

    void add(User user);

    void update(User user);

    void delete(Integer id);

    User getById(Integer id);

    User findByUsername(String username) throws UsernameNotFoundException;
}
