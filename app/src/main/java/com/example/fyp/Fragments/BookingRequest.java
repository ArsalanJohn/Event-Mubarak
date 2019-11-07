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
import android.widget.Toast;

import com.example.fyp.Adapters.RequestListAdapter;
import com.example.fyp.Model.Classes.AddCompanyPackage;
import com.example.fyp.Model.Classes.BookingClass;
import com.example.fyp.Model.Classes.CompanyClass;
import com.example.fyp.Model.Classes.CompanyPackages;
import com.example.fyp.Model.Classes.RequestListClass;
import com.example.fyp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class BookingRequest extends Fragment {

RecyclerView recyclerView;
    String Cadd,Pname,img;
    public BookingRequest() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_booking_request, container, false);

recyclerView = view.findViewById(R.id.requestList);
        final ArrayList<RequestListClass> reqList = new ArrayList<>();
        final ArrayList<String> reqListNode = new ArrayList<>();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        final RequestListAdapter adapter = new RequestListAdapter(getContext(),reqList,reqListNode);
        recyclerView.setAdapter(adapter);




  database.getReference().child("BookingRequest").addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

          for (DataSnapshot snapshot:dataSnapshot.getChildren()){
              reqListNode.add(snapshot.getKey());
              BookingClass bookobj = snapshot.getValue(BookingClass.class);

 database.getReference().child("Company").child(bookobj.getCompanyId()).addValueEventListener(new ValueEventListener() {
     @Override
     public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
         CompanyClass cobj = dataSnapshot.getValue(CompanyClass.class);
         Cadd = cobj.getComp_Name();
     }

     @Override
     public void onCancelled(@NonNull DatabaseError databaseError) {

     }
 });
              database.getReference().child(bookobj.getType()).child(bookobj.getDealPackageId()).addValueEventListener(new ValueEventListener() {
                  @Override
                  public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                      AddCompanyPackage ccobj = dataSnapshot.getValue(AddCompanyPackage.class);

                      Pname = ccobj.getPkgName();
                      img = ccobj.getPkgImage();
                      reqList.add(new RequestListClass(Pname,Cadd,img));
                      adapter.notifyDataSetChanged();
                  }

                  @Override
                  public void onCancelled(@NonNull DatabaseError databaseError) {

                  }
              });
             // Toast.makeText(getContext(), Pname+"hello "+Cadd+" "+img, Toast.LENGTH_SHORT).show();



          }
      }

      @Override
      public void onCancelled(@NonNull DatabaseError databaseError) {

      }
  });









        return view;
    }


}
