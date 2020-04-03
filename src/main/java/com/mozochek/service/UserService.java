package com.mozochek.service;

import com.mozochek.entity.User;
import com.mozochek.repository.UserRepository;
import com.mozochek.utils.Role;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;

import static com.mozochek.utils.LengthConstants.PASSWORD_LENGTH;
import static com.mozochek.utils.LengthConstants.USERNAME_LENGTH;

@Service
public class UserService extends AbstractService implements UserDetailsService {

    private UserRepository userRepository;

    private User user;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Пользователь не найден!");
        }
        return user;
    }

    public boolean addUser(User user) {
        this.user = user;

        errors = new HashMap<>();

        validateData();
        if (errors.isEmpty()) {
            this.user.setRole(Role.USER);
            userRepository.save(this.user);
            return true;
        }
        return false;
    }

    private void validateData() {
        validateField(user.getUsername(), USERNAME_LENGTH, "usernameError");
        validateField(user.getPassword(), PASSWORD_LENGTH, "passwordError");
        if (userRepository.findByUsername(user.getUsername()) != null) {
            errors.put("saveError", "Пользователь с таким именем уже зарегестрирован!");
        }
    }
}
