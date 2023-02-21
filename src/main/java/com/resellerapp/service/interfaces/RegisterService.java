package com.resellerapp.service.interfaces;

import com.resellerapp.model.dto.RegisterDTO;

public interface RegisterService {

    boolean areCredentialsValid(String username, String email);

    void registerUser(RegisterDTO registerDTO);

    boolean doPasswordsMatch(String password, String confirmPassword);

    boolean usernameExists(String username);

    boolean emailExists(String email);
}
