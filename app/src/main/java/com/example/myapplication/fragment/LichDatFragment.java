package com.example.myapplication.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.activities.MainActivity;
import com.example.myapplication.activities.UserActivity;
import com.example.myapplication.adapter.LoTrinhAdapter;
import com.example.myapplication.adapter.LoTrinhClientAdapter;
import com.example.myapplication.api.ApiServiceLichDat;
import com.example.myapplication.api.ApiServiceLoTrinh;
import com.example.myapplication.model.LoTrinh;
import com.example.myapplication.model.User;
import com.example.myapplication.sharedPreferences.SharedPreferencesManager;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LichDatFragment extends Fragment implements LoTrinhClientAdapter.ClickItemAddLoTrinh {


    private RecyclerView recycleViewLoTrinhInClient;
    private UserActivity mUserActivity;
    private List<LoTrinh> mList;
    private LoTrinhClientAdapter loTrinhClientAdapter;

    public SharedPreferencesManager sharedPreferencesManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lich_dat, container, false);
        mUserActivity = (UserActivity) getActivity();

        sharedPreferencesManager = SharedPreferencesManager.getInstance(mUserActivity);


        recycleViewLoTrinhInClient = view.findViewById(R.id.recycleViewLoTrinhInClient);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mUserActivity);
        recycleViewLoTrinhInClient.setLayoutManager(linearLayoutManager);


        mList = new ArrayList<>();
        loTrinhClientAdapter = new LoTrinhClientAdapter(mUserActivity, this);
        recycleViewLoTrinhInClient.setAdapter(loTrinhClientAdapter);

        getDataLoTrinh();


        return view;
    }

    public void getDataLoTrinh(){
        ApiServiceLoTrinh.apiServiceLoTrinh.getDataLoTrinh()
                .enqueue(new Callback<List<LoTrinh>>() {
                    @Override
                    public void onResponse(Call<List<LoTrinh>> call, Response<List<LoTrinh>> response) {
                        mList = response.body();
                        loTrinhClientAdapter.setData(mList);
                    }

                    @Override
                    public void onFailure(Call<List<LoTrinh>> call, Throwable t) {
                        Toast.makeText(mUserActivity, "Lỗi khi lấy dữ liệu", Toast.LENGTH_SHORT).show();
                        Log.d("errorGetDataLoTrinh", t.toString());

                    }
                });
    }
    @Override
    public void additemLoTrinh(int position) {
        // Insert 1 đối tượng Lịch Đặt(ldID-tự tăng, ltID, uID, giờ đặt-là giờ của hệ thống)
        int mLTID = mList.get(position).getLtID();
        int mUID = Integer.parseInt(sharedPreferencesManager.sharedPreferencesGetUID());

        long currentTimeMillis = System.currentTimeMillis();
        Date currentDate = new Date(currentTimeMillis);

        // Định dạng thời gian thành chuỗi
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String mThoiGianDat = sdf.format(currentDate);

        int mTinhTrangXacNhan = 0;

        ApiServiceLichDat.apiServiceLichDat.insertDataLichDat(mLTID, mUID, mThoiGianDat, mTinhTrangXacNhan)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.body().equals("thanhcong")){
                            Toast.makeText(mUserActivity, "Đặt lịch thành công", Toast.LENGTH_SHORT).show();
//                            mList.remove(position);
//                            loTrinhClientAdapter.notifyItemRemoved(position);
                        }
                        else{
                            Toast.makeText(mUserActivity, "Lỗi đặt lịch!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(mUserActivity, "Lỗi kết nối cơ sở dữ liệu", Toast.LENGTH_SHORT).show();
                        Log.d("errorInsertDataLichDat", t.toString());
                    }
                });
    }


    @Override
    public void onResume() {
        super.onResume();
        getDataLoTrinh();
    }
}