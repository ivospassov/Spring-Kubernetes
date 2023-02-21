package com.resellerapp.service;

import com.resellerapp.model.dto.RegisterDTO;
import com.resellerapp.model.entity.User;
import com.resellerapp.repository.UserRepository;
import com.resellerapp.service.interfaces.RegisterService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public RegisterServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean areCredentialsValid(String username, String email) {

        if (usernameExists(username)) {
            return false;
        }

        return emailExists(email);
    }

    @Override
    public boolean doPasswordsMatch(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

    @Override
    public void registerUser(RegisterDTO registerDTO) {
        User user = mapRegisterDTO(registerDTO);
        this.userRepository.save(user);
    }

    @Override
    public boolean usernameExists(String username) {
        return this.checkIfUsernameExists(username);
    }

    @Override
    public boolean emailExists(String email) {
        return this.checkIfEmailExists(email);
    }

    private User mapRegisterDTO(RegisterDTO registerDTO) {
        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(this.passwordEncoder.encode(registerDTO.getPassword()));
        return user;
    }

    private boolean checkIfUsernameExists(String username) {
        return this.userRepository.findByUsername(username).isPresent();
    }

    private boolean checkIfEmailExists(String email) {
        return this.userRepository.findByEmail(email).isPresent();
    }
}
