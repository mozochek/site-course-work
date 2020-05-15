package com.mozochek.controller;

import com.mozochek.entity.User;
import com.mozochek.service.UserService;
import com.mozochek.utils.FieldsLengthConstantsImplementing;
import com.mozochek.utils.Role;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController implements FieldsLengthConstantsImplementing {

    private UserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @GetMapping("/login")
    public String login(Model model) {
        addFieldsLengthConstantsIn(model);
        return "login";
    }

    // Этот метод перекрывается обработчиком спринга, но все равно пусть будет на всякий случай
    /*@PostMapping("/login")
    public String performLogin(@RequestParam String username,
                               @RequestParam String password,
                               Model model) {
        User user = userService.findUserByUsername(username);
        boolean userIsNotExistOrPasswordIncorrect = (user == null) || !password.equals(user.getPassword());
        if (userIsNotExistOrPasswordIncorrect) {
            model.addAttribute("loginError", "Пользователь не найден!");
            return "login";
        }
        userService.loadUserByUsername(username);
        addFieldsLengthConstantsIn(model);
        return "login";
    }*/

    @GetMapping("/registration")
    public String registration(Model model) {
        addFieldsLengthConstantsIn(model);
        return "registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@RequestParam String username,
                                      @RequestParam String password,
                                      Model model) {
        boolean isSavedSuccessfully = userService.saveUser(new User(username.trim(), bCryptPasswordEncoder.encode(password.trim())), password, "USER");
        if (isSavedSuccessfully) {
            model.addAttribute("saveSuccessful", "Пользователь успешно зарегестрирован!");
        } else {
            model.addAllAttributes(userService.getErrors());
        }
        addFieldsLengthConstantsIn(model);
        return "registration";
    }

    @GetMapping("/profile")
    public String profileGet() {
        return "profile";
    }

    @GetMapping("/admin/users/all")
    public String usersListGet(Model model) {
        model.addAttribute("users", userService.findAllUsers());
        return "user_list";
    }

    @GetMapping("/dev/create_user")
    public String createUserGet(Model model) {
        addFieldsLengthConstantsIn(model);
        return "registration";
    }

    @PostMapping("/dev/create_user")
    public String createUserPost(@RequestParam String username,
                                 @RequestParam String password,
                                 @RequestParam String role,
                                 Model model) {
        User user = new User(username.trim(), bCryptPasswordEncoder.encode(password.trim()));
        boolean isSavedSuccessfully = userService.saveUser(user, password, role);
        if (isSavedSuccessfully) {
            return "redirect:/admin/users/all";
        }
        model.addAllAttributes(userService.getErrors());
        model.addAllAttributes(userService.getPreviousValues());
        addFieldsLengthConstantsIn(model);
        return "registration";
    }
}
