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
import com.example.myapplication.model.LoTrinh;

import java.util.ArrayList;
import java.util.List;

public class LoTrinhClientAdapter extends RecyclerView.Adapter<LoTrinhClientAdapter.LoTrinhClientViewHolder> {
    private List<LoTrinh> mList;
    private Context context;
    private static LoTrinhItemListener loTrinhItemListener;

    private ClickItemAddLoTrinh clickItemAddLoTrinh;
    public LoTrinhClientAdapter(Context context, ClickItemAddLoTrinh clickItemAddLoTrinh) {
        this.context = context;
        this.clickItemAddLoTrinh = clickItemAddLoTrinh;
        mList = new ArrayList<>();
    }

    public void setClickListener(LoTrinhItemListener loTrinhItemListener){
        this.loTrinhItemListener = loTrinhItemListener;

    }
    public void setData(List<LoTrinh> mList){
        this.mList = mList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LoTrinhClientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lo_trinh_client, parent, false);
        return new LoTrinhClientViewHolder(itemView);
    }
    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull LoTrinhClientViewHolder holder, int position) {
        LoTrinh loTrinh = mList.get(position);
        holder.textViewClientDiemDau.setText(loTrinh.getDiemDau());
        holder.textViewClientDiemCuoi.setText(loTrinh.getDiemCuoi());
        holder.textViewClientThoiGianXuatPhat.setText(loTrinh.getThoiGianXuatPhat());
        holder.textViewClientThoiGianToiNoi.setText(loTrinh.getThoiGianToiNoi());
        holder.textViewClientTenXeLoTrinh.setText(loTrinh.getTenXe());
        holder.imageViewClientAddLoTrinh.setOnClickListener(view -> {
            clickItemAddLoTrinh.additemLoTrinh(position);
        });
    }

    @Override
    public int getItemCount() {
        if (mList != null){
            return mList.size();
        }
        return 0;
    }

    public static class LoTrinhClientViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView textViewClientDiemDau, textViewClientDiemCuoi,
                textViewClientThoiGianXuatPhat,textViewClientThoiGianToiNoi,textViewClientTenXeLoTrinh;
        ImageView imageViewClientAddLoTrinh;

        public LoTrinhClientViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewClientDiemDau = itemView.findViewById(R.id.textViewClientDiemDau);
            textViewClientDiemCuoi = itemView.findViewById(R.id.textViewClientDiemCuoi);
            textViewClientThoiGianXuatPhat = itemView.findViewById(R.id.textViewClientThoiGianXuatPhat);
            textViewClientThoiGianToiNoi = itemView.findViewById(R.id.textViewClientThoiGianToiNoi);
            textViewClientTenXeLoTrinh = itemView.findViewById(R.id.textViewClientTenXeLoTrinh);
            imageViewClientAddLoTrinh = itemView.findViewById(R.id.imageViewClientAddLoTrinh);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if (loTrinhItemListener != null){
                loTrinhItemListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    public interface ClickItemAddLoTrinh{
        void additemLoTrinh(int position);
    }

    public interface LoTrinhItemListener{
        void onItemClick(View view, int position);
    }
}
