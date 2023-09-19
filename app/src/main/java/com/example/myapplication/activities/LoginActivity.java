package com.example.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.api.ApiServiceUser;
import com.example.myapplication.model.User;
import com.example.myapplication.sharedPreferences.SharedPreferencesManager;
import com.google.android.material.button.MaterialButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText editTextUserName, editTextPassWord;
    private MaterialButton buttonDangNhap;
    private TextView textSignUp;


    public SharedPreferencesManager sharedPreferencesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        anhXa();

        sharedPreferencesManager = SharedPreferencesManager.getInstance(this);


        Intent intent = getIntent();
        if (intent != null){
            String userName = intent.getStringExtra("userName");
            String passWord = intent.getStringExtra("passWord");
            editTextUserName.setText(userName);
            editTextPassWord.setText(passWord);

        }

        textSignUp.setOnClickListener(view -> {
            Intent intent12 = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent12);
        });


        buttonDangNhap.setOnClickListener(view -> {
            if (editTextUserName.getText().toString().trim().isEmpty()){
                Toast.makeText(LoginActivity.this, "Nhập tài khoản", Toast.LENGTH_SHORT).show();
            } else if (editTextPassWord.getText().toString().trim().isEmpty()) {
                Toast.makeText(LoginActivity.this, "Nhập mật khẩu", Toast.LENGTH_SHORT).show();
            }else {
                String userName = editTextUserName.getText().toString().trim();
                String passWord = editTextPassWord.getText().toString().trim();
                ApiServiceUser.apiServiceUser.loginUser(userName, passWord)
                        .enqueue(new Callback<List<User>>() {
                            @Override
                            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                                List<User> mList = response.body();
                                if (mList.size() > 0){
                                    if (mList.get(0).getRole().equals("1")){
                                        Intent intent1 = new Intent(LoginActivity.this, MainActivity.class);
                                        startActivity(intent1);
                                        finish();
                                    }
                                    else{
                                        // lưu dữ liệu người dùng vào SharedPreferences
                                        User userInfor = mList.get(0);
                                        sharedPreferencesManager.saveUserData(userInfor);
                                        Log.d("SharedPreferences", sharedPreferencesManager.sharedPreferencesGetHoTen());

                                        Intent intent2 = new Intent(LoginActivity.this, UserActivity.class);
                                        startActivity(intent2);
                                        finish();
                                    }

                                }
                            }

                            @Override
                            public void onFailure(Call<List<User>> call, Throwable t) {
                                Toast.makeText(LoginActivity.this, "Lỗi đăng nhập", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

    private void anhXa() {
        editTextUserName = findViewById(R.id.editTextUserName);
        editTextPassWord = findViewById(R.id.editTextPassWord);
        buttonDangNhap = findViewById(R.id.buttonDangNhap);
        textSignUp = findViewById(R.id.textSignUp);
    }
}