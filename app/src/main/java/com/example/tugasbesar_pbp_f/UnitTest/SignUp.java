package com.example.tugasbesar_pbp_f.UnitTest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.tugasbesar_pbp_f.ApiClient;
import com.example.tugasbesar_pbp_f.ApiInterface;
import com.example.tugasbesar_pbp_f.Login;
import com.example.tugasbesar_pbp_f.R;
import com.example.tugasbesar_pbp_f.SettingsMode;
import com.example.tugasbesar_pbp_f.UserDAO;
import com.example.tugasbesar_pbp_f.UserResponse;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignUp extends AppCompatActivity implements SignUpView{
    FirebaseAuth mAuth;
    private TextInputEditText email,
            password, phone, country, name;
    private SharedPreferences preferences;
    private MaterialButton btn;
    private ImageButton back;
    private ProgressDialog progressDialog;
    private SignUpPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        SettingsMode.sharedPreferences = getSharedPreferences("night", 0);
        Boolean booleanValue = SettingsMode.sharedPreferences.getBoolean("night_mode", true);
        if (booleanValue) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        mAuth = FirebaseAuth.getInstance();

        btn = findViewById(R.id.btnLogin);
        back = findViewById(R.id.back);

        name = findViewById(R.id.nameInput);
        email = findViewById(R.id.mailInput);
        password = findViewById(R.id.passInput);
        country = findViewById(R.id.countryInput);
        phone = findViewById(R.id.phoneInput);
        progressDialog = new ProgressDialog(this);

        presenter = new SignUpPresenter(this, new SignUpService());

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login = new Intent(SignUp.this, Login.class);
                startActivity(login);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onLoginClicked();
                String mail = String.valueOf(email.getText());
                String pass = String.valueOf(password.getText());
                String nama = String.valueOf(name.getText());
                String telp = String.valueOf(phone.getText());
                String negara = String.valueOf(country.getText());

                String pattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if (mail.isEmpty()) {
                    Toast.makeText(SignUp.this, "Please Enter Email!", Toast.LENGTH_SHORT).show();
                } else if (pass.isEmpty()) {
                    Toast.makeText(SignUp.this, "Please Enter Password!", Toast.LENGTH_SHORT).show();
                } else if (pass.length() < 8) {
                    Toast.makeText(SignUp.this, "Password too short!", Toast.LENGTH_SHORT).show();
                } else if (!mail.matches(pattern)) {
                    Toast.makeText(SignUp.this, "Email Invalid!", Toast.LENGTH_SHORT).show();
                } else if (nama.isEmpty()) {
                    Toast.makeText(SignUp.this, "Please Enter Your Name!", Toast.LENGTH_SHORT).show();
                }
                else if (telp.length() < 12) {
                    Toast.makeText(SignUp.this, "Phone too short!", Toast.LENGTH_SHORT).show();
                }
                else if (negara.isEmpty()) {
                    Toast.makeText(SignUp.this, "Please Enter Country!", Toast.LENGTH_SHORT).show();
                } else {
                   progressDialog.show();
                   requestSignUp();
                }
            }
        });
    }

    private void requestSignUp() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<UserResponse> register = apiService.register(name.getText().toString(), phone.getText().toString(),
                email.getText().toString(), password.getText().toString(),
                country.getText().toString());

        progressDialog.dismiss();
        register.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {

                if(response.isSuccessful()){
                    Toast.makeText(SignUp.this, response.body().getMessage(),Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignUp.this, Login.class);
                    startActivity(intent);
                    finish();
                }else{
                    String messege = "An error occured! Please Try again!";
                    Toast.makeText(SignUp.this, messege,Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(SignUp.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }

    @Override
    public String getEmail() {
        return email.getText().toString();
    }

    @Override
    public String getPassword() {
        return password.getText().toString();
    }

    @Override
    public String getPhone() {
        return phone.getText().toString();
    }

    @Override
    public String getCountry() {
        return country.getText().toString();
    }

    @Override
    public String getName() {
        return name.getText().toString();
    }

    @Override
    public void showEmailError(String message) {
        email.setError(message);
    }

    @Override
    public void showEmailInvalid(String message) {
        email.setError(message);
    }

    @Override
    public void showPasswordError(String message) {
        password.setError(message);
    }

    @Override
    public void showPhoneLess(String message) {
        phone.setError(message);
    }

    @Override
    public void showCountryError(String message) {
        country.setError(message);
    }

    @Override
    public void showNameError(String message) {
        name.setError(message);
    }

    @Override
    public void startAdminActivity() {
        new ActivityUtil(this).startAdminActivity();
    }

    @Override
    public void startMainActivity(UserDAO user) {
        new ActivityUtil(this).startMainActivity(user);
    }

    @Override
    public void showSignUpError(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorResponse(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}