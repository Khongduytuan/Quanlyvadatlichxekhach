package com.example.myapplication.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import com.example.myapplication.activities.UpdateActivity;
import com.example.myapplication.activities.UpdateLoTrinhActivity;
import com.example.myapplication.adapter.LoTrinhAdapter;
import com.example.myapplication.adapter.XeAdapter;
import com.example.myapplication.api.ApiServiceGetDataXe;
import com.example.myapplication.api.ApiServiceLoTrinh;
import com.example.myapplication.model.LoTrinh;
import com.example.myapplication.model.Xe;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoTrinhFragment extends Fragment implements LoTrinhAdapter.ClickItemDeleteLoTrinh, LoTrinhAdapter.LoTrinhItemListener {

    private RecyclerView recycleViewLoTrinh;
    private MainActivity mMainActivity;
    private List<LoTrinh> mList;
    private LoTrinhAdapter loTrinhAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shorts, container, false);
        mMainActivity = (MainActivity) getActivity();

        recycleViewLoTrinh = view.findViewById(R.id.recycleViewLoTrinh);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mMainActivity);
        recycleViewLoTrinh.setLayoutManager(linearLayoutManager);

        mList = new ArrayList<>();
        loTrinhAdapter = new LoTrinhAdapter(mMainActivity, this);
        recycleViewLoTrinh.setAdapter(loTrinhAdapter);
        loTrinhAdapter.setClickListener((LoTrinhAdapter.LoTrinhItemListener) this);
        getDataLoTrinh();
        return view;
    }


    public void getDataLoTrinh(){
        ApiServiceLoTrinh.apiServiceLoTrinh.getDataLoTrinh()
                .enqueue(new Callback<List<LoTrinh>>() {
                    @Override
                    public void onResponse(Call<List<LoTrinh>> call, Response<List<LoTrinh>> response) {
                        mList = response.body();
                        loTrinhAdapter.setData(mList);
                    }

                    @Override
                    public void onFailure(Call<List<LoTrinh>> call, Throwable t) {
                        Toast.makeText(mMainActivity, "Lỗi khi lấy dữ liệu", Toast.LENGTH_SHORT).show();
                        Log.d("errorGetDataLoTrinh", t.toString());

                    }
                });
    }

    @Override
    public void deleteitemLoTrinh(int position) {
        LoTrinh loTrinhToDelete = mList.get(position);
        int ltID = loTrinhToDelete.getLtID();

        ApiServiceLoTrinh.apiServiceLoTrinh.deleteDataLoTrinh(ltID)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            String result = response.body();
                            if (result.equals("thanhcong")) {
                                mList.remove(position);
                                loTrinhAdapter.notifyItemRemoved(position);
                                Toast.makeText(mMainActivity, "Xóa lộ trình thành công", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(mMainActivity, "Xóa lộ trình không thành công", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(mMainActivity, "Lỗi kết nối", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(mMainActivity, "Lỗi gửi yêu cầu", Toast.LENGTH_SHORT).show();
                        Log.d("errorDelete", t.toString());
                    }
                });
    }

    @Override
    public void onItemClick(View view, int position) {
        LoTrinh loTrinhToUpdate = mList.get(position);

        Intent intent = new Intent(mMainActivity, UpdateLoTrinhActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("loTrinhToUpdate", loTrinhToUpdate);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    @Override
    public void onResume() {
        super.onResume();
        getDataLoTrinh();
    }
}

