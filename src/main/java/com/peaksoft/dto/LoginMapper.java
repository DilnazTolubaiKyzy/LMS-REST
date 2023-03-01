package com.peaksoft.dto;

import com.peaksoft.entity.User;
import org.springframework.stereotype.Component;

@Component
public class LoginMapper {
    public LoginResponse view(String token, String message, User user){
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(token);
        loginResponse.setMessage(message);
        if (user != null){
            loginResponse.setAuthority(user.getRole().getAuthority());
        }
        return loginResponse;
    }
}
