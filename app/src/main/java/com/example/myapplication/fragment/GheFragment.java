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

import com.example.myapplication.activities.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.adapter.GheAdminAdapter;
import com.example.myapplication.adapter.LoTrinhAdapter;
import com.example.myapplication.api.ApiServiceGhe;
import com.example.myapplication.api.ApiServiceLoTrinh;
import com.example.myapplication.model.Ghe;
import com.example.myapplication.model.LoTrinh;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GheFragment extends Fragment implements GheAdminAdapter.ClickItemDeleteGhe {
    private RecyclerView recycleViewGhe;
    private MainActivity mMainActivity;
    private List<Ghe> mList;
    private GheAdminAdapter gheAdminAdapter;



    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_subscription, container, false);
        mMainActivity = (MainActivity) getActivity();

        recycleViewGhe = view.findViewById(R.id.recycleViewGhe);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mMainActivity);
        recycleViewGhe.setLayoutManager(linearLayoutManager);

        mList = new ArrayList<>();
        gheAdminAdapter = new GheAdminAdapter(mMainActivity, this);
        recycleViewGhe.setAdapter(gheAdminAdapter);
        ApiServiceGhe.apiServiceGhe.getDataGhe()
                .enqueue(new Callback<List<Ghe>>() {
                    @Override
                    public void onResponse(Call<List<Ghe>> call, Response<List<Ghe>> response) {
                        mList = response.body();
                        gheAdminAdapter.setData(mList);
                    }

                    @Override
                    public void onFailure(Call<List<Ghe>> call, Throwable t) {
                        Toast.makeText(mMainActivity, "Lỗi khi lấy thông tin ghế", Toast.LENGTH_SHORT).show();
                        Log.d("errorCallDataGhe", t.toString());
                    }
                });
//
//        ApiServiceLoTrinh.apiServiceLoTrinh.getDataLoTrinh()
//                .enqueue(new Callback<List<LoTrinh>>() {
//                    @Override
//                    public void onResponse(Call<List<LoTrinh>> call, Response<List<LoTrinh>> response) {
//                        mList = response.body();
//                        loTrinhAdapter.setData(mList);
//                    }
//
//                    @Override
//                    public void onFailure(Call<List<LoTrinh>> call, Throwable t) {
//
//                    }
//                });

        return view;
    }

    @Override
    public void deleteitemGhe(int position) {
        Ghe ghe = mList.get(position);
        int gID = ghe.getgID();

        ApiServiceGhe.apiServiceGhe.deleteDataGhe(gID)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.body().equals("thanhcong")){
                            Toast.makeText(mMainActivity, "Xóa đối tượng Ghế thành công", Toast.LENGTH_SHORT).show();
                            mList.remove(position);
                            gheAdminAdapter.notifyItemRemoved(position);
                        }
                        else{
                            Toast.makeText(mMainActivity, "Lỗi không thể xóa!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(mMainActivity, "Lỗi khi xóa ở cơ sở dữ liệu!", Toast.LENGTH_SHORT).show();
                        Log.d("errorDeleteGhe", t.toString());
                    }
                });

    }
}