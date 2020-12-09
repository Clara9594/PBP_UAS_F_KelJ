package com.example.tugasbesar_pbp_f.UnitTest;

import com.example.tugasbesar_pbp_f.UserDAO;

public interface SignUpCallback {
    void onSuccess(boolean value, UserDAO user);
    void onError();
}
