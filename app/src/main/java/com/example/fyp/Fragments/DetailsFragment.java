package com.example.fyp.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fyp.Adapters.CompanyDealsAdapter;
import com.example.fyp.Adapters.CompanyImageAdapter;
import com.example.fyp.MainActivity;
import com.example.fyp.Model.Classes.AddCompanyPackage;
import com.example.fyp.Model.Classes.CompanyClass;
import com.example.fyp.Model.Classes.CompanyImageBar;
import com.example.fyp.Model.Classes.CompanyPackages;
import com.example.fyp.ProfileActivity;
import com.example.fyp.R;
import com.example.fyp.RecycleListener.RecyclerItemClickListener;
import com.example.fyp.ShowImageActivity;
import com.example.fyp.VendorsListActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class DetailsFragment extends Fragment {
RecyclerView recyclerView;
TextView Cname,Caddress,Cest,Csp,Cowner;
    FirebaseAuth auth;
    FirebaseDatabase database;
    String Node1="jgcyjdtyfkuybgyugiul3";
String imgurl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT0X55iRKuvZX8QiFFD6TvGBfa74Rr09FY1Ej6jVTssmzywB9B7";
    public DetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view = inflater.inflate(R.layout.fragment_details, container, false);
        recyclerView = view.findViewById(R.id.companyimages);
        Cname = view.findViewById(R.id.company_name_de);
        Caddress = view.findViewById(R.id.company_address_de);
        Cest = view.findViewById(R.id.company_established_de);
        Csp = view.findViewById(R.id.company_startPrice_de);
        Cowner = view.findViewById(R.id.company_owner_de);

        database=FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        ProfileActivity activity = (ProfileActivity)getActivity();
        Node1= activity.sendData();



final ArrayList<CompanyImageBar> list = new ArrayList<>();


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

      final   CompanyImageAdapter adapter = new CompanyImageAdapter(getContext(),list);
        recyclerView.setAdapter(adapter);



        //DatabaseStart
        database.getReference().child("Company").child(Node1).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                CompanyClass companyClass = dataSnapshot.getValue(CompanyClass.class);
                Cname.setText(companyClass.getComp_Name());
                Caddress.setText(companyClass.getComp_Address());
                Cest.setText(companyClass.getComp_Estab());
                Csp.setText(companyClass.getComp_Sp());
                Cowner.setText(companyClass.getComp_Owner());
                database.getReference().child("Company").child(Node1).child("comp_images").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        list.clear();
                        for (DataSnapshot snapshot:dataSnapshot.getChildren()){

                            String URL = snapshot.getValue(String.class);
                            list.add(new CompanyImageBar(URL));
                            adapter.notifyDataSetChanged();

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent it =new Intent(getActivity(), ShowImageActivity.class);
                it.putExtra("URL",list.get(position).getCImage());
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