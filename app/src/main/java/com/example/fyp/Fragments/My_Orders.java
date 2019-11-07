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

import com.example.fyp.Adapters.BookedDealsAdapter;
import com.example.fyp.Model.Classes.AddCompanyPackage;
import com.example.fyp.Model.Classes.BookedDeals;
import com.example.fyp.Model.Classes.BookingClass;
import com.example.fyp.Model.Classes.CompanyClass;
import com.example.fyp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class My_Orders extends Fragment {
    RecyclerView recyclerView;
    FirebaseAuth auth;
    String pkgName;
    String PImg;
    String Pprice;
    String pkg_Name,CompanyName,CompanyAddress,PkgPrice,datee,URLL;
    String Type,NodeType="abc";
    ArrayList<String> nodeName;
    FirebaseDatabase database;

    public My_Orders() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my__orders, container, false);

        recyclerView = view.findViewById(R.id.OrderRecycle);
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        nodeName =new ArrayList<>();
        final ArrayList<BookedDeals> list = new ArrayList<>();


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));

        final BookedDealsAdapter adapter = new BookedDealsAdapter(getContext(),list);
        recyclerView.setAdapter(adapter);


        database.getReference().child("Bookings").child(auth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    nodeName.add(snapshot.getKey());
                    final BookingClass bookingClass = snapshot.getValue(BookingClass.class);

                    datee = bookingClass.getDd()+"-"+bookingClass.getMm()+"-"+bookingClass.getYy();

                    database.getReference().child(bookingClass.getType()).child(bookingClass.getDealPackageId()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            AddCompanyPackage addCompanyPackage = dataSnapshot.getValue(AddCompanyPackage.class);

                            pkg_Name = addCompanyPackage.getPkgName();
                            PkgPrice = addCompanyPackage.getPkgPrice();
                            URLL = addCompanyPackage.getPkgImage();

                            database.getReference().child("Company").child(bookingClass.getCompanyId()).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    CompanyClass companyClass = dataSnapshot.getValue(CompanyClass.class);
                                    CompanyName = companyClass.getComp_Name();
                                    CompanyAddress = companyClass.getComp_Address();



                                    list.add(new BookedDeals(URLL,pkg_Name,CompanyName,CompanyAddress,datee,PkgPrice));
                                    adapter.notifyDataSetChanged();


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

                    //BookedDeals bookedDeals = new BookedDeals()
                    // tobeContinued



                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });








        return view;
    }


}
