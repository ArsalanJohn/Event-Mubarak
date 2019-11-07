package com.example.fyp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import com.example.fyp.Adapters.CustomerPagerAdapter;
import com.example.fyp.Fragments.CustomerProfile;
import com.example.fyp.Model.Classes.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class CustomerProfileActivity extends AppCompatActivity {
    ViewPager viewPager;
    TabLayout tabLayout;
    Toolbar toolbar;
ImageView Dp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_profile);

        toolbar = findViewById(R.id.CustomerProfileToolbar);
        viewPager = findViewById(R.id.customerprofileviewPager);
        tabLayout = findViewById(R.id.customerprofiletabLayout);
        Dp = findViewById(R.id.customerprofileimage);
        setSupportActionBar(toolbar);
        tabLayout.setupWithViewPager(viewPager);
        final CustomerPagerAdapter adapter = new CustomerPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        database.getReference().child("Users").child(auth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Users users = new Users();
                users = dataSnapshot.getValue(Users.class);


                Picasso.get().load(users.getProfileimage()).into(Dp);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    /* Option Menu Created
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.customeroptionmenu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection

        switch (item.getItemId()) {
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(this,MainActivity.class));
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }


    }




}
