package com.example.tugasbesar_pbp_f.UnitTest;

import com.example.tugasbesar_pbp_f.ApiClient;
import com.example.tugasbesar_pbp_f.ApiInterface;
import com.example.tugasbesar_pbp_f.UserDAO;
import com.example.tugasbesar_pbp_f.UserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;

public class SignUpService {
    public void login(final SignUpView view, String name, String phone,
                      String email, String password, String country, final SignUpCallback callback){

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<UserResponse> userDAOCall = apiService.register(name,phone,email,password,country);

        userDAOCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call,
                                   Response<UserResponse> response) {
                if(response.body().getMessage().equalsIgnoreCase("berhasil login" )){
                    callback.onSuccess(true, response.body().getUsers());
                } else{
                    callback.onError();
                    view.showSignUpError(response.body().getMessage());
                }
            }
            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                view.showErrorResponse(t.getMessage());
                callback.onError();
            }
        });
    }
    public Boolean getValid(final SignUpView view, String name, String phone,
                            String email, String password, String country){
        final Boolean[] bool = new Boolean[1];
        login(view,name,phone,email,password,country, new SignUpCallback() {
            @Override public void onSuccess(boolean value, UserDAO user) {
                bool[0] = true;
            }
            @Override
            public void onError() {
                bool[0] = false;
            }
        });
        return bool[0];
    }
}
