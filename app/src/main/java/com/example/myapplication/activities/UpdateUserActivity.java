package com.example.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.api.ApiServiceUser;
import com.example.myapplication.model.User;
import com.example.myapplication.model.Xe;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateUserActivity extends AppCompatActivity {
    private EditText inputUpdateHoTen, inputUpdateNgayThangNS, inputUpdateDiaChi, inputUpdateSDT, inputUpdateEmail;
    private Button buttonUpdateUser, buttonBackInFragUser;
    private Calendar calendar;
    private int uID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);
        anhXa();

        calendar = Calendar.getInstance();
        inputUpdateNgayThangNS.setOnClickListener(view -> showDatePickerDialog());

        User userToUpdate = (User) getIntent().getExtras().get("userToUpdate");

        if (userToUpdate != null){
            uID = userToUpdate.getuID();
            inputUpdateHoTen.setText(userToUpdate.getHoTen());
            inputUpdateNgayThangNS.setText(userToUpdate.getNgayThangNamSinh());
            inputUpdateDiaChi.setText(userToUpdate.getDiaChi());
            inputUpdateSDT.setText(userToUpdate.getSdt());
            inputUpdateEmail.setText(userToUpdate.getEmail());
        }
        buttonBackInFragUser.setOnClickListener(view -> onBackPressed());

        buttonUpdateUser.setOnClickListener(view -> {
            if (inputUpdateHoTen.getText().toString().trim().isEmpty()){
                Toast.makeText(UpdateUserActivity.this, "Nhập họ tên", Toast.LENGTH_SHORT).show();
            } else if (inputUpdateNgayThangNS.getText().toString().trim().isEmpty()) {
                Toast.makeText(UpdateUserActivity.this, "Nhập ngày tháng năm sinh", Toast.LENGTH_SHORT).show();
            } else if (inputUpdateDiaChi.getText().toString().trim().isEmpty()) {
                Toast.makeText(UpdateUserActivity.this, "Nhập địa chỉ", Toast.LENGTH_SHORT).show();
            } else if (inputUpdateSDT.getText().toString().trim().isEmpty()) {
                Toast.makeText(UpdateUserActivity.this, "Nhập số điện thoại", Toast.LENGTH_SHORT).show();
            }else if (inputUpdateEmail.getText().toString().trim().isEmpty()) {
                Toast.makeText(UpdateUserActivity.this, "Nhập email", Toast.LENGTH_SHORT).show();
            } else if (!Patterns.EMAIL_ADDRESS.matcher(inputUpdateEmail.getText().toString()).matches()) {
                Toast.makeText(UpdateUserActivity.this, "Nhập đúng định dạng email", Toast.LENGTH_SHORT).show();
            } else {

                String hoTen = inputUpdateHoTen.getText().toString().trim();
                String ngayThangNamSinh = inputUpdateNgayThangNS.getText().toString().trim();
                String diaChi = inputUpdateDiaChi.getText().toString().trim();
                String sdt = inputUpdateSDT.getText().toString().trim();
                String email = inputUpdateEmail.getText().toString().trim();


                ApiServiceUser.apiServiceUser.updateUser(uID, hoTen, ngayThangNamSinh, diaChi, sdt, email)
                        .enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                if (response.body().equals("thanhcong")){
                                    Toast.makeText(UpdateUserActivity.this, "Cập nhật đối tượng người dùng thành công", Toast.LENGTH_SHORT).show();
                                    onBackPressed();
                                }
                                else{
                                    Toast.makeText(UpdateUserActivity.this, "Cập nhật không thành công!", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                                Toast.makeText(UpdateUserActivity.this, "lỗi khi cập nhật đối tượng", Toast.LENGTH_SHORT).show();
                                Log.d("errorUpdateUser", t.toString());
                            }
                        });
            }
        });
    }

    private void anhXa() {
        inputUpdateHoTen = findViewById(R.id.inputUpdateHoTen);
        inputUpdateNgayThangNS = findViewById(R.id.inputUpdateNgayThangNS);
        inputUpdateDiaChi = findViewById(R.id.inputUpdateDiaChi);
        inputUpdateSDT = findViewById(R.id.inputUpdateSDT);
        inputUpdateEmail = findViewById(R.id.inputUpdateEmail);
        buttonUpdateUser = findViewById(R.id.buttonUpdateUser);
        buttonBackInFragUser = findViewById(R.id.buttonBackInFragUser);
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
                    inputUpdateNgayThangNS.setText(selectedDate);
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }
}