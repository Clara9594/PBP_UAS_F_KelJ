package com.example.tugasbesar_pbp_f.UnitTest;

import com.example.tugasbesar_pbp_f.UserDAO;
//private TextInputEditText email,
//        password, phone, country, name;
public interface SignUpView {
    String getEmail();
    String getPassword();
    String getPhone();
    String getCountry();
    String getName();
    void showEmailError(String message);
    void showEmailInvalid(String message);
    void showPasswordError(String message);
    void showPhoneError(String message);
    void showPhoneLess(String message);
    void showCountryError(String message);
    void showNameError(String message);
    void startAdminActivity();
    void startMainActivity(UserDAO user);
    void showSignUpError(String message);
    void showErrorResponse(String message);
}
