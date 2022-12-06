package com.example.appstudyenglish.ui.test.listening;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.appstudyenglish.R;
import com.example.appstudyenglish.databinding.ActivityTestListeningBinding;

public class TestListeningActivity extends AppCompatActivity {
    private ActivityTestListeningBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_test_listening);
    }
}