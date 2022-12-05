package com.example.appstudyenglish.ui.fragment.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.appstudyenglish.R;
import com.example.appstudyenglish.databinding.FragmentHomeBinding;
import com.example.appstudyenglish.model.KhoaHoc;
import com.example.appstudyenglish.ui.fragment.khoa_hoc_info.KhoaHocInfoActivity;
import com.example.appstudyenglish.ui.fragment.notification.NotificationFragment;
import com.example.appstudyenglish.ui.fragment.search.SearchFragment;
import com.example.appstudyenglish.ui.fragment.thong_tin_vstep.ThongTinVstepFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment  implements KhoaHocAdapter.IKhoaHoc {

    private View view;
    private FragmentHomeBinding binding;
    private List<KhoaHoc> listKhoaHoc;
    private List<KhoaHoc> listKhoaHoc2;
    private KhoaHocAdapter khoaHocAdapter;
    private KhoaHocAdapter khoaHocAdapter2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false);
        view = binding.getRoot();
        onCLick();
        addData();
        initAdapter();
        return view;
    }

    private void initAdapter() {
        khoaHocAdapter = new KhoaHocAdapter(this,1);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        binding.rcvThongTinStep.setLayoutManager(linearLayoutManager);
        binding.rcvThongTinStep.setAdapter(khoaHocAdapter);

        khoaHocAdapter2 = new KhoaHocAdapter(this,2);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        binding.rcvCacKhoaHoc.setLayoutManager(linearLayoutManager2);
        binding.rcvCacKhoaHoc.setAdapter(khoaHocAdapter2);
    }


    private void addData() {
        listKhoaHoc = new ArrayList<>();
        listKhoaHoc.add(new KhoaHoc("H1",R.drawable.img_test,"Chứng chỉ Vstep là gì ?","19/05/2001",2001,""));
        listKhoaHoc.add(new KhoaHoc("H1",R.drawable.img_test,"Hậu môn là ai ?","06/09/2021",2001,""));
        listKhoaHoc.add(new KhoaHoc("H1",R.drawable.img_test,"Khi nào cần học Tiếng anh ?","19/05/2001",2001,""));

        listKhoaHoc2 = new ArrayList<>();
        listKhoaHoc2.add(new KhoaHoc("H1",R.drawable.img_test,"Level A0","2 tuần",2301,""));
        listKhoaHoc2.add(new KhoaHoc("H1",R.drawable.img_test,"Level A1","3 tuần",3343,""));
        listKhoaHoc2.add(new KhoaHoc("H1",R.drawable.img_test,"Level A2","8 tuần",7663,""));

    }


    private void onCLick() {
        binding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchFragment searchFragment = new SearchFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmentMain, searchFragment);
                fragmentTransaction.addToBackStack(SearchFragment.TAG);
                fragmentTransaction.commit();
            }
        });
        binding.btnNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationFragment notificationFragment = new NotificationFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmentMain, notificationFragment);
                fragmentTransaction.addToBackStack(NotificationFragment.TAG);
                fragmentTransaction.commit();
            }
        });
    }

    @Override
    public int getCount(int check) {
        if(check == 1){
            return listKhoaHoc.size();
        }else {
            return listKhoaHoc2.size();
        }
    }

    @Override
    public KhoaHoc getKhoaHoc(int position,int check) {
        if(check == 1){
            return listKhoaHoc.get(position);
        }else {
            return listKhoaHoc2.get(position);
        }
    }

    @Override
    public void onCLickKhoaHoc(int position,int check) {
        if(check == 1){
            ThongTinVstepFragment thongTinVstepFragment = new ThongTinVstepFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("stepinfo",listKhoaHoc.get(position));
            thongTinVstepFragment.setArguments(bundle);
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragmentMain, thongTinVstepFragment);
            fragmentTransaction.addToBackStack(ThongTinVstepFragment.TAG);
            fragmentTransaction.commit();
        }else {
            Intent intent = new Intent(getContext(), KhoaHocInfoActivity.class);
            intent.putExtra("khoahoc",listKhoaHoc2.get(position));
            startActivity(intent);
        }
    }
}