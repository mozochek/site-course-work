package com.mozochek.controller;

import com.mozochek.entity.User;
import com.mozochek.repository.UserRepository;
import com.mozochek.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.mozochek.utils.LengthConstants.*;

@Controller
public class UserController {

    private UserRepository userRepository;
    private UserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(UserRepository userRepository, UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @GetMapping("/login")
    public String login(Model model) {
        addFieldsLengthConstants(model);
        return "login";
    }

    // Этот метод перекрывается обработчиком спринга, но все равно пусть будет на всякий случай
    @PostMapping("/login")
    public String performLogin(@RequestParam String username,
                               @RequestParam String password,
                               Model model) {
        User user = userRepository.findByUsername(username);
        boolean userIsNotExistOrPasswordIncorrect = user == null || !password.equals(user.getPassword());
        if (userIsNotExistOrPasswordIncorrect) {
            model.addAttribute("loginError", "Данные были введены неверно!");
            return "login";
        }
        model.addAttribute("user", user);
        addFieldsLengthConstants(model);
        return "login";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        addFieldsLengthConstants(model);
        return "registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@RequestParam String username,
                                      @RequestParam String password,
                                      Model model) {
        boolean isSavedSuccessfully = userService.addUser(new User(username, bCryptPasswordEncoder.encode(password)));
        if (isSavedSuccessfully) {
            model.addAttribute("saveSuccessful", "Пользователь успешно зарегестрирован!");
        } else {
            model.addAllAttributes(userService.getErrors());
        }
        addFieldsLengthConstants(model);
        return "registration";
    }

    @GetMapping("/profile")
    public String getProfile() {
        return "profile";
    }

    @GetMapping("/admin/users/all")
    public String getUsersList(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "user_list";
    }

    private void addFieldsLengthConstants(Model model) {
        model.addAttribute("usernameLength", USERNAME_LENGTH);
        model.addAttribute("passwordLength", PASSWORD_LENGTH);
    }
}
