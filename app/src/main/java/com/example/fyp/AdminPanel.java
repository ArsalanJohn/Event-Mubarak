package com.example.fyp;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.fyp.Adapters.AdminPanelPagerAdapter;
import com.example.fyp.Adapters.VendorViewPagerAdapter;

public class AdminPanel extends AppCompatActivity {
    ViewPager viewPager;
    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        viewPager = findViewById(R.id.AdminPanelviewPager);
        tabLayout = findViewById(R.id.AdminPaneltabLayout);

        tabLayout.setupWithViewPager(viewPager);
        final AdminPanelPagerAdapter adapter = new AdminPanelPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);



    }
}
