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

import com.example.myapplication.R;
import com.example.myapplication.activities.MainActivity;
import com.example.myapplication.activities.UpdateActivity;
import com.example.myapplication.activities.UpdateUserActivity;
import com.example.myapplication.adapter.UserAdapter;
import com.example.myapplication.adapter.XeAdapter;
import com.example.myapplication.api.ApiServiceUser;
import com.example.myapplication.model.User;
import com.example.myapplication.model.Xe;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NguoiDungFragment extends Fragment implements UserAdapter.ClickItemDeleteUser, UserAdapter.UserItemListener{

    private RecyclerView recycleViewDataUser;
    private UserAdapter userAdapter;
    private List<User> mList;
    private MainActivity mMainActivity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_library, container, false);
        mMainActivity = (MainActivity) getActivity();

        recycleViewDataUser = view.findViewById(R.id.recycleViewDataUser);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mMainActivity);
        recycleViewDataUser.setLayoutManager(linearLayoutManager);

        mList = new ArrayList<>();

        userAdapter = new UserAdapter(mMainActivity, this);
        recycleViewDataUser.setAdapter(userAdapter);
        userAdapter.setClickItemUser((UserAdapter.UserItemListener) this);


        GetDataUser();
        return view;
    }

    private void GetDataUser() {
        ApiServiceUser.apiServiceUser.getDataUserByRole("0").enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                mList = response.body();
//                Toast.makeText(mMainActivity, "Lấy thông tin người dùng thành công", Toast.LENGTH_SHORT).show();
                userAdapter.setData(mList);
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(mMainActivity, "Có lỗi xảy ra khi lấy dữ liệu người dùng", Toast.LENGTH_SHORT).show();
                Log.d("errorGetDataUser", t.toString());
            }
        });

    }

    @Override
    public void deleteitemUser(int position) {
        User user = mList.get(position);
        int uID = user.getuID();
        if (user.getRole().equals("0")){
            ApiServiceUser.apiServiceUser.deleteUser(uID)
                    .enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            if (response.body().equals("thanhcong")){
                                mList.remove(position);
                                userAdapter.notifyItemRemoved(position);
                                Toast.makeText(mMainActivity, "Xóa đối tượng người dùng thành công", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Toast.makeText(mMainActivity, "Lỗi xóa đối tượng người dùng này!", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        User userToUpdate = mList.get(position);

        Log.d("check", userToUpdate.getEmail());

        Intent intent = new Intent(mMainActivity, UpdateUserActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("userToUpdate", userToUpdate);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        GetDataUser();
    }
}