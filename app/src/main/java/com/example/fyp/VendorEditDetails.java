package com.example.fyp;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.fyp.Adapters.VendorEditPagerAdapter;
import com.example.fyp.Adapters.VendorViewPagerAdapter;

public class VendorEditDetails extends AppCompatActivity {
    ViewPager viewPager;
    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_edit_details);

        viewPager = findViewById(R.id.vendoreditviewPager);
        tabLayout = findViewById(R.id.vendoredittabLayout);

        tabLayout.setupWithViewPager(viewPager);
        final VendorEditPagerAdapter adapter = new VendorEditPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);




    }
}
