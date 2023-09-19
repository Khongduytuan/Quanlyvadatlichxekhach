package com.example.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.adapter.LichDatAdminAdapter;
import com.example.myapplication.adapter.LichDatClientAdapter;
import com.example.myapplication.adapter.XeAdapter;
import com.example.myapplication.api.ApiServiceLichDat;
import com.example.myapplication.model.LichDat;
import com.example.myapplication.model.Xe;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConFirmLichDatAdminActivity extends AppCompatActivity implements LichDatAdminAdapter.ClickDeleteLichDat, LichDatAdminAdapter.ClickConfirmLichDat{
    private RecyclerView recycleViewConfirmLichDat;
    private LichDatAdminAdapter lichDatAdminAdapter;
    private List<LichDat> mList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_con_firm_lich_dat_admin);

        recycleViewConfirmLichDat = findViewById(R.id.recycleViewConfirmLichDat);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recycleViewConfirmLichDat.setLayoutManager(linearLayoutManager);

        mList = new ArrayList<>();

        lichDatAdminAdapter = new LichDatAdminAdapter(ConFirmLichDatAdminActivity.this, this, this);
        recycleViewConfirmLichDat.setAdapter(lichDatAdminAdapter);



        GetDataLichDaDat();
    }

    private void GetDataLichDaDat() {
        ApiServiceLichDat.apiServiceLichDat.getDataLichDatFullForAdmin()
                .enqueue(new Callback<List<LichDat>>() {
                    @Override
                    public void onResponse(Call<List<LichDat>> call, Response<List<LichDat>> response) {
                        mList = response.body();
                        lichDatAdminAdapter.setData(mList);
                    }

                    @Override
                    public void onFailure(Call<List<LichDat>> call, Throwable t) {
                        Toast.makeText(ConFirmLichDatAdminActivity.this, "Lỗi kết nối cơ sở dữ liệu lịch đặt", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void deleteLichDatAdmin(int position) {
        LichDat lichDatToDelete = mList.get(position);
        int ldIDToDelete = lichDatToDelete.getLdID();
        ApiServiceLichDat.apiServiceLichDat.deleteDataLichDat(ldIDToDelete)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.body().equals("thanhcong")){
                            Toast.makeText(ConFirmLichDatAdminActivity.this, "Xóa thành công lịch đặt", Toast.LENGTH_SHORT).show();
                            mList.remove(position);
                            lichDatAdminAdapter.notifyItemRemoved(position);
                        }
                        else{
                            Toast.makeText(ConFirmLichDatAdminActivity.this, "Lỗi xóa lịch đặt", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(ConFirmLichDatAdminActivity.this, "Lỗi với cơ sở dữ liệu lịch đặt", Toast.LENGTH_SHORT).show();
                        Log.d("errorDeleteLichDat", t.toString());
                    }
                });

    }

    @Override
    public void confirmLichDatAdmin(int position) {
        LichDat lichDat = mList.get(position);
        int ldIDToConfirm = lichDat.getLdID();

        ApiServiceLichDat.apiServiceLichDat.confirmLichDatForAdmin(ldIDToConfirm, "1")
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.body().equals("thanhcong")){
                            Toast.makeText(ConFirmLichDatAdminActivity.this, "Xác nhận thành công", Toast.LENGTH_SHORT).show();
                            GetDataLichDaDat();
                        }else {
                            Toast.makeText(ConFirmLichDatAdminActivity.this, "Xác nhận không thành công!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(ConFirmLichDatAdminActivity.this, "Lỗi khi xác nhận trên cơ sở dữ liệu Lịch đặt!", Toast.LENGTH_SHORT).show();
                        Log.d("errorConfirmLichDat", t.toString());
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        GetDataLichDaDat();
    }
}