package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;
import java.security.Principal;

@Controller
public class UsersController {

    private UserService userService;
    private RoleService roleService;

    public UsersController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    //User
    @GetMapping(value = "/user")
    public String pageForUser(Principal principal, Model model) {
        model.addAttribute("user",
                userService.findByUsername(principal.getName()));
        return "user";
    }

    //Admin
    @GetMapping(value = "/admin")
    public String pageForAdmin(Principal principal, ModelMap model) {
        model.addAttribute("users", userService.listUsers());
        model.addAttribute("thisUser",
                userService.findByUsername(principal.getName()));
        model.addAttribute("newUser", new User());
        model.addAttribute("roles", roleService.listRoles());
        return "users";
    }

    @PostMapping("admin")
    public String addUser(@ModelAttribute("newUser") User newUser) {
        userService.add(newUser);
        return "redirect:/admin";
    }

    @PostMapping("admin/update")
    public String update(@ModelAttribute("userToEdit") User userToEdit) {
        userService.update(userToEdit);
        return "redirect:/admin";
    }

    @DeleteMapping("admin/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}
