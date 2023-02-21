package com.resellerapp.service;

import com.resellerapp.model.entity.User;
import com.resellerapp.repository.UserRepository;
import com.resellerapp.service.interfaces.LoginService;
import com.resellerapp.util.UserLoginSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    private final UserRepository userRepository;
    private final UserLoginSession userLoginSession;
    private final PasswordEncoder passwordEncoder;

    public LoginServiceImpl(UserRepository userRepository, UserLoginSession userLoginSession, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userLoginSession = userLoginSession;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean areCredentialsValid(String username, String password) {
        User user = findUserByUsername(username);

        if (user == null) {
            return false;
        }
        return this.passwordEncoder.matches(password, user.getPassword());
    }

    @Override
    public void loginUser(String username) {
        User existingUser = this.findUserByUsername(username);
        this.userLoginSession.login(existingUser.getId(), username);
    }

    @Override
    public void logout() {
        this.userLoginSession.logout();
    }

    private User findUserByUsername(String username) {
        return this.userRepository.findByUsername(username).orElse(null);
    }
}
