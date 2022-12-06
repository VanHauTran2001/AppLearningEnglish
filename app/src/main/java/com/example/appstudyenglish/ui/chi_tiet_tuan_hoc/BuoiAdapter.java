package com.example.appstudyenglish.ui.chi_tiet_tuan_hoc;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appstudyenglish.R;
import com.example.appstudyenglish.databinding.ItemBuoiHocBinding;
import com.example.appstudyenglish.databinding.ItemTuanBinding;
import com.example.appstudyenglish.model.Buoi;
import com.example.appstudyenglish.model.Tuan;

public class BuoiAdapter extends RecyclerView.Adapter<BuoiAdapter.NotificationViewHolder> {

    private IBuoi iBuoi;

    public BuoiAdapter(IBuoi iBuoi) {
        this.iBuoi = iBuoi;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBuoiHocBinding binding = ItemBuoiHocBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new NotificationViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        Buoi buoi = iBuoi.getBuoi(position);
        holder.binding.txtBuoi.setText(buoi.getTenBuoi());
        holder.binding.txtPhan1.setText(buoi.getPhan1());
        holder.binding.txtPhan2.setText(buoi.getPhan2());
        holder.binding.txtPhan3.setText(buoi.getPhan3());
        holder.binding.txtPhanTram1.setText(buoi.getTienDo1()+"/20");
        holder.binding.txtPhanTram2.setText(buoi.getTienDo2()+"/20");
        holder.binding.txtPhanTram3.setText(buoi.getTienDo3()+"/20");
        if(buoi.getCheckLoai() == 1){
            holder.binding.imgCapacity.setImageResource(R.drawable.ic_headphones);
        }else if(buoi.getCheckLoai() == 2){
            holder.binding.imgCapacity.setImageResource(R.drawable.ic_book);
        } else if(buoi.getCheckLoai() == 3){
            holder.binding.imgCapacity.setImageResource(R.drawable.ic_pen);
        } else if(buoi.getCheckLoai() == 4){
            holder.binding.imgCapacity.setImageResource(R.drawable.ic_speak);
        }
    }

    @Override
    public int getItemCount() {
        return iBuoi.getCount();
    }

    public interface IBuoi{
        int getCount();
        Buoi getBuoi(int position);
    }
    public class NotificationViewHolder extends RecyclerView.ViewHolder{
        ItemBuoiHocBinding binding;
        public NotificationViewHolder(@NonNull ItemBuoiHocBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
