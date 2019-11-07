package com.example.fyp.Fragments;

import android.app.Activity;
import android.content.Intent;
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
import com.example.fyp.DealsPackagesDetailActivity;
import com.example.fyp.MainActivity;
import com.example.fyp.Model.Classes.AddCompanyPackage;
import com.example.fyp.Model.Classes.CompanyClass;
import com.example.fyp.Model.Classes.CompanyDeals;
import com.example.fyp.Model.Classes.CompanyPackages;
import com.example.fyp.ProfileActivity;
import com.example.fyp.R;
import com.example.fyp.RecycleListener.RecyclerItemClickListener;
import com.example.fyp.VendorsListActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class DealsFragment extends Fragment {
    FirebaseAuth auth;
    FirebaseDatabase database;
    Activity context = getActivity();
String Node1="jgcyjdtyfkuybgyugiul3" +
        "265565";
    RecyclerView recyclerView;

    public DealsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
   View view = inflater.inflate(R.layout.fragment_deals, container, false);
        database=FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        ProfileActivity activity = (ProfileActivity)getActivity();
        Node1= activity.sendData();
      //Node1 = getArguments().getString("Node1");
        recyclerView = view.findViewById(R.id.dealdetailrecyclerview);
       final ArrayList<CompanyDeals> list = new ArrayList<>();
        final ArrayList<String> NodeKey = new ArrayList<>();

//        list.add(new CompanyDeals(R.drawable.transport,"January Offer","Riaz Photographer","Nishat RA bazar"
//                ,"250000"
//                ));
//        list.add(new CompanyDeals(R.drawable.place,"January Offer","Riaz Photographer","Nishat RA bazar"
//               ,"250000"
//        ));
//        list.add(new CompanyDeals(R.drawable.catering,"January Offer","Riaz Photographer","Nishat RA bazar"
//               ,"250000"
//        ));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));

        final CompanyDealsAdapter adapter = new CompanyDealsAdapter(getContext(),list);
        recyclerView.setAdapter(adapter);

     database.getReference().child("Deals").addValueEventListener(new ValueEventListener() {
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



        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent it =new Intent(getActivity(), DealsPackagesDetailActivity.class);
                it.putExtra("DealId",NodeKey.get(position));
                it.putExtra("Type","Deals");

                startActivity(it);

            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));



   return view;
    }

}