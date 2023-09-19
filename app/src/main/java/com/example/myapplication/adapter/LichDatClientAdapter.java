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
import com.example.myapplication.model.LichDat;

import java.util.ArrayList;
import java.util.List;

public class LichDatClientAdapter extends RecyclerView.Adapter<LichDatClientAdapter.LichDatClientVIewHolder> {
    private List<LichDat> mList;
    private Context context;
    private ClickItemDeleteLichDatForClient clickItemDeleteLichDatForClient;

    public LichDatClientAdapter(Context context, ClickItemDeleteLichDatForClient clickItemDeleteLichDatForClient) {
        this.context = context;
        this.clickItemDeleteLichDatForClient = clickItemDeleteLichDatForClient;
        mList = new ArrayList<>();
    }
    public void setData(List<LichDat> mList){
        this.mList = mList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LichDatClientVIewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lich_da_dat, parent, false);
        return  new LichDatClientVIewHolder(itemView);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull LichDatClientVIewHolder holder, int position) {
        LichDat lichDat = mList.get(position);
        holder.textViewDiemDauLichDat.setText(lichDat.getDiemDau());
        holder.textViewDiemCuoiLichDat.setText(lichDat.getDiemCuoi());
        holder.textViewThoiGianXuatPhatLichDat.setText(lichDat.getThoiGianXuatPhat());
        holder.textViewThoiGianToiNoiLichDat.setText(lichDat.getThoiGianToiNoi());
        holder.textViewTenXeLichDat.setText(lichDat.getTenXe());
        holder.textViewThoiGianDat.setText(lichDat.getThoiGianDat());
        if (lichDat.getTinhTrangXacNhan() == 1){
            holder.textViewTinhTrangXacNhan.setText("Đã Xác Nhận");
        }else {
            holder.textViewTinhTrangXacNhan.setText("Chưa Xác Nhận");
        }

        holder.imageViewDeleteLichDat.setOnClickListener(view -> clickItemDeleteLichDatForClient.deleteItemLichDat(position));
    }

    @Override
    public int getItemCount() {
        if (mList != null){
            return mList.size();
        }
        return 0;
    }

    public static class LichDatClientVIewHolder extends RecyclerView.ViewHolder{
        private TextView textViewDiemDauLichDat, textViewDiemCuoiLichDat,
                textViewThoiGianXuatPhatLichDat, textViewThoiGianToiNoiLichDat,
                textViewTenXeLichDat, textViewThoiGianDat, textViewTinhTrangXacNhan;
        private ImageView imageViewDeleteLichDat;
        public LichDatClientVIewHolder(@NonNull View itemView) {
            super(itemView);
            textViewDiemDauLichDat = itemView.findViewById(R.id.textViewDiemDauLichDat);
            textViewDiemCuoiLichDat = itemView.findViewById(R.id.textViewDiemCuoiLichDat);
            textViewThoiGianXuatPhatLichDat = itemView.findViewById(R.id.textViewThoiGianXuatPhatLichDat);
            textViewThoiGianToiNoiLichDat = itemView.findViewById(R.id.textViewThoiGianToiNoiLichDat);
            textViewTenXeLichDat = itemView.findViewById(R.id.textViewTenXeLichDat);
            textViewThoiGianDat = itemView.findViewById(R.id.textViewThoiGianDat);
            textViewTinhTrangXacNhan = itemView.findViewById(R.id.textViewTinhTrangXacNhan);
            imageViewDeleteLichDat = itemView.findViewById(R.id.imageViewDeleteLichDat);
        }
    }

    public interface ClickItemDeleteLichDatForClient{
        void deleteItemLichDat(int position);
    }
}
