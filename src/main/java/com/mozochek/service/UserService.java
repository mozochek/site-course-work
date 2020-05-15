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

    public boolean saveUser(User user, String password, String role) {
        this.user = user;

        errors = new HashMap<>();

        validateData(password);

        if (errors.isEmpty()) {
            if (Role.valueOf(role) == Role.USER) {
                user.assignUserRole();
            } else if (Role.valueOf(role) == Role.MODERATOR) {
                user.assignModeratorRole();
            } else if (Role.valueOf(role) == Role.ADMIN) {
                user.assignAdminRole();
            } else if (Role.valueOf(role) == Role.DEVELOPER) {
                user.assignDeveloperRole();
            } else {
                user.assignUserRole();
            }
            userRepository.save(this.user);
            if (this.user.getId() == 1) {
                user.assignDeveloperRole();
                userRepository.save(this.user);
            }
            return true;
        }
        return false;
    }

    private void validateData(String password) {
        validateField(user.getUsername(), USERNAME_LENGTH, "usernameError");
        validateField(password, PASSWORD_LENGTH, "passwordError");
        if (password.length() < 5) {
            errors.put("passwordError", "Длина парола должна превышать 6 символов!");
        }
        if (userRepository.findByUsername(user.getUsername()) != null) {
            errors.put("saveError", "Пользователь с таким именем уже зарегестрирован!");
        }
    }

    public Iterable<User> findAllUsers() {
        return userRepository.findAll();
    }
}
