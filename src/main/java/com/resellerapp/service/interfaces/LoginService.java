package com.resellerapp.service.interfaces;

public interface LoginService {

    boolean areCredentialsValid(String username, String password);

    void loginUser(String username);

    void logout();
}
