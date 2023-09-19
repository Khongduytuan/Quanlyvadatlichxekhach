package com.example.myapplication.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Xe;

import java.util.ArrayList;
import java.util.List;

public class XeAdapter extends RecyclerView.Adapter<XeAdapter.XeViewHolder>{

    private List<Xe> mList;
    private Context context;

    private ClickItemDeleteXe clickItemDeleteXe;
    private static XeItemListener xeItemListener;

    public XeAdapter(Context context, ClickItemDeleteXe clickItemDeleteXe) {
        this.context = context;
        this.clickItemDeleteXe = clickItemDeleteXe;
        mList = new ArrayList<>();
    }

    // setClick cho 1 đối tượng xe cụ thể
    public void setClickListener(XeItemListener xeItemListener){
        this.xeItemListener = xeItemListener;

    }

    public void setDataXe(List<Xe> newList){
        this.mList = newList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public XeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data_xe, parent, false);
        return new XeViewHolder(itemView);
    }
    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull XeViewHolder holder, int position) {
        Xe xe = mList.get(position);
        holder.textViewXeID.setText(String.valueOf(xe.getxID()));
        holder.textViewTenXe.setText(xe.getTenXe());
        holder.textViewBienSo.setText(xe.getBienSo());
        holder.textViewSoGhe.setText(String.valueOf(xe.getSoGhe()));
        holder.imageViewDelete.setOnClickListener(view -> clickItemDeleteXe.deleteitemXe(position));
    }

    @Override
    public int getItemCount() {
        if (mList != null){
            return mList.size();
        }
        return 0;
    }

    public static class XeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView textViewXeID, textViewTenXe, textViewBienSo, textViewSoGhe;
        ImageView imageViewDelete;

        public XeViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewXeID = itemView.findViewById(R.id.textViewXeID);
            textViewTenXe = itemView.findViewById(R.id.textViewTenXe);
            textViewBienSo = itemView.findViewById(R.id.textViewBienSo);
            textViewSoGhe = itemView.findViewById(R.id.textViewSoGhe);
            imageViewDelete = itemView.findViewById(R.id.imageViewDelete);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if (xeItemListener != null){
                xeItemListener.onItemClick(view, getAdapterPosition());
            }
        }
    }



    public interface ClickItemDeleteXe{
        void deleteitemXe(int position);
    }


    public interface XeItemListener{
        void onItemClick(View view, int position);
    }
}
