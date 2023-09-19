package com.example.myapplication.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Ghe;
import com.example.myapplication.model.LoTrinh;

import java.util.ArrayList;
import java.util.List;

public class GheAdminAdapter extends RecyclerView.Adapter<GheAdminAdapter.GheAdminViewHoler>{
    private List<Ghe> mList;
    private Context context;
    private ClickItemDeleteGhe clickItemDeleteGhe;


    public GheAdminAdapter(Context context, ClickItemDeleteGhe clickItemDeleteGhe) {
        this.context = context;
        this.clickItemDeleteGhe = clickItemDeleteGhe;
        mList = new ArrayList<>();
    }
    public void setData(List<Ghe> mList){
        this.mList = mList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GheAdminViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_admin_ghe, parent, false);
        return new GheAdminViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GheAdminViewHoler holder, int position) {
        Ghe ghe = mList.get(position);
        holder.textViewGheID.setText(String.valueOf(ghe.getgID()));
        holder.textViewTenGhe.setText(ghe.getTenGhe());
        holder.textViewLoaiGhe.setText(ghe.getLoaiGhe());
        holder.textViewTenXeCuaGhe.setText(ghe.getTenXe());
        if (ghe.getTinhTrang() == 1){
            holder.textViewTinhTrang.setText("Full");
        }else{
            holder.textViewTinhTrang.setText("Empty");
        }


        holder.imageViewDelete.setOnClickListener(view -> clickItemDeleteGhe.deleteitemGhe(position));
    }

    @Override
    public int getItemCount() {
        if (mList != null){
            return mList.size();
        }
        return 0;
    }

    public static class GheAdminViewHoler extends RecyclerView.ViewHolder{
        private TextView textViewGheID, textViewTenGhe, textViewLoaiGhe, textViewTinhTrang, textViewTenXeCuaGhe;
        private ImageView imageViewDelete;

        public GheAdminViewHoler(@NonNull View itemView) {
            super(itemView);
            textViewGheID = itemView.findViewById(R.id.textViewGheID);
            textViewTenGhe = itemView.findViewById(R.id.textViewTenGhe);
            textViewLoaiGhe = itemView.findViewById(R.id.textViewLoaiGhe);
            textViewTinhTrang = itemView.findViewById(R.id.textViewTinhTrang);
            imageViewDelete = itemView.findViewById(R.id.imageViewDelete);
            textViewTenXeCuaGhe = itemView.findViewById(R.id.textViewTenXeCuaGhe);
        }
    }

    public interface ClickItemDeleteGhe{
        void deleteitemGhe(int position);
    }
}
