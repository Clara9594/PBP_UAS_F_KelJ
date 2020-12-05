package com.example.tugasbesar_pbp_f;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "userLogin";
    private static final String KEY_ID = "id";
    private TextInputEditText edtEmail,edtPassword;
//    private MaterialButton btnSignUp;
    private MaterialButton btnLogin;
    private ProgressDialog progressDialog;

//    FirebaseAuth mFirebaseAuth;
//    private FirebaseAuth.AuthStateListener mAuthStateListener;
//    static SharedPreferences statusLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        setContentView(R.layout.activity_login);
//        SettingsMode.sharedPreferences = getSharedPreferences("night",0);
//        Boolean booleanValue = SettingsMode.sharedPreferences.getBoolean("night_mode",true);
//        if(booleanValue){
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//        }
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//        }
//
//        mFirebaseAuth = FirebaseAuth.getInstance();
        edtEmail = findViewById(R.id.emailInput);
        edtPassword = findViewById(R.id.passwordInput);
//        btnSignUp = findViewById(R.id.btnSignUp);
        btnLogin = findViewById(R.id.btnLogin);
        progressDialog = new ProgressDialog(this);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        String id = sharedPreferences.getString(KEY_ID, null);

        if (id != null) {
            if (id.equals("0")) {
                Intent login = new Intent(Login.this, AdminActivity.class);
                startActivity(login);
                finish();
            } else {
                Intent login = new Intent(Login.this, MainActivity.class);
                startActivity(login);
                finish();
            }
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtEmail.getText().toString().isEmpty())
                {
                    edtEmail.setError("Email tidak ditemukan");
                    edtEmail.requestFocus();
                }
                else if (edtPassword.getText().toString().isEmpty())
                {
                    edtPassword.setError("Password salah");
                    edtPassword.requestFocus();
                }
                else {
                    progressDialog.show();
                    requestLogin();
                }
            }
        });
    }

    private void requestLogin() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<UserResponse> log = apiService.login(edtEmail.getText().toString(), edtPassword.getText().toString());

        log.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if(response.body()!=null){
                    Toast.makeText(Login.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                        if (response.body().getUsers().getStatus().equals("admin")){
                            Intent i = new Intent(Login.this, AdminActivity.class);
                            startActivity(i);
                            finish();
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString(KEY_ID, response.body().getUsers().getId());
                            editor.apply();
                        }else {
                            Intent i = new Intent(Login.this, MainActivity.class);
                            startActivity(i);
                            finish();
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString(KEY_ID, response.body().getUsers().getId());
                            editor.apply();
                        }
                    }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(Login.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }

//        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
//                if(mFirebaseUser!=null){
//                    //Toast.makeText(MainActivity.this,"You are logged in!",Toast.LENGTH_SHORT).show();
//                    //Intent i = new Intent(MainActivity.this,HomeActivity.class);
//                    //startActivity(i);
//                }
//                else{
//                    //Toast.makeText(MainActivity.this, "Please Login", Toast.LENGTH_SHORT).show();
//                }
//            }
//        };
//        btnSignIn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String email = edtEmail.getText().toString();
//                String password = edtPassword.getText().toString();
//                if (email.isEmpty()) {
//                    Toast.makeText(Login.this, "Please enter your email!", Toast.LENGTH_SHORT).show();
//                } else if (password.isEmpty()) {
//                    Toast.makeText(Login.this, "Please enter your password", Toast.LENGTH_SHORT).show();
//                }
//
//
//                else if(!(email.isEmpty() && password.isEmpty())){
//                    mFirebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            if(!task.isSuccessful()){
//                                Toast.makeText(Login.this, "Log In Error, please try again!", Toast.LENGTH_SHORT).show();
//                            }
//                            else{
//                                try {
//                                    SharedPreferences.Editor editor = statusLogin.edit();
//                                    editor.putBoolean("status_login", true);
//                                    editor.commit();
//                                    Toast.makeText(Login.this, "Log In Successfully", Toast.LENGTH_SHORT).show();
//                                    Intent intToHome = new Intent(Login.this, MainActivity.class);
//                                    startActivity(intToHome);
//                                }catch (Exception E)
//                                {
//                                    System.out.println("peyebab probably: " +E.getLocalizedMessage());
//                                }
//                            }
//                        }
//                    });
//                }
//                else{
//                    Toast.makeText(Login.this, "Error Occurred", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

    }
//    @Override
//    protected void onStart() {
//        super.onStart();
//        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
//    }
