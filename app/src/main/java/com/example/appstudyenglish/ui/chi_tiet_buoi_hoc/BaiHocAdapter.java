package com.example.appstudyenglish.ui.chi_tiet_buoi_hoc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appstudyenglish.R;
import com.example.appstudyenglish.databinding.ItemBaiHocBinding;
import com.example.appstudyenglish.databinding.ItemTuanBinding;
import com.example.appstudyenglish.model.BaiHocTrongNgay;
import com.example.appstudyenglish.model.TuMoi;
import com.example.appstudyenglish.model.Tuan;

import java.util.ArrayList;
import java.util.List;

public class BaiHocAdapter extends RecyclerView.Adapter<BaiHocAdapter.NotificationViewHolder> implements TuMoiAdapter.ITuMoi {

    private IBaiHoc iBaiHoc;
    private List<TuMoi> tuMoiList;
    private Context context;
    private TuMoiAdapter tuMoiAdapter;
    private boolean show = true;

    public BaiHocAdapter(IBaiHoc iBaiHoc, Context context) {
        this.iBaiHoc = iBaiHoc;
        this.context = context;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBaiHocBinding binding = ItemBaiHocBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        addDataBaiHoc();
        return new NotificationViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        BaiHocTrongNgay baiHocTrongNgay = iBaiHoc.getBaiHoc(position);
        holder.binding.txtTenBaiHoc.setText(baiHocTrongNgay.getBaiHoc());
        holder.binding.txtPhanTram.setText(baiHocTrongNgay.getTienDo()+"/20");

        tuMoiAdapter = new TuMoiAdapter(this);
        holder.binding.rcvCacMucHoc.setLayoutManager(new LinearLayoutManager(context));
        holder.binding.rcvCacMucHoc.setAdapter(tuMoiAdapter);

        holder.binding.rcvCacMucHoc.setVisibility(View.GONE);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(show == true){
                    holder.binding.rcvCacMucHoc.setVisibility(View.VISIBLE);
                    show = false;
                }else {
                    holder.binding.rcvCacMucHoc.setVisibility(View.GONE);
                    show = true;
                }
            }
        });
    }

    private void addDataBaiHoc(){
        tuMoiList = new ArrayList<>();
        tuMoiList.add(new TuMoi("Quarter /ˈkwɔː.tər/ ",0));
        tuMoiList.add(new TuMoi("Quarter /ˈkwɔː.tər/ ",1));
        tuMoiList.add(new TuMoi("Quarter /ˈkwɔː.tər/ ",0));
        tuMoiList.add(new TuMoi("Quarter /ˈkwɔː.tər/ ",1));
        tuMoiList.add(new TuMoi("Quarter /ˈkwɔː.tər/ ",1));
        tuMoiList.add(new TuMoi("Quarter /ˈkwɔː.tər/ ",0));
        tuMoiList.add(new TuMoi("Quarter /ˈkwɔː.tər/ ",0));
    }

    @Override
    public int getItemCount() {
        return iBaiHoc.getCount();
    }

    @Override
    public int getCount() {
        return tuMoiList.size();
    }

    @Override
    public TuMoi getTuMoi(int position) {
        return tuMoiList.get(position);
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
