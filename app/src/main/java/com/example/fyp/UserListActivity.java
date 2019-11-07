package com.example.fyp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.fyp.Adapters.UserListAdapter;
import com.example.fyp.Model.Classes.Users;
import com.example.fyp.RecycleListener.RecyclerItemClickListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserListActivity extends AppCompatActivity {
RecyclerView UserList;
ArrayList<Users> users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        users = new ArrayList<>();
       final ArrayList<String > UNode = new ArrayList<>();

        UserList = findViewById(R.id.UserListRecycle);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        UserList.setLayoutManager(linearLayoutManager);
        final UserListAdapter adapter = new UserListAdapter(this,users);
        UserList.setAdapter(adapter);
     database.getReference().child("Users").addValueEventListener(new ValueEventListener() {
         @Override
         public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
             for (DataSnapshot snapshot:dataSnapshot.getChildren()){
             UNode.add(snapshot.getKey());
             Users user = snapshot.getValue(Users.class);
             users.add(new Users(user.getName(),user.getEmail(),
                     user.getCnic(),user.getPhone(),user.getUserType(),
                     user.getCity(),user.getProfileimage(),user.getAddress()
                     ));
                 adapter.notifyDataSetChanged();

             }


         }

         @Override
         public void onCancelled(@NonNull DatabaseError databaseError) {

         }
     });


        UserList.addOnItemTouchListener(new RecyclerItemClickListener(this, UserList, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent it =new Intent(UserListActivity.this,ChatActivity.class);
                it.putExtra("rec_id",UNode.get(position));
                it.putExtra("TypeAdmin","Admin");

                startActivity(it);

            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));



    }
}
