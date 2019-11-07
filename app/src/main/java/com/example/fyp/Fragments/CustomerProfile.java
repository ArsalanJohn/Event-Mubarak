package com.example.fyp.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fyp.Model.Classes.Users;
import com.example.fyp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


public class CustomerProfile extends Fragment {
    FirebaseAuth auth;
    FirebaseDatabase database;
    Users users;
    TextView name,city,cnic,num,add;
    ImageView cus_dp;
    public CustomerProfile() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_customer_profile, container, false);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        name = view.findViewById(R.id.cus_name_d);
        city = view.findViewById(R.id.cus_city);
        cnic = view.findViewById(R.id.cus_cnic_num);
        num = view.findViewById(R.id.cus_num);
        add = view.findViewById(R.id.cus_address_d);
        cus_dp = view.findViewById(R.id.cus_profile_dp);

        database.getReference().child("Users").child(auth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                users = dataSnapshot.getValue(Users.class);

                name.setText(users.getName());
                city.setText(users.getCity());
                cnic.setText(users.getCnic());
                num.setText(users.getPhone());
                add.setText(users.getAddress());
                Picasso.get().load(users.getProfileimage()).into(cus_dp);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });








        return view;
    }

}
