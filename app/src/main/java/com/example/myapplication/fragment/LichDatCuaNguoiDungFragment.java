package com.example.myapplication.fragment;

import android.annotation.SuppressLint;
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
import com.example.myapplication.adapter.LichDatClientAdapter;
import com.example.myapplication.adapter.XeAdapter;
import com.example.myapplication.api.ApiServiceLichDat;
import com.example.myapplication.model.LichDat;
import com.example.myapplication.model.Xe;
import com.example.myapplication.sharedPreferences.SharedPreferencesManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LichDatCuaNguoiDungFragment extends Fragment implements LichDatClientAdapter.ClickItemDeleteLichDatForClient {
    private RecyclerView recycleViewLichDaDat;
    private LichDatClientAdapter lichDatClientAdapter;
    private List<LichDat> mList;
    private UserActivity mUserActivity;
    public SharedPreferencesManager sharedPreferencesManager;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_update_infor_user, container, false);


        mUserActivity = (UserActivity) getActivity();
        sharedPreferencesManager = SharedPreferencesManager.getInstance(mUserActivity);

        recycleViewLichDaDat = view.findViewById(R.id.recycleViewLichDaDat);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mUserActivity);
        recycleViewLichDaDat.setLayoutManager(linearLayoutManager);

        mList = new ArrayList<>();

        lichDatClientAdapter = new LichDatClientAdapter(mUserActivity, this);
        recycleViewLichDaDat.setAdapter(lichDatClientAdapter);



        GetDataLichDaDatCuaNguoiDung();


        return view;
    }

    private void GetDataLichDaDatCuaNguoiDung() {
        int uIDInLichDaDat = Integer.parseInt(sharedPreferencesManager.sharedPreferencesGetUID());
        ApiServiceLichDat.apiServiceLichDat.getDataLichDatClient(uIDInLichDaDat)
                .enqueue(new Callback<List<LichDat>>() {
                    @Override
                    public void onResponse(Call<List<LichDat>> call, Response<List<LichDat>> response) {
                        mList = response.body();
                        lichDatClientAdapter.setData(mList);
                    }

                    @Override
                    public void onFailure(Call<List<LichDat>> call, Throwable t) {
                        Toast.makeText(mUserActivity, "Lỗi lấy dữ liệu lịch đã đặt", Toast.LENGTH_SHORT).show();
                        Log.d("errorGetDataLichDaDat", t.toString());
                    }
                });
    }

    @Override
    public void deleteItemLichDat(int position) {
        LichDat lichDatToDelete = mList.get(position);
        int ldIDToDelete = lichDatToDelete.getLdID();

        ApiServiceLichDat.apiServiceLichDat.deleteDataLichDat(ldIDToDelete)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.body().equals("thanhcong")){
                            Toast.makeText(mUserActivity, "Xóa thành công lịch đặt", Toast.LENGTH_SHORT).show();
                            mList.remove(position);
                            lichDatClientAdapter.notifyItemRemoved(position);
                        }
                        else{
                            Toast.makeText(mUserActivity, "Lỗi không thể xóa lịch đặt", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(mUserActivity, "Lỗi tương tác với cơ sở dữ liệu", Toast.LENGTH_SHORT).show();
                        Log.d("errorDeleteLichDat", t.toString());
                    }
                });

    }

    @Override
    public void onResume() {
        super.onResume();
        GetDataLichDaDatCuaNguoiDung();
    }
}