package com.example.myapplication.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.fragment.NguoiDungFragment;
import com.example.myapplication.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>{

    private List<User> mList;
    private Context context;
    private ClickItemDeleteUser clickItemDeleteUser;
    private static UserItemListener userItemListener;

    public UserAdapter(Context context, ClickItemDeleteUser clickItemDeleteUser) {
        this.context = context;
        this.clickItemDeleteUser = clickItemDeleteUser;
        mList = new ArrayList<>();
    }

    public void setClickItemUser(UserItemListener userItemListener) {
        this.userItemListener = userItemListener;
    }

    public void setData(List<User> mList){
        this.mList = mList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(itemView);

    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = mList.get(position);
        if (user.getRole().equals("0")){
            holder.textViewUID.setText(String.valueOf(user.getuID()));
            holder.textViewTenUser.setText(user.getHoTen());
            holder.textViewUserName.setText(user.getUserName());
            holder.textViewDiaChi.setText(user.getDiaChi());
            holder.imageViewDeleteUser.setOnClickListener(view -> clickItemDeleteUser.deleteitemUser(position));
        }

    }

    @Override
    public int getItemCount() {
        if (mList != null){
            return mList.size();
        }
        return 0;
    }


    public static class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView textViewUID, textViewTenUser, textViewUserName, textViewDiaChi;
        private ImageView imageViewDeleteUser;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewUID = itemView.findViewById(R.id.textViewUID);
            textViewTenUser = itemView.findViewById(R.id.textViewTenUser);
            textViewUserName = itemView.findViewById(R.id.textViewUserName);
            textViewDiaChi = itemView.findViewById(R.id.textViewDiaChi);
            imageViewDeleteUser = itemView.findViewById(R.id.imageViewDeleteUser);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (userItemListener != null){
                userItemListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    public interface ClickItemDeleteUser{
        void deleteitemUser(int position);
    }


    public interface UserItemListener{
        void onItemClick(View view, int position);
    }

}
