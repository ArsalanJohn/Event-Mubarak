package com.example.fyp.Fragments;

import android.content.Context;
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
import com.example.fyp.Model.Classes.AddCompanyPackage;
import com.example.fyp.Model.Classes.CompanyClass;
import com.example.fyp.Model.Classes.CompanyDeals;
import com.example.fyp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class VendorPackageFragment extends Fragment {
    FirebaseAuth auth;
    FirebaseDatabase database;

    RecyclerView recyclerView;

    public VendorPackageFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_vendor_package, container, false);


        recyclerView = view.findViewById(R.id.Vendorpackagedetailrecyclerview);
        final ArrayList<CompanyDeals> list = new ArrayList<>();
        final ArrayList<String> NodeKey = new ArrayList<>();
        database=FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));

        final CompanyDealsAdapter adapter = new CompanyDealsAdapter(getContext(),list);
        recyclerView.setAdapter(adapter);

        database.getReference().child("Packages").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    AddCompanyPackage pkg = snapshot.getValue(AddCompanyPackage.class);
                    if (pkg.getCompanyId().equals(auth.getCurrentUser().getUid())){
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
                                list.add(new CompanyDeals(Dimg,Dname,DCname,DCadd
                                        ,Dprice
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



        return view;
    }

}
