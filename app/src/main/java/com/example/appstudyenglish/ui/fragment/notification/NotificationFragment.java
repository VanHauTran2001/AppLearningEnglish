package com.example.appstudyenglish.ui.fragment.notification;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.appstudyenglish.R;
import com.example.appstudyenglish.databinding.FragmentNotificationBinding;

public class NotificationFragment extends Fragment {

    private FragmentNotificationBinding binding;
    private View view;
    public static final String TAG = NotificationFragment.class.getName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_notification, container, false);
        view = binding.getRoot();
        onClick();
        return view;
    }

    private void onClick() {
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 getFragmentManager().popBackStack();
            }
        });
    }
}