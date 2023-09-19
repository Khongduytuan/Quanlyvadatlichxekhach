package com.example.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.api.ApiServiceUser;
import com.google.android.material.button.MaterialButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    private EditText inputDKHoTen, inputDKNgayThangNS, inputDKDiaChi,
            inputDKSDT, inputDKEmail, inputDKMatKhau, inputDKXacNhanMatKhau, inputDKUsername;

    private ImageView imageBack;
    private TextView textSignIn;
    private MaterialButton buttonSignUp;

    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        anhXa();
        imageBack.setOnClickListener(view -> onBackPressed());
        textSignIn.setOnClickListener(view -> onBackPressed());

        calendar = Calendar.getInstance();
        inputDKNgayThangNS.setOnClickListener(view -> showDatePickerDialog());


        buttonSignUp.setOnClickListener(view -> {
            if (inputDKHoTen.getText().toString().trim().isEmpty()){
                Toast.makeText(SignUpActivity.this, "Nhập họ tên", Toast.LENGTH_SHORT).show();
            } else if (inputDKNgayThangNS.getText().toString().trim().isEmpty()) {
                Toast.makeText(SignUpActivity.this, "Nhập ngày tháng năm sinh", Toast.LENGTH_SHORT).show();
            } else if (inputDKDiaChi.getText().toString().trim().isEmpty()) {
                Toast.makeText(SignUpActivity.this, "Nhập địa chỉ", Toast.LENGTH_SHORT).show();
            } else if (inputDKSDT.getText().toString().trim().isEmpty()) {
                Toast.makeText(SignUpActivity.this, "Nhập số điện thoại", Toast.LENGTH_SHORT).show();
            }else if (inputDKEmail.getText().toString().trim().isEmpty()) {
                Toast.makeText(SignUpActivity.this, "Nhập email", Toast.LENGTH_SHORT).show();
            } else if (!Patterns.EMAIL_ADDRESS.matcher(inputDKEmail.getText().toString()).matches()) {
                Toast.makeText(SignUpActivity.this, "Nhập đúng định dạng email", Toast.LENGTH_SHORT).show();
            } else if (inputDKMatKhau.getText().toString().trim().isEmpty()) {
                Toast.makeText(SignUpActivity.this, "Nhập mật khẩu", Toast.LENGTH_SHORT).show();
            } else if (inputDKXacNhanMatKhau.getText().toString().trim().isEmpty()) {
                Toast.makeText(SignUpActivity.this, "Xác nhận lại mật khẩu", Toast.LENGTH_SHORT).show();
            } else if (!inputDKMatKhau.getText().toString().trim()
                    .equals(inputDKXacNhanMatKhau.getText().toString().trim())) {
                Toast.makeText(SignUpActivity.this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
            } else if (inputDKUsername.getText().toString().trim().isEmpty()) {
                Toast.makeText(SignUpActivity.this, "Nhập tên tài khoản", Toast.LENGTH_SHORT).show();
            } else{
                String hoTen = inputDKHoTen.getText().toString().trim();
                String ngayThangNamSinh = inputDKNgayThangNS.getText().toString().trim();
                String diaChi = inputDKDiaChi.getText().toString().trim();
                String sdt = inputDKSDT.getText().toString().trim();
                String email = inputDKEmail.getText().toString().trim();
                String userName = inputDKUsername.getText().toString().trim();
                String passWord = inputDKMatKhau.getText().toString().trim();
                String role = "0";
                ApiServiceUser.apiServiceUser.dangKyAcc(hoTen, ngayThangNamSinh, diaChi, sdt, email, userName, passWord, role)
                        .enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                if (response.body().equals("thanhcong")){
                                    Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                                    Toast.makeText(SignUpActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                                    intent.putExtra("userName", userName);
                                    intent.putExtra("passWord", passWord);
                                    startActivity(intent);
                                    finish();
                                }else {
                                    Toast.makeText(SignUpActivity.this, "Đăng ký không thành công!", Toast.LENGTH_SHORT).show();
                                    inputDKMatKhau.setText("");
                                    inputDKXacNhanMatKhau.setText("");
                                }
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                                Toast.makeText(SignUpActivity.this, "Lỗi đăng ký", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

    }

    private void anhXa() {
        inputDKHoTen = findViewById(R.id.inputDKHoTen);
        inputDKNgayThangNS = findViewById(R.id.inputDKNgayThangNS);
        inputDKDiaChi = findViewById(R.id.inputDKDiaChi);
        inputDKSDT = findViewById(R.id.inputDKSDT);
        inputDKEmail = findViewById(R.id.inputDKEmail);
        inputDKMatKhau = findViewById(R.id.inputDKMatKhau);
        inputDKXacNhanMatKhau = findViewById(R.id.inputDKXacNhanMatKhau);
        imageBack = findViewById(R.id.imageBack);
        textSignIn = findViewById(R.id.textSignIn);
        buttonSignUp = findViewById(R.id.buttonSignUp);
        inputDKUsername = findViewById(R.id.inputDKUsername);
    }

    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, year, month, dayOfMonth) -> {
                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, month);
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
                    String selectedDate = sdf.format(calendar.getTime());
                    inputDKNgayThangNS.setText(selectedDate);
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }
}
