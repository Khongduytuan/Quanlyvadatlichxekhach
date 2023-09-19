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
import com.example.myapplication.model.LoTrinh;
import com.example.myapplication.model.Xe;
import com.example.myapplication.ultity.Ultitys;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateLoTrinhActivity extends AppCompatActivity {

    private EditText editTextIDLoTrinh, editTextDiemDau, editTextDiemCuoi,
            editTextThoiGianXP, editTextThoiGianTN, editTextTenXeInUpdateLoTrinh;
    private Spinner spinnerXe;
    private Button buttonUpdateLoTrinh, buttonBackInFragLoTrinh;
    private ArrayAdapter<String> adapter;

    private List<String> mList = new ArrayList<>();
    private Calendar calendar;
    private int ltID, xID;
    private String tenXe;

    private static final SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_lo_trinh);
        anhXa();

        GetDataXe();

        LoTrinh loTrinhToUpdate = (LoTrinh) getIntent().getExtras().get("loTrinhToUpdate");
        if (loTrinhToUpdate != null){
            ltID = loTrinhToUpdate.getLtID();
            editTextIDLoTrinh.setText(String.valueOf(loTrinhToUpdate.getLtID()));
            editTextDiemDau.setText(loTrinhToUpdate.getDiemDau());
            editTextDiemCuoi.setText(loTrinhToUpdate.getDiemCuoi());
            editTextThoiGianXP.setText(loTrinhToUpdate.getThoiGianXuatPhat());
            editTextThoiGianTN.setText(loTrinhToUpdate.getThoiGianToiNoi());
        }



        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerXe.setAdapter(adapter);

        spinnerXe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tenXe = mList.get(i);
                Log.d("tenXe", tenXe);
                editTextTenXeInUpdateLoTrinh.setText(tenXe);
                ApiServiceGetDataXe.apiServiceGetDataXe.getTenXeByxID(tenXe)
                        .enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                xID = Integer.parseInt(response.body());
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                                Toast.makeText(UpdateLoTrinhActivity.this, "Lỗi trong quá trình xác thực!", Toast.LENGTH_SHORT).show();

                            }
                        });

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });





        calendar = Calendar.getInstance();
        editTextThoiGianXP.setOnClickListener(view -> showDateTimePicker());

        editTextThoiGianTN.setOnClickListener(view -> showDateTimePicker2());


        buttonBackInFragLoTrinh.setOnClickListener(view -> onBackPressed());

        buttonUpdateLoTrinh.setOnClickListener(view -> {
            if (editTextDiemDau.getText().toString().trim().isEmpty()){
                Toast.makeText(UpdateLoTrinhActivity.this, "Nhập điểm xuất phát", Toast.LENGTH_SHORT).show();
            } else if (editTextDiemCuoi.getText().toString().trim().isEmpty()) {
                Toast.makeText(UpdateLoTrinhActivity.this, "Nhập điểm cuối của lộ trình", Toast.LENGTH_SHORT).show();
            }else {
                String diemDau = editTextDiemDau.getText().toString().trim();
                String diemCuoi = editTextDiemCuoi.getText().toString().trim();
                String thoiGianXuatPhat = editTextThoiGianXP.getText().toString().trim();
                String thoiGianToiNoi = editTextThoiGianTN.getText().toString().trim();

                ApiServiceLoTrinh.apiServiceLoTrinh.updateDataLoTrinh2(ltID, diemDau,
                                diemCuoi, thoiGianXuatPhat, thoiGianToiNoi, xID)
                        .enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                if (response.body().equals("thanhcong")) {
                                    Toast.makeText(UpdateLoTrinhActivity.this, "Cập nhật đối tượng lộ trình thành công!!!", Toast.LENGTH_SHORT).show();
                                    onBackPressed();
                                } else {
                                    Toast.makeText(UpdateLoTrinhActivity.this, "Không thành công!!!", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                                Toast.makeText(UpdateLoTrinhActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                                Log.d("errorUpdateLoTrinh", t.toString());
                            }
                        });

            }
        });



    }



    private void anhXa() {
        editTextIDLoTrinh = findViewById(R.id.editTextIDLoTrinh);
        editTextDiemDau = findViewById(R.id.editTextDiemDau);
        editTextDiemCuoi = findViewById(R.id.editTextDiemCuoi);
        editTextThoiGianXP = findViewById(R.id.editTextThoiGianXP);
        editTextThoiGianTN = findViewById(R.id.editTextThoiGianTN);
        spinnerXe = findViewById(R.id.spinnerXe);
        buttonUpdateLoTrinh = findViewById(R.id.buttonUpdateLoTrinh);
        buttonBackInFragLoTrinh = findViewById(R.id.buttonBackInFragLoTrinh);
        editTextTenXeInUpdateLoTrinh = findViewById(R.id.editTextTenXeInUpdateLoTrinh);
    }

    public void GetDataXe(){
        ApiServiceGetDataXe.apiServiceGetDataXe.getDataXe().enqueue(new Callback<List<Xe>>() {
            @Override
            public void onResponse(Call<List<Xe>> call, Response<List<Xe>> response) {
//                Toast.makeText(UpdateLoTrinhActivity.this, "Call Api successfully", Toast.LENGTH_SHORT).show();
                List<Xe> list = response.body();
                for (Xe xe: list){
                    mList.add(xe.getTenXe());
                    Log.d("xxx", mList.get(0));
                    spinnerXe.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<List<Xe>> call, Throwable t) {
                Toast.makeText(UpdateLoTrinhActivity.this, "Call api error: " + t.toString(), Toast.LENGTH_SHORT).show();
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
        editTextThoiGianXP.setText(sdf.format(calendar.getTime()));
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
        editTextThoiGianTN.setText(sdf.format(calendar.getTime()));
    }
}