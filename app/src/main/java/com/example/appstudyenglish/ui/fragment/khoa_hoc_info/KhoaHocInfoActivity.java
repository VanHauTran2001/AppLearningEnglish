package com.example.appstudyenglish.ui.fragment.khoa_hoc_info;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.appstudyenglish.R;
import com.example.appstudyenglish.databinding.ActivityKhoaHocInfoBinding;
import com.example.appstudyenglish.model.KhoaHoc;

public class KhoaHocInfoActivity extends AppCompatActivity {

    private ActivityKhoaHocInfoBinding binding;
    private KhoaHoc khoaHoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_khoa_hoc_info);
        getAndSetData();
        onClickListener();
    }

    private void getAndSetData() {
        khoaHoc = (KhoaHoc) getIntent().getSerializableExtra("khoahoc");
        binding.anhBiaKhoaHoc.setImageResource(khoaHoc.getAvatar());
        binding.txtTenKhoaHoc.setText(khoaHoc.getName());
        binding.txtTenGiaoVien.setText("Hoàng Minh");
        binding.txtThoiGian.setText(khoaHoc.getDate());
    }

    private void onClickListener() {
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        binding.btnMuaKhoaHoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(KhoaHocInfoActivity.this, "Mua khóa học thành công", Toast.LENGTH_SHORT).show();
            }
        });
    }
}