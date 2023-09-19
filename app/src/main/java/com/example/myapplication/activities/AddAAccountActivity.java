package com.example.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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

public class AddAAccountActivity extends AppCompatActivity {

    private EditText inputAddHoTen, inputAddNgayThangNS, inputAddDiaChi,
            inputAddSDT, inputAddEmail, inputAddMatKhau, inputAddXacNhanMatKhau, inputAddUsername;

    private ImageView imageAddBack;
    private MaterialButton buttonAddSignUp;

    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_aaccount);
        anhXa();

        imageAddBack.setOnClickListener(view -> onBackPressed());

        calendar = Calendar.getInstance();
        inputAddNgayThangNS.setOnClickListener(view -> showDatePickerDialog());

        buttonAddSignUp.setOnClickListener(view -> {
            if (inputAddHoTen.getText().toString().trim().isEmpty()){
                Toast.makeText(AddAAccountActivity.this, "Nhập họ tên", Toast.LENGTH_SHORT).show();
            } else if (inputAddNgayThangNS.getText().toString().trim().isEmpty()) {
                Toast.makeText(AddAAccountActivity.this, "Nhập ngày tháng năm sinh", Toast.LENGTH_SHORT).show();
            } else if (inputAddDiaChi.getText().toString().trim().isEmpty()) {
                Toast.makeText(AddAAccountActivity.this, "Nhập địa chỉ", Toast.LENGTH_SHORT).show();
            } else if (inputAddSDT.getText().toString().trim().isEmpty()) {
                Toast.makeText(AddAAccountActivity.this, "Nhập số điện thoại", Toast.LENGTH_SHORT).show();
            }else if (inputAddEmail.getText().toString().trim().isEmpty()) {
                Toast.makeText(AddAAccountActivity.this, "Nhập email", Toast.LENGTH_SHORT).show();
            } else if (!Patterns.EMAIL_ADDRESS.matcher(inputAddEmail.getText().toString()).matches()) {
                Toast.makeText(AddAAccountActivity.this, "Nhập đúng định dạng email", Toast.LENGTH_SHORT).show();
            } else if (inputAddMatKhau.getText().toString().trim().isEmpty()) {
                Toast.makeText(AddAAccountActivity.this, "Nhập mật khẩu", Toast.LENGTH_SHORT).show();
            } else if (inputAddXacNhanMatKhau.getText().toString().trim().isEmpty()) {
                Toast.makeText(AddAAccountActivity.this, "Xác nhận lại mật khẩu", Toast.LENGTH_SHORT).show();
            } else if (!inputAddMatKhau.getText().toString().trim()
                    .equals(inputAddXacNhanMatKhau.getText().toString().trim())) {
                Toast.makeText(AddAAccountActivity.this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
            } else if (inputAddUsername.getText().toString().trim().isEmpty()) {
                Toast.makeText(AddAAccountActivity.this, "Nhập tên tài khoản", Toast.LENGTH_SHORT).show();
            } else{
                String hoTen = inputAddHoTen.getText().toString().trim();
                String ngayThangNamSinh = inputAddNgayThangNS.getText().toString().trim();
                String diaChi = inputAddDiaChi.getText().toString().trim();
                String sdt = inputAddSDT.getText().toString().trim();
                String email = inputAddEmail.getText().toString().trim();
                String userName = inputAddUsername.getText().toString().trim();
                String passWord = inputAddMatKhau.getText().toString().trim();
                String role = "0";
                ApiServiceUser.apiServiceUser.dangKyAcc(hoTen, ngayThangNamSinh, diaChi, sdt, email, userName, passWord, role)
                        .enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                if (response.body().equals("thanhcong")){
                                    Intent intent = new Intent(AddAAccountActivity.this, LoginActivity.class);
                                    Toast.makeText(AddAAccountActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                                    onBackPressed();
                                }else {
                                    Toast.makeText(AddAAccountActivity.this, "Đăng ký không thành công!", Toast.LENGTH_SHORT).show();
                                    inputAddMatKhau.setText("");
                                    inputAddXacNhanMatKhau.setText("");
                                }
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                                Toast.makeText(AddAAccountActivity.this, "Lỗi đăng ký", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

    private void anhXa() {
        inputAddHoTen = findViewById(R.id.inputAddHoTen);
        inputAddNgayThangNS = findViewById(R.id.inputAddNgayThangNS);
        inputAddDiaChi = findViewById(R.id.inputAddDiaChi);
        inputAddSDT = findViewById(R.id.inputAddSDT);
        inputAddEmail = findViewById(R.id.inputAddEmail);
        inputAddMatKhau = findViewById(R.id.inputAddMatKhau);
        inputAddXacNhanMatKhau = findViewById(R.id.inputAddXacNhanMatKhau);
        imageAddBack = findViewById(R.id.imageAddBack);
        buttonAddSignUp = findViewById(R.id.buttonAddSignUp);
        inputAddUsername = findViewById(R.id.inputAddUsername);
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
                    inputAddNgayThangNS.setText(selectedDate);
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }
}