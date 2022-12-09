package com.example.appstudyenglish.ui.chi_tiet_khoa_hoc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.appstudyenglish.R;
import com.example.appstudyenglish.databinding.ActivityChiTietKhoaHocBinding;
import com.example.appstudyenglish.model.KhoaHoc;
import com.example.appstudyenglish.model.Tuan;
import com.example.appstudyenglish.ui.chi_tiet_tuan_hoc.ChiTietTuanHocActivity;
import com.example.appstudyenglish.ui.khoa_hoc_info.KhoaHocInfoActivity;
import com.example.appstudyenglish.ui.main.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class ChiTietKhoaHocActivity extends AppCompatActivity implements TuanAdapter.ITuan {

    private ActivityChiTietKhoaHocBinding binding;
    private KhoaHoc khoaHoc;
    private List<Tuan> tuanList;
    private TuanAdapter tuanAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_chi_tiet_khoa_hoc);
        khoaHoc = (KhoaHoc) getIntent().getSerializableExtra("khoahoc");
        getAndSetData();
        addDataTuan();
        initRecyclerview();
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        binding.btnBookmart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChiTietKhoaHocActivity.this, MainActivity.class);
                intent.putExtra("check",2);
                startActivity(intent);
            }
        });
    }

    private void initRecyclerview() {
        tuanAdapter = new TuanAdapter(this);
        binding.rcvCacTuanHoc.setLayoutManager(new LinearLayoutManager(this));
        binding.rcvCacTuanHoc.setAdapter(tuanAdapter);
    }

    private void getAndSetData() {
        binding.anhBiaKhoaHoc.setImageResource(khoaHoc.getAvatar());
        binding.txtTenKhoaHoc.setText(khoaHoc.getName());
        binding.txtTenGiaoVien.setText(khoaHoc.getTeacher());
        binding.txtThoiGian.setText(khoaHoc.getDate());
    }

    private void addDataTuan() {
        tuanList = new ArrayList<>();
        tuanList.add(new Tuan("Tuần 1",1));
        tuanList.add(new Tuan("Tuần 2",1));
        tuanList.add(new Tuan("Tuần 3",1));
        tuanList.add(new Tuan("Kỳ thi kiểm tra",1));
        tuanList.add(new Tuan("Tuần 5",1));
        tuanList.add(new Tuan("Tuần 6",1));
    }

    @Override
    public int getCount() {
        return tuanList.size();
    }

    @Override
    public Tuan getTuan(int position) {
        return tuanList.get(position);
    }

    @Override
    public void onClickTuan(int position) {
        Intent intent = new Intent(ChiTietKhoaHocActivity.this, ChiTietTuanHocActivity.class);
        intent.putExtra("tuan",tuanList.get(position));
        startActivity(intent);
    }
}