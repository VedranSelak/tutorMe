package com.example.tutorme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private ViewPager2 viewPager2;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navbar);
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

        viewPager2 = findViewById(R.id.fragment_container);


        setUpAdapter(viewPager2);
        if (getIntent().getExtras() == null) {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        }

    }

    private void setUpAdapter(ViewPager2 viewPager2){
        ViewPageAdapter viewPageAdapter = new ViewPageAdapter(this);
        viewPageAdapter.addFragment(new HomeFragment());
        viewPageAdapter.addFragment(new AccountFragment());
        viewPageAdapter.addFragment(new SettingsFragment());
        viewPager2.setAdapter(viewPageAdapter);
    }


    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Log.d("Debug", "Item clicked" + item.getItemId());
            switch (item.getItemId()) {
                case R.id.nav_home:
                    viewPager2.setCurrentItem(0);
                    return true;
                case R.id.nav_account:
                    viewPager2.setCurrentItem(1);
                    return true;
                case R.id.nav_settings:
                    viewPager2.setCurrentItem(2);
                    return true;
                default:
                    return false;
            }
        }
    };
}