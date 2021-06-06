package com.example.tutorme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.tutorme.R;
import com.example.tutorme.activities.PostActivity;
import com.example.tutorme.activities.RegisterActivity;
import com.example.tutorme.adapters.ViewPageAdapter;
import com.example.tutorme.fragments.AccountFragment;
import com.example.tutorme.fragments.HomeFragment;
import com.example.tutorme.fragments.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private ViewPager2 viewPager2;
    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navbar);
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

        viewPager2 = findViewById(R.id.fragment_container);
        this.extras = getIntent().getExtras();

        if (this.extras == null) {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        }

        setUpAdapter(viewPager2);
    }

    public void addNewPost(View view){
        Intent intent = new Intent(getApplicationContext(), PostActivity.class);
        intent.putExtra("user", this.extras.getString("user"));
        startActivity(intent);
    }

    private void setUpAdapter(ViewPager2 viewPager2){
        try {
            ViewPageAdapter viewPageAdapter = new ViewPageAdapter(this);
            HomeFragment homeFragment = new HomeFragment();
            Bundle bundle = new Bundle();
            bundle.putString("user", getIntent().getExtras().getString("user"));
            homeFragment.setArguments(bundle);
            viewPageAdapter.addFragment(homeFragment);
            AccountFragment accountFragment = new AccountFragment();
            accountFragment.setArguments(bundle);
            viewPageAdapter.addFragment(accountFragment);
            viewPageAdapter.addFragment(new SettingsFragment());
            viewPager2.setAdapter(viewPageAdapter);
        } catch (Exception e) {
            Log.i("Error: ", e.getMessage());
        }
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