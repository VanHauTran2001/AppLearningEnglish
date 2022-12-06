package com.example.appstudyenglish.ui.chi_tiet_tuan_hoc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.example.appstudyenglish.R;
import com.example.appstudyenglish.databinding.ActivityChiTietTuanHocBinding;
import com.example.appstudyenglish.model.Buoi;
import com.example.appstudyenglish.model.Tuan;

import java.util.ArrayList;
import java.util.List;

public class ChiTietTuanHocActivity extends AppCompatActivity implements BuoiAdapter.IBuoi {

    private ActivityChiTietTuanHocBinding binding;
    private Tuan tuan;
    private BuoiAdapter buoiAdapter;
    private List<Buoi> buoiList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_chi_tiet_tuan_hoc);
        tuan = (Tuan) getIntent().getSerializableExtra("tuan");
        setDataToView();
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        addDataToDatabase();
        initRecyclerView();
    }

    private void initRecyclerView() {
        buoiAdapter = new BuoiAdapter(this);
        binding.rcvBuoiHoc.setLayoutManager(new LinearLayoutManager(this));
        binding.rcvBuoiHoc.setAdapter(buoiAdapter);
    }

    private void addDataToDatabase() {
        buoiList = new ArrayList<>();
        buoiList.add(new Buoi("Buổi 1 : Listening","1.1 Từ Vựng","20","1.2 Bài tập - Ghi nhớ từ vựng","20","1.3 Memory Game","20",1));
        buoiList.add(new Buoi("Buổi 2 : Reading","2.1 Từ vựng","20","2.2 Bài tập - Reading part 1","20","2.3 Crossword Game","20",2));
        buoiList.add(new Buoi("Buổi 3 : Writing","3.1 Từ vựng","0","3.2 Image Pairing Game","0","3.3 Bài tập - Topic: Viết thư","0",3));
        buoiList.add(new Buoi("Buổi 4 : Speaking","4.1 Từ vựng","0","4.2 Bài tập - Speaking part 1","0","4.3 Giới thiệu bản thân","0",4));
    }

    private void setDataToView() {
        binding.txtTuan.setText(tuan.getTitle());
        binding.tvPhanTram.setText("69%");
        binding.progressBar.setProgress(69);
        binding.progressBar.setMax(100);
    }

    @Override
    public int getCount() {
        return buoiList.size();
    }

    @Override
    public Buoi getBuoi(int position) {
        return buoiList.get(position);
    }
}