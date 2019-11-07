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

import com.example.fyp.Adapters.CompanyDealsAdapter;
import com.example.fyp.Adapters.CompanyPackagesAdapter;
import com.example.fyp.DealsPackagesDetailActivity;
import com.example.fyp.Model.Classes.AddCompanyPackage;
import com.example.fyp.Model.Classes.CompanyClass;
import com.example.fyp.Model.Classes.CompanyDeals;
import com.example.fyp.Model.Classes.CompanyPackages;
import com.example.fyp.ProfileActivity;
import com.example.fyp.R;
import com.example.fyp.RecycleListener.RecyclerItemClickListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class PackagesFragment extends Fragment {
    FirebaseAuth auth;
    FirebaseDatabase database;
    String Node1="jgcyjdtyfkuybgyugiul3";
    RecyclerView packagerecyclerView;

    public PackagesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_packages, container, false);
        packagerecyclerView = view.findViewById(R.id.packagerecyclerview);
        database=FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        ProfileActivity activity = (ProfileActivity)getActivity();
        Node1= activity.sendData();

        final ArrayList<CompanyPackages> list = new ArrayList<>();
        final ArrayList<String> NodeKey = new ArrayList<>();

//        list.add(new CompanyPackages(R.drawable.transport,"Birthday Offer"
//                ,"250000"
//        ));
//        list.add(new CompanyPackages(R.drawable.place,"Waleema Offer","250000"));
//        list.add(new CompanyPackages(R.drawable.catering,"Mehndi Offer"
//                ,"250000"
//        ));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        packagerecyclerView.setLayoutManager(linearLayoutManager);

        packagerecyclerView.addItemDecoration(new DividerItemDecoration(packagerecyclerView.getContext(), DividerItemDecoration.VERTICAL));

      final   CompanyPackagesAdapter adapter = new CompanyPackagesAdapter(getContext(),list);
        packagerecyclerView.setAdapter(adapter);

//DatabaseStart
        database.getReference().child("Packages").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    AddCompanyPackage pkg = snapshot.getValue(AddCompanyPackage.class);
                    if (pkg.getCompanyId().equals(Node1)){
                        NodeKey.add(snapshot.getKey());
                        final String Dname = pkg.getPkgName();
                        final String Dimg=pkg.getPkgImage();

                        final String Dprice = pkg.getPkgPrice();
                        database.getReference().child("Company").child(pkg.getCompanyId()).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                CompanyClass companyClass = dataSnapshot.getValue(CompanyClass.class);
                                String DCname=companyClass.getComp_Name();
                                String DCadd = companyClass.getComp_Address();
                                list.add(new CompanyPackages(Dimg,Dname,Dprice
                                ));
                                adapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });


///IF Finish
                    }


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        packagerecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), packagerecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent it =new Intent(getActivity(), DealsPackagesDetailActivity.class);
                it.putExtra("DealId",NodeKey.get(position));
                it.putExtra("Type","Packages");

                startActivity(it);

            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));






///DatabaseClose
        return view;
    }

    }