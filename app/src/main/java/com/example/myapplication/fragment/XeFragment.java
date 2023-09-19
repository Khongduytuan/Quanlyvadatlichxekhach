package com.example.myapplication.fragment;

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
import com.example.myapplication.adapter.XeAdapter;
import com.example.myapplication.api.ApiServiceGetDataXe;
import com.example.myapplication.model.Xe;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class XeFragment extends Fragment implements XeAdapter.ClickItemDeleteXe, XeAdapter.XeItemListener {
    private RecyclerView recycleViewDataXe;
    private XeAdapter xeAdapter;
    private List<Xe> mList;
    private MainActivity mMainActivity;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        mMainActivity = (MainActivity) getActivity();

        recycleViewDataXe = view.findViewById(R.id.recycleViewDataXe);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mMainActivity);
        recycleViewDataXe.setLayoutManager(linearLayoutManager);

        mList = new ArrayList<>();

        xeAdapter = new XeAdapter(mMainActivity, this);
        recycleViewDataXe.setAdapter(xeAdapter);
        xeAdapter.setClickListener((XeAdapter.XeItemListener) this);


        GetDataXe();


        return view;
    }


    @Override
    public void deleteitemXe(int position) {
        Xe xeToDelete = mList.get(position);
        int xID = xeToDelete.getxID();

        ApiServiceGetDataXe.apiServiceGetDataXe.deleteDataXe(xID)
        .enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    String result = response.body();
                    if (result.equals("thanhcong")) {
                        mList.remove(position);
                        xeAdapter.notifyItemRemoved(position);
                        Toast.makeText(mMainActivity, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(mMainActivity, "Xóa không thành công", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(mMainActivity, "Lỗi kết nối", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(mMainActivity, "Lỗi gửi yêu cầu", Toast.LENGTH_SHORT).show();
                Log.d("errorDeleteXe", t.toString());
            }
        });
    }

    @Override
    public void onItemClick(View view, int position) {
        Xe xeToUpdate = mList.get(position);

        Log.d("XeUpdate", "inFragment" + xeToUpdate.getTenXe());

        Intent intent = new Intent(mMainActivity, UpdateActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("xeToUpdate", xeToUpdate);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    public void GetDataXe(){
        ApiServiceGetDataXe.apiServiceGetDataXe.getDataXe().enqueue(new Callback<List<Xe>>() {
            @Override
            public void onResponse(Call<List<Xe>> call, Response<List<Xe>> response) {
//                Toast.makeText(mMainActivity, "Call Api successfully", Toast.LENGTH_SHORT).show();
                mList = response.body();
                xeAdapter.setDataXe(mList);
                Log.d("success", String.valueOf(mList.size()));
            }

            @Override
            public void onFailure(Call<List<Xe>> call, Throwable t) {
                Toast.makeText(mMainActivity, "Call api error: " + t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("errorCallApi", t.toString());
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        GetDataXe();
    }
}