package com.example.appstudyenglish.ui.chi_tiet_tuan_hoc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.appstudyenglish.R;
import com.example.appstudyenglish.databinding.ActivityChiTietTuanHocBinding;
import com.example.appstudyenglish.model.BaiHocTrongNgay;
import com.example.appstudyenglish.model.Buoi;
import com.example.appstudyenglish.model.Tuan;
import com.example.appstudyenglish.ui.chi_tiet_buoi_hoc.ChiTietBuoiHocActivity;

import java.util.ArrayList;
import java.util.List;

public class ChiTietTuanHocActivity extends AppCompatActivity implements BuoiAdapter.IBuoi {

    private ActivityChiTietTuanHocBinding binding;
    private Tuan tuan;
    private BuoiAdapter buoiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_chi_tiet_tuan_hoc);
        tuan = (Tuan) getIntent().getSerializableExtra("tuan");
        Log.d("CheckTuan",tuan.getBuoiArrayList().size()+"");
        setDataToView();
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        initRecyclerView();
    }

    private void initRecyclerView() {
        buoiAdapter = new BuoiAdapter(this);
        binding.rcvBuoiHoc.setLayoutManager(new LinearLayoutManager(this));
        binding.rcvBuoiHoc.setAdapter(buoiAdapter);
    }

    private void setDataToView() {
        binding.txtTuan.setText(tuan.getTitle());
        binding.tvPhanTram.setText("69%");
        binding.progressBar.setProgress(69);
        binding.progressBar.setMax(100);
    }

    @Override
    public int getCount() {
        return tuan.getBuoiArrayList().size();
    }

    @Override
    public Buoi getBuoi(int position) {
        return tuan.getBuoiArrayList().get(position);
    }

    @Override
    public void onClickBuoi(int position) {
        Intent intent = new Intent(ChiTietTuanHocActivity.this, ChiTietBuoiHocActivity.class);
        intent.putExtra("buoi",tuan.getBuoiArrayList().get(position));
        startActivity(intent);
    }
}