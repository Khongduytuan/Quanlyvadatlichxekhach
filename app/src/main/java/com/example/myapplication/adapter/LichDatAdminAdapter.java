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

public class LichDatAdminAdapter extends RecyclerView.Adapter<LichDatAdminAdapter.LichDatAdminViewHolder>{
    private List<LichDat> mList;
    private Context context;

    private ClickConfirmLichDat clickConfirmLichDat;
    private ClickDeleteLichDat clickDeleteLichDat;


    public LichDatAdminAdapter(Context context, ClickConfirmLichDat clickConfirmLichDat, ClickDeleteLichDat clickDeleteLichDat) {
        this.clickConfirmLichDat = clickConfirmLichDat;
        this.clickDeleteLichDat = clickDeleteLichDat;
        mList = new ArrayList<>();
        this.context = context;
    }

    public void setData(List<LichDat> mList){
        this.mList = mList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LichDatAdminViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lich_da_dat_admin, parent, false);
        return new LichDatAdminViewHolder(itemView);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull LichDatAdminViewHolder holder, int position) {
        LichDat lichDat = mList.get(position);
        holder.textViewIDLichDat.setText(String.valueOf(lichDat.getLdID()));
        holder.textViewDiemDauLichDatAdmin.setText(lichDat.getDiemDau());
        holder.textViewDiemCuoiLichDatAdmin.setText(lichDat.getDiemCuoi());
        holder.textViewThoiGianXuatPhatLichDatAdmin.setText(lichDat.getThoiGianXuatPhat());
        holder.textViewThoiGianToiNoiLichDatAdmin.setText(lichDat.getThoiGianToiNoi());
        holder.textViewTenXeLichDatAdmin.setText(lichDat.getTenXe());
        holder.textViewThoiGianDatAdmin.setText(lichDat.getThoiGianDat());
        holder.textViewHoTenNguoiDatLich.setText(lichDat.getHoTen());
        if (lichDat.getTinhTrangXacNhan() == 1){
            holder.textViewTinhTrangXacNhanAdmin.setText("Đã Xác Nhận");
            holder.imageViewConfirm.setEnabled(false);


        }else {
            holder.textViewTinhTrangXacNhanAdmin.setText("Chưa Xác Nhận");
            holder.imageViewConfirm.setEnabled(true);

        }

        holder.imageViewConfirm.setOnClickListener(view -> clickConfirmLichDat.confirmLichDatAdmin(position));
        holder.imageViewDeleteLichDat.setOnClickListener(view -> clickDeleteLichDat.deleteLichDatAdmin(position));



    }

    @Override
    public int getItemCount() {
        if (mList != null){
            return mList.size();
        }
        return 0;
    }

    public static class LichDatAdminViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewIDLichDat, textViewDiemDauLichDatAdmin, textViewDiemCuoiLichDatAdmin,
                textViewThoiGianXuatPhatLichDatAdmin, textViewThoiGianToiNoiLichDatAdmin,
                textViewTenXeLichDatAdmin, textViewThoiGianDatAdmin, textViewTinhTrangXacNhanAdmin,
                textViewHoTenNguoiDatLich;

        private ImageView imageViewDeleteLichDat, imageViewConfirm;
        public LichDatAdminViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewIDLichDat = itemView.findViewById(R.id.textViewIDLichDat);
            textViewDiemDauLichDatAdmin = itemView.findViewById(R.id.textViewDiemDauLichDatAdmin);
            textViewDiemCuoiLichDatAdmin = itemView.findViewById(R.id.textViewDiemCuoiLichDatAdmin);
            textViewThoiGianXuatPhatLichDatAdmin = itemView.findViewById(R.id.textViewThoiGianXuatPhatLichDatAdmin);
            textViewThoiGianToiNoiLichDatAdmin = itemView.findViewById(R.id.textViewThoiGianToiNoiLichDatAdmin);
            textViewTenXeLichDatAdmin = itemView.findViewById(R.id.textViewTenXeLichDatAdmin);
            textViewThoiGianDatAdmin = itemView.findViewById(R.id.textViewThoiGianDatAdmin);
            textViewTinhTrangXacNhanAdmin = itemView.findViewById(R.id.textViewTinhTrangXacNhanAdmin);
            textViewHoTenNguoiDatLich = itemView.findViewById(R.id.textViewHoTenNguoiDatLich);
            imageViewDeleteLichDat = itemView.findViewById(R.id.imageViewDeleteLichDat);
            imageViewConfirm = itemView.findViewById(R.id.imageViewConfirm);
        }
    }


    public interface ClickDeleteLichDat{
        void deleteLichDatAdmin(int position);
    }

    public interface ClickConfirmLichDat{
        void confirmLichDatAdmin(int position);
    }
}
