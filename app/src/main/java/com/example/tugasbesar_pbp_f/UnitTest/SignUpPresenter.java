package com.example.tugasbesar_pbp_f.UnitTest;

import android.widget.Toast;

import com.example.tugasbesar_pbp_f.UserDAO;

public class SignUpPresenter {
    private SignUpView view;
    private SignUpService service;
    private SignUpCallback callback;
    public SignUpPresenter(SignUpView view, SignUpService service) {
        this.view = view;
        this.service = service;
    }
    public void onLoginClicked() {
        String pattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (view.getName().isEmpty()) {
            view.showNameError("Please Enter Name");
            return;
        } else if (view.getEmail().isEmpty()) {
            view.showEmailError("Please Enter Email!");
            return;
        } else if (view.getPassword().isEmpty()) {
            view.showPasswordError("Please Enter Password!");
            return;
        } else if (view.getPassword().length() < 8) {
            view.showPasswordError("Password too short!");
            return;
        } else if (view.getEmail().matches(pattern)) {
            view.showEmailInvalid("Email Invalid!");
            return;
        } else if (view.getPhone().length() < 12) {
            view.showPhoneLess("Phone too short!");
            return;
        } else if (view.getPhone().isEmpty()) {
            view.showPhoneError("Please Enter Telp");
            return;
        } else if (view.getCountry().isEmpty()) {
            view.showCountryError("Please Enter Country!");
            return;
        }else {
            service.login(view, view.getEmail(), view.getPassword(), view.getPhone(), view.getCountry(), view.getName(),
                    new SignUpCallback() {
                        @Override
                        public void onSuccess(boolean value, UserDAO user) {
                            if (user.getEmail().equalsIgnoreCase("admin.cardido@cardido.masuk.web.id")) {
                                view.startAdminActivity();
                            } else {
                                view.startMainActivity(user);
                            }
                        }

                        @Override
                        public void onError() {
                        }
                    });
            return;
        }
    }
}
