package com.example.fyp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fyp.Model.Classes.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ProfileViewActivity extends AppCompatActivity {
ImageView Pimg;
TextView Pname,Pcnic,Pcity,Padd,Pnum,pemail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);
String node = getIntent().getStringExtra("Node");

        Pimg = findViewById(R.id.PImageView);
        Pname =findViewById(R.id.Pname);
        Pcnic = findViewById(R.id.Pcnic);
        Pcity = findViewById(R.id.Pcity);
        Padd = findViewById(R.id.Paddress);
        Pnum = findViewById(R.id.Pphone);
        pemail = findViewById(R.id.Pemail);
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        database.getReference().child("Users").child(node).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Users users = dataSnapshot.getValue(Users.class);

                Pname.setText(users.getName());
                Pcnic.setText(users.getCnic());
                Pcity.setText(users.getCity());
                Padd.setText(users.getAddress());
                Pnum.setText(users.getPhone());
                pemail.setText(users.getEmail());
                Picasso.get().load(users.getProfileimage()).into(Pimg);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}
