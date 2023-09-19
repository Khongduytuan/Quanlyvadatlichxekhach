package com.example.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.api.ApiServiceGetDataXe;
import com.example.myapplication.api.ApiServiceLoTrinh;
import com.example.myapplication.model.Xe;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddARouteActivity extends AppCompatActivity {
    private List<String> mList = new ArrayList<>();
    private EditText editTextAddDiemDau, editTextAddDiemCuoi,
            editTextAddThoiGianXP, editTextAddThoiGianTN, editTextTenXeInAddLoTrinh;
    private Button buttonAddLoTrinh, buttonBackInAddLoTrinh;
    private Spinner spinnerLoTrinh;

    private ArrayAdapter<String> adapter;
    private Calendar calendar;
    private int ltID, xID;
    private String tenXe;

    private static final SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_aroute);
        anhXa();
        GetDataXe();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLoTrinh.setAdapter(adapter);

        spinnerLoTrinh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tenXe = mList.get(i);
                editTextTenXeInAddLoTrinh.setText(tenXe);
                ApiServiceGetDataXe.apiServiceGetDataXe.getTenXeByxID(tenXe)
                        .enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                xID = Integer.parseInt(response.body());
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                                Toast.makeText(AddARouteActivity.this, "Lỗi trong quá trình xác thực!", Toast.LENGTH_SHORT).show();

                            }
                        });

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        calendar = Calendar.getInstance();
        editTextAddThoiGianXP.setOnClickListener(view -> showDateTimePicker());

        editTextAddThoiGianTN.setOnClickListener(view -> showDateTimePicker2());


        buttonBackInAddLoTrinh.setOnClickListener(view -> onBackPressed());

        buttonAddLoTrinh.setOnClickListener(view -> {
            if (editTextAddDiemDau.getText().toString().trim().isEmpty()){
                Toast.makeText(AddARouteActivity.this, "Nhập điểm xuất phát", Toast.LENGTH_SHORT).show();
            } else if (editTextAddDiemCuoi.getText().toString().trim().isEmpty()) {
                Toast.makeText(AddARouteActivity.this, "Nhập điểm cuối của lộ trình", Toast.LENGTH_SHORT).show();
            }else {
                String diemDau = editTextAddDiemDau.getText().toString().trim();
                String diemCuoi = editTextAddDiemCuoi.getText().toString().trim();
                String thoiGianXuatPhat = editTextAddThoiGianXP.getText().toString().trim();
                String thoiGianToiNoi = editTextAddThoiGianTN.getText().toString().trim();

                ApiServiceLoTrinh.apiServiceLoTrinh.insertDataLoTrinh(diemDau,
                                diemCuoi, thoiGianXuatPhat, thoiGianToiNoi, xID)
                        .enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                if (response.body().equals("thanhcong")) {
                                    Toast.makeText(AddARouteActivity.this, "Thêm đối tượng lộ trình thành công!!!", Toast.LENGTH_SHORT).show();
                                    onBackPressed();
                                } else {
                                    Toast.makeText(AddARouteActivity.this, "Không thành công!!!", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                                Toast.makeText(AddARouteActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                                Log.d("errorAddLoTrinh", t.toString());
                            }
                        });

            }
        });


    }

    private void anhXa() {
        editTextAddDiemDau = findViewById(R.id.editTextAddDiemDau);
        editTextAddDiemCuoi = findViewById(R.id.editTextAddDiemCuoi);
        editTextAddThoiGianXP = findViewById(R.id.editTextAddThoiGianXP);
        editTextAddThoiGianTN = findViewById(R.id.editTextAddThoiGianTN);
        editTextTenXeInAddLoTrinh = findViewById(R.id.editTextTenXeInAddLoTrinh);
        buttonAddLoTrinh = findViewById(R.id.buttonAddLoTrinh);
        buttonBackInAddLoTrinh = findViewById(R.id.buttonBackInAddLoTrinh);
        spinnerLoTrinh = findViewById(R.id.spinnerLoTrinh);
    }


    public void GetDataXe(){
        ApiServiceGetDataXe.apiServiceGetDataXe.getDataXe().enqueue(new Callback<List<Xe>>() {
            @Override
            public void onResponse(Call<List<Xe>> call, Response<List<Xe>> response) {
//                Toast.makeText(AddARouteActivity.this, "Call Api successfully", Toast.LENGTH_SHORT).show();
                List<Xe> list = response.body();
                for (Xe xe: list){
                    mList.add(xe.getTenXe());
                    Log.d("xxx", mList.get(0));
                    spinnerLoTrinh.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<List<Xe>> call, Throwable t) {
                Toast.makeText(AddARouteActivity.this, "Call api error: " + t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("errorCallApi", t.toString());
            }
        });
    }



    private void showDateTimePicker() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, year, monthOfYear, dayOfMonth) -> {
                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, monthOfYear);
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                    showTimePicker1();
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );

        datePickerDialog.show();
    }

    private void showTimePicker1() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                (view, hourOfDay, minute) -> {
                    calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    calendar.set(Calendar.MINUTE, minute);

                    updateDateTimeEditTextXP();
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
        );

        timePickerDialog.show();
    }

    private void updateDateTimeEditTextXP() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        editTextAddThoiGianXP.setText(sdf.format(calendar.getTime()));
    }


    private void showDateTimePicker2() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, year, monthOfYear, dayOfMonth) -> {
                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, monthOfYear);
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                    showTimePicker2();
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );

        datePickerDialog.show();
    }

    private void showTimePicker2() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                (view, hourOfDay, minute) -> {
                    calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    calendar.set(Calendar.MINUTE, minute);

                    updateDateTimeEditTextTN();
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
        );

        timePickerDialog.show();
    }

    private void updateDateTimeEditTextTN() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        editTextAddThoiGianTN.setText(sdf.format(calendar.getTime()));
    }


}