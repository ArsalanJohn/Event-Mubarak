package com.example.fyp.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.fyp.Adapters.UserListAdapter;
import com.example.fyp.Adapters.VendorsListAdapter;
import com.example.fyp.AdminPanel;
import com.example.fyp.Model.Classes.Users;
import com.example.fyp.Model.Classes.VendorsList;
import com.example.fyp.ProfileActivity;
import com.example.fyp.ProfileViewActivity;
import com.example.fyp.R;
import com.example.fyp.RecycleListener.RecyclerItemClickListener;
import com.example.fyp.VendorsListActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class UserList extends Fragment {
    RecyclerView UserListRecyclerView;
    ArrayList<Users> users;


    public UserList() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_user_list, container, false);
        users = new ArrayList<>();
        final ArrayList<String > UNode = new ArrayList<>();

        UserListRecyclerView = view.findViewById(R.id.adminUserList);

//        arrayList.add(new VendorsList(R.drawable.photography+"","Konica","Defence Lahore"));
//        arrayList.add(new VendorsList(R.drawable.place,"Ali Studio","NIshat Lahore"));
//        arrayList.add(new VendorsList(R.drawable.transport,"Digital Studio","Kahana Kacha"));
//        arrayList.add(new VendorsList(R.drawable.catering,"John Studio","Gulberg Lahore"));
//        arrayList.add(new VendorsList(R.drawable.place,"Konica","Defence Lahore"));
//        arrayList.add(new VendorsList(R.drawable.catering,"Monica","Defence Gujranwala"));
//        arrayList.add(new VendorsList(R.drawable.transport,"Sheroz Digital Studio","Kot Lakh pat Lahore"));

        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        LinearLayoutManager linearLayoutManager = new
                LinearLayoutManager(getContext());

        UserListRecyclerView.setLayoutManager(linearLayoutManager);
        UserListRecyclerView.addItemDecoration(new DividerItemDecoration(UserListRecyclerView.getContext(), DividerItemDecoration.VERTICAL));

        final UserListAdapter adapter = new UserListAdapter(getContext(),users);
        UserListRecyclerView.setAdapter(adapter);


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






        UserListRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), UserListRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

Intent it = new Intent(getContext(), ProfileViewActivity.class);
it.putExtra("Node",UNode.get(position));
startActivity(it);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));












        return view;
    }

}
