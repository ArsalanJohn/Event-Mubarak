package com.example.fyp;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.fyp.Adapters.CustomerPagerAdapter;
import com.example.fyp.Adapters.VendorViewPagerAdapter;

public class VendorView extends AppCompatActivity {
    ViewPager viewPager;
    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_view);

        viewPager = findViewById(R.id.vendorViewviewPager);
        tabLayout = findViewById(R.id.vendorViewtabLayout);

        tabLayout.setupWithViewPager(viewPager);
        final VendorViewPagerAdapter adapter = new VendorViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);




    }
}
