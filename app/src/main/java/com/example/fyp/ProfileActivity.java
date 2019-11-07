package com.example.fyp;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

import com.example.fyp.Adapters.PagerAdapter;
import com.example.fyp.Fragments.DealsFragment;
import com.example.fyp.Fragments.DetailsFragment;
import com.example.fyp.Fragments.PackagesFragment;
import com.example.fyp.R;

public class ProfileActivity extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;
    String NodePass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
String Node = getIntent().getStringExtra("Node");
        NodePass = Node;

//        Bundle bundle = new Bundle();
//        bundle.putString("Node1", Node);
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
//        DealsFragment dealsFragment = new DealsFragment();
//        dealsFragment.setArguments(bundle);
//        DetailsFragment detailsFragment = new DetailsFragment();
//        detailsFragment.setArguments(bundle);
//        PackagesFragment packagesFragment = new PackagesFragment();
//        packagesFragment.setArguments(bundle);

        tabLayout.setupWithViewPager(viewPager);
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);



    }

    public String sendData() {
        return NodePass;
    }


}
