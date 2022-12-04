package com.example.appstudyenglish.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.Toast;

import com.example.appstudyenglish.R;
import com.example.appstudyenglish.databinding.ActivityMainBinding;
import com.example.appstudyenglish.ui.fragment.home.HomeFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private HomeFragment homeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        onCLickMenu();
        homeFragment = new HomeFragment();
        getFragment(homeFragment);
        binding.bottomNav.getMenu().findItem(R.id.homeFragment).setChecked(true);
    }

    private void onCLickMenu() {
        binding.bottomNav.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case  R.id.bookmarkFragment :
                    Toast.makeText(this, "BookMark", Toast.LENGTH_SHORT).show();
                    binding.bottomNav.getMenu().findItem(R.id.bookmarkFragment).setChecked(true);
                    break;
                case  R.id.homeFragment :
                    Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
                    binding.bottomNav.getMenu().findItem(R.id.homeFragment).setChecked(true);
                    getFragment(homeFragment);
                    break;
                case  R.id.userFragment :
                    Toast.makeText(this, "User", Toast.LENGTH_SHORT).show();
                    binding.bottomNav.getMenu().findItem(R.id.userFragment).setChecked(true);
                    break;
            }
            return false;
        });
    }

    private void getFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentMain,fragment,Fragment.class.getName())
                .commit();
    }

}