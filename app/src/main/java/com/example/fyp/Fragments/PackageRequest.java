package com.example.fyp.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fyp.Adapters.PkgRequestListAdapter;
import com.example.fyp.Adapters.RequestListAdapter;
import com.example.fyp.Model.Classes.AddCompanyPackageReq;
import com.example.fyp.Model.Classes.RequestListClass;
import com.example.fyp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PackageRequest extends Fragment {
    RecyclerView recyclerView;

    public PackageRequest() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_package_request, container, false);
        recyclerView = view.findViewById(R.id.PkgrequestList);
        final ArrayList<RequestListClass> reqList = new ArrayList<>();
        final ArrayList<String> reqListNode = new ArrayList<>();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        final PkgRequestListAdapter adapter = new PkgRequestListAdapter(getContext(),reqList,reqListNode);
        recyclerView.setAdapter(adapter);


        database.getReference().child("PkgRequest").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    reqListNode.add(snapshot.getKey());
                    AddCompanyPackageReq addCompanyPackageReq = snapshot.getValue(AddCompanyPackageReq.class);

                    reqList.add(new RequestListClass(addCompanyPackageReq.getPkgName(),addCompanyPackageReq.getPkgPrice(),
                            addCompanyPackageReq.getPkgImage()));

                    adapter.notifyDataSetChanged();



                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        return view;
    }

}
