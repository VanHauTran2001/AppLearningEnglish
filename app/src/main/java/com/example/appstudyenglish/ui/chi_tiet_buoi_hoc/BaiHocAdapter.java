package com.example.appstudyenglish.ui.chi_tiet_buoi_hoc;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appstudyenglish.R;
import com.example.appstudyenglish.databinding.ItemBaiHocBinding;
import com.example.appstudyenglish.databinding.ItemTuanBinding;
import com.example.appstudyenglish.model.BaiHocTrongNgay;
import com.example.appstudyenglish.model.Tuan;

public class BaiHocAdapter extends RecyclerView.Adapter<BaiHocAdapter.NotificationViewHolder> {

    private IBaiHoc iBaiHoc;

    public BaiHocAdapter(IBaiHoc iBaiHoc) {
        this.iBaiHoc = iBaiHoc;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBaiHocBinding binding = ItemBaiHocBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new NotificationViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        BaiHocTrongNgay baiHocTrongNgay = iBaiHoc.getBaiHoc(position);
        holder.binding.txtTenBaiHoc.setText(baiHocTrongNgay.getBaiHoc());
        holder.binding.txtPhanTram.setText(baiHocTrongNgay.getTienDo()+"/20");
    }

    @Override
    public int getItemCount() {
        return iBaiHoc.getCount();
    }

    public interface IBaiHoc{
        int getCount();
        BaiHocTrongNgay getBaiHoc(int position);
    }
    public class NotificationViewHolder extends RecyclerView.ViewHolder{
        ItemBaiHocBinding binding;
        public NotificationViewHolder(@NonNull ItemBaiHocBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
