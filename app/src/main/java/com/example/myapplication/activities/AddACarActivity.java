package com.example.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.api.ApiServiceGetDataXe;
import com.example.myapplication.model.Xe;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddACarActivity extends AppCompatActivity {
    private EditText editTextAddTenXe, editTextAddBienSoXe, editTextAddSoGheXe;
    private Button buttonAddXe, buttonCancelXe;
    private List<Xe> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_acar);
        anhXa();
        buttonCancelXe.setOnClickListener(view -> onBackPressed());
        buttonAddXe.setOnClickListener(view -> {
            if (editTextAddTenXe.getText().toString().trim().isEmpty()){
                Toast.makeText(AddACarActivity.this, "Nhập tên xe", Toast.LENGTH_SHORT).show();
            } else if (editTextAddBienSoXe.getText().toString().trim().isEmpty()) {
                Toast.makeText(AddACarActivity.this, "Nhập biển số xe", Toast.LENGTH_SHORT).show();
            } else if (editTextAddSoGheXe.getText().toString().trim().isEmpty()) {
                Toast.makeText(AddACarActivity.this, "Nhập số ghế", Toast.LENGTH_SHORT).show();
            } else {
                if (CheckDataBienSoXe(editTextAddBienSoXe.getText().toString().trim())){
                    String tenXe = editTextAddTenXe.getText().toString().trim();
                    String bienSo = editTextAddBienSoXe.getText().toString().trim();
                    int soGhe = Integer.parseInt(editTextAddSoGheXe.getText().toString().trim());
                    ApiServiceGetDataXe.apiServiceGetDataXe.insertDataXe(tenXe, bienSo, soGhe)
                            .enqueue(new Callback<String>() {
                                @Override
                                public void onResponse(Call<String> call, Response<String> response) {
                                    if (response.body().equals("thanhcong")){
                                        Toast.makeText(AddACarActivity.this, "Thêm thành công!", Toast.LENGTH_SHORT).show();
                                        onBackPressed();
                                    }
                                    else{
                                        Toast.makeText(AddACarActivity.this, "Đã có lỗi xảy ra trong quá trình thêm!!!", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<String> call, Throwable t) {
                                    Toast.makeText(AddACarActivity.this, "Lỗi!", Toast.LENGTH_SHORT).show();
                                    Log.d("errorAddCar", t.toString());
                                }
                            });
                }
            }
        });
    }

    private void anhXa() {
        editTextAddTenXe = findViewById(R.id.editTextAddTenXe);
        editTextAddBienSoXe = findViewById(R.id.editTextAddBienSoXe);
        editTextAddSoGheXe = findViewById(R.id.editTextAddSoGheXe);
        buttonAddXe = findViewById(R.id.buttonAddXe);
        buttonCancelXe = findViewById(R.id.buttonCancelXe);
    }

    private boolean CheckDataBienSoXe(String bienso){
        ApiServiceGetDataXe.apiServiceGetDataXe.getDataXe().enqueue(new Callback<List<Xe>>() {
            @Override
            public void onResponse(Call<List<Xe>> call, Response<List<Xe>> response) {
                mList = response.body();
            }

            @Override
            public void onFailure(Call<List<Xe>> call, Throwable t) {
                Toast.makeText(AddACarActivity.this, "Xuất hiện lỗi khi kiểm tra dữ liệu", Toast.LENGTH_SHORT).show();
                Log.d("errorCheckData", t.toString());
            }
        });

        for (Xe xe : mList) {
            if(xe.getBienSo().equals(bienso)){
                return false;
            }
        }
        return true;
    }

}