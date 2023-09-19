package com.example.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.api.ApiServiceGetDataXe;
import com.example.myapplication.model.Xe;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateActivity extends AppCompatActivity {
    private EditText editTextIDXe, editTextTenXe, editTextBienSoXe, editTextSoGheXe;
    private Button buttonUpdateXe, buttonBackInFragXe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        anhXa();

        Xe xeToUpdate = (Xe) getIntent().getExtras().get("xeToUpdate");

        if (xeToUpdate != null){
            Log.d("XeUpdate", xeToUpdate.getTenXe());
            editTextIDXe.setText(String.valueOf(xeToUpdate.getxID()));
            editTextTenXe.setText(xeToUpdate.getTenXe());
            editTextBienSoXe.setText(xeToUpdate.getBienSo());
            editTextSoGheXe.setText(String.valueOf(xeToUpdate.getSoGhe()));
        }

        buttonUpdateXe.setOnClickListener(view -> {
            if(editTextTenXe.getText().toString().trim().isEmpty()){
                Toast.makeText(UpdateActivity.this, "Nhập Tên Xe", Toast.LENGTH_SHORT).show();
            } else if (editTextBienSoXe.getText().toString().trim().isEmpty()) {
                Toast.makeText(UpdateActivity.this, "Nhập Biển Số Xe", Toast.LENGTH_SHORT).show();
            }else if (editTextSoGheXe.getText().toString().trim().isEmpty()) {
                Toast.makeText(UpdateActivity.this, "Nhập Số Ghế", Toast.LENGTH_SHORT).show();
            }else {
                int xID = Integer.parseInt(editTextIDXe.getText().toString().trim());
                String tenXe = editTextTenXe.getText().toString().trim();
                String bienSo = editTextBienSoXe.getText().toString().trim();
                int soGhe = Integer.parseInt(editTextSoGheXe.getText().toString().trim());

                if (xeToUpdate.getTenXe().equals(tenXe)
                        && xeToUpdate.getBienSo().equals(bienSo)
                        && String.valueOf(xeToUpdate.getSoGhe()).equals(String.valueOf(soGhe))){
                    Toast.makeText(UpdateActivity.this, "Đã không có sự thay đổi nào!", Toast.LENGTH_SHORT).show();
                }else{
                    ApiServiceGetDataXe.apiServiceGetDataXe.updateDataXe(xID, tenXe, bienSo, soGhe)
                            .enqueue(new Callback<String>() {
                                @Override
                                public void onResponse(Call<String> call, Response<String> response) {
                                    if (response.body().trim().equals("thanhcong")){
                                        Toast.makeText(UpdateActivity.this, "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
                                        onBackPressed();
                                    }
                                    else {
                                        Toast.makeText(UpdateActivity.this, "Lỗi update", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<String> call, Throwable t) {

                                }
                            });
                }
            }
        });

        buttonBackInFragXe.setOnClickListener(view -> onBackPressed());

    }

    private void anhXa() {
        editTextIDXe = findViewById(R.id.editTextIDXe);
        editTextTenXe = findViewById(R.id.editTextTenXe);
        editTextBienSoXe = findViewById(R.id.editTextBienSoXe);
        editTextSoGheXe = findViewById(R.id.editTextSoGheXe);
        buttonUpdateXe = findViewById(R.id.buttonUpdateXe);
        buttonBackInFragXe = findViewById(R.id.buttonBackInFragXe);
    }
}