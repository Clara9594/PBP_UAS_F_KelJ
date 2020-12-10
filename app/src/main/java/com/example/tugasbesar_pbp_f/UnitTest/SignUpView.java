package com.example.tugasbesar_pbp_f.UnitTest;

import com.example.tugasbesar_pbp_f.UserDAO;

public interface SignUpView {
    //String id, String name, String phone,
    // String email, String password, String country,
    // String status, String url
    String getName();
    String getPhone();
    String getEmail();
    String getPassword();
    String getCountry();
    void showNameError(String message);
    void showPhoneLess(String message);
    void showEmailError(String message);
    void showEmailInvalid(String message);
    void showPasswordError(String message);
    void showCountryError(String message);

    void startAdminActivity();
    void startMainActivity(UserDAO user);
    void showSignUpError(String message);
    void showErrorResponse(String message);
}
