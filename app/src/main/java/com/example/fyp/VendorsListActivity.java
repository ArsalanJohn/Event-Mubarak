package com.example.fyp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fyp.Adapters.VendorsListAdapter;
import com.example.fyp.Fragments.DealsFragment;
import com.example.fyp.Fragments.DetailsFragment;
import com.example.fyp.Fragments.PackagesFragment;
import com.example.fyp.Model.Classes.CompanyClass;
import com.example.fyp.Model.Classes.VendorsList;
import com.example.fyp.RecycleListener.RecyclerItemClickListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class VendorsListActivity extends AppCompatActivity {
RecyclerView vendorListRecyclerView;
TextView category;
FirebaseAuth auth;
FirebaseDatabase database;
String CatName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendors_list);

        vendorListRecyclerView = findViewById(R.id.vendorsListRecyclerView);
        category = findViewById(R.id.Vendor_Cat_list_name);
database=FirebaseDatabase.getInstance();
auth = FirebaseAuth.getInstance();
       final ArrayList<VendorsList> arrayList = new ArrayList<>();
        final ArrayList<String> NodeId = new ArrayList<>();

        LinearLayoutManager linearLayoutManager = new
                LinearLayoutManager(this);

        vendorListRecyclerView.setLayoutManager(linearLayoutManager);
        vendorListRecyclerView.addItemDecoration(new DividerItemDecoration(vendorListRecyclerView.getContext(), DividerItemDecoration.VERTICAL));

       final VendorsListAdapter adapter = new VendorsListAdapter(this,arrayList);
        vendorListRecyclerView.setAdapter(adapter);

        CatName = getIntent().getStringExtra("Cat_Name");
        category.setText(CatName);

        database.getReference().child("Company").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    CompanyClass companyClass = snapshot.getValue(CompanyClass.class);
                  if (companyClass.getComp_Type().equals(CatName)) {
                      NodeId.add(snapshot.getKey());
                      ArrayList<String> CImage = companyClass.getComp_images();
                      arrayList.add(new VendorsList(CImage.get(0)
                              ,companyClass.getComp_Name(),companyClass.getComp_Address()));

                      adapter.notifyDataSetChanged();


                  }



                }
                if (arrayList.size() == 0){
                    Toast.makeText(VendorsListActivity.this,
                            "No Company Registerd from this Category", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


//        ArrayList<VendorsList> arrayList = new ArrayList<>();
//        arrayList.add(new VendorsList(R.drawable.photography,"Konica","Defence Lahore"));
//        arrayList.add(new VendorsList(R.drawable.place,"Ali Studio","NIshat Lahore"));
//        arrayList.add(new VendorsList(R.drawable.transport,"Digital Studio","Kahana Kacha"));
//        arrayList.add(new VendorsList(R.drawable.catering,"John Studio","Gulberg Lahore"));
//        arrayList.add(new VendorsList(R.drawable.place,"Konica","Defence Lahore"));
//        arrayList.add(new VendorsList(R.drawable.catering,"Monica","Defence Gujranwala"));
//        arrayList.add(new VendorsList(R.drawable.transport,"Sheroz Digital Studio","Kot Lakh pat Lahore"));



        vendorListRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, vendorListRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent it =new Intent(VendorsListActivity.this,ProfileActivity.class);
              it.putExtra("Node",NodeId.get(position));
                startActivity(it);


            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));






    }


}
