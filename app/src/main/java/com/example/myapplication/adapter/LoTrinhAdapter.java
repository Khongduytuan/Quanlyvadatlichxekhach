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

public class LoTrinhAdapter extends RecyclerView.Adapter<LoTrinhAdapter.LoTrinhViewHolder> {
    private List<LoTrinh> mList;
    private Context context;
    private static LoTrinhItemListener loTrinhItemListener;

    private ClickItemDeleteLoTrinh clickItemDeleteLoTrinh;
    public LoTrinhAdapter(Context context, ClickItemDeleteLoTrinh clickItemDeleteLoTrinh) {
        this.context = context;
        this.clickItemDeleteLoTrinh = clickItemDeleteLoTrinh;
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
    public LoTrinhViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data_lotrinh, parent, false);
        return new LoTrinhViewHolder(itemView);
    }
    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull LoTrinhViewHolder holder, int position) {
        LoTrinh loTrinh = mList.get(position);
        holder.textViewLoTrinhID.setText(String.valueOf(loTrinh.getLtID()));
        holder.textViewDiemDau.setText(loTrinh.getDiemDau());
        holder.textViewDiemCuoi.setText(loTrinh.getDiemCuoi());
        holder.textViewThoiGianXuatPhat.setText(loTrinh.getThoiGianXuatPhat());
        holder.textViewThoiGianToiNoi.setText(loTrinh.getThoiGianToiNoi());
        holder.textViewTenXeLoTrinh.setText(loTrinh.getTenXe());
        holder.imageViewDeleteLoTrinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickItemDeleteLoTrinh.deleteitemLoTrinh(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mList != null){
            return mList.size();
        }
        return 0;
    }

    public static class LoTrinhViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView textViewLoTrinhID, textViewDiemDau, textViewDiemCuoi, textViewThoiGianXuatPhat,textViewThoiGianToiNoi,textViewTenXeLoTrinh;
        ImageView imageViewDeleteLoTrinh;

        public LoTrinhViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewLoTrinhID = itemView.findViewById(R.id.textViewLoTrinhID);
            textViewDiemDau = itemView.findViewById(R.id.textViewDiemDau);
            textViewDiemCuoi = itemView.findViewById(R.id.textViewDiemCuoi);
            textViewThoiGianXuatPhat = itemView.findViewById(R.id.textViewThoiGianXuatPhat);
            textViewThoiGianToiNoi = itemView.findViewById(R.id.textViewThoiGianToiNoi);
            imageViewDeleteLoTrinh = itemView.findViewById(R.id.imageViewDeleteLoTrinh);
            textViewTenXeLoTrinh = itemView.findViewById(R.id.textViewTenXeLoTrinh);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if (loTrinhItemListener != null){
                loTrinhItemListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    public interface ClickItemDeleteLoTrinh{
        void deleteitemLoTrinh(int position);
    }

    public interface LoTrinhItemListener{
        void onItemClick(View view, int position);
    }
}
