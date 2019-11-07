package com.example.fyp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fyp.Model.Classes.AddCompanyPackage;
import com.example.fyp.Model.Classes.CompanyClass;
import com.example.fyp.Model.Classes.HomeDeals;
import com.example.fyp.Model.Classes.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class DealsPackagesDetailActivity extends AppCompatActivity {
TextView dealName,dealPrice,Companyname,CompanyAddress,BookedBy,dealDes;
Button dealBookBtn;
String DealId;
    String cId;
    String type ="Deals";

FirebaseDatabase database;
FirebaseAuth auth;
ImageView pkgImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deals_packages_detail);

        dealName = findViewById(R.id.pkgdetailname);
        dealPrice = findViewById(R.id.pkgdetailprice);
        Companyname = findViewById(R.id.company_name_detail);
        CompanyAddress = findViewById(R.id.cmpny_add_detail);
        BookedBy = findViewById(R.id.pkgdetailbookedBy);
        dealDes = findViewById(R.id.pkgdetaildescription);
        dealBookBtn = findViewById(R.id.Dealbookbtn);
        pkgImage = findViewById(R.id.pkgdetailimg);

        database = FirebaseDatabase.getInstance();
auth = FirebaseAuth.getInstance();
DealId = getIntent().getStringExtra("DealId");
        type = getIntent().getStringExtra("Type");

if ("Packages".equals(type)){
    ///StartrPackages
    database.getReference().child("Packages").child(DealId).addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            AddCompanyPackage companypkg = dataSnapshot.getValue(AddCompanyPackage.class);
            final String DealName = companypkg.getPkgName();
            final String DealImage =companypkg.getPkgImage();
            final  String DealPrice = companypkg.getPkgPrice();
            final  String DealDes = companypkg.getPkgDes();
            cId = companypkg.getCompanyId();
            database.getReference().child("Company").child(companypkg.getCompanyId()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    CompanyClass companyClass = dataSnapshot.getValue(CompanyClass.class);
                    final String CompanyName = companyClass.getComp_Name();
                    final   String CompanyAdd = companyClass.getComp_Address();

                    database.getReference().child("Users").child(auth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            Users users = dataSnapshot.getValue(Users.class);
                            String Uname = users.getName();

                            dealName.setText(DealName);
                            dealPrice.setText(DealPrice);
                            dealDes.setText(DealDes);
                            Companyname.setText(CompanyName);
                            CompanyAddress.setText(CompanyAdd);
                            BookedBy.setText(Uname);
                            Picasso.get().load(DealImage).into(pkgImage);



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


///loopFinish


        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });
    ///ClosePackages

}
else {

    database.getReference().child("Deals").child(DealId).addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            AddCompanyPackage companypkg = dataSnapshot.getValue(AddCompanyPackage.class);
            final String DealName = companypkg.getPkgName();
            final String DealImage = companypkg.getPkgImage();
            final String DealPrice = companypkg.getPkgPrice();
            final String DealDes = companypkg.getPkgDes();
            cId = companypkg.getCompanyId();
            database.getReference().child("Company").child(companypkg.getCompanyId()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    CompanyClass companyClass = dataSnapshot.getValue(CompanyClass.class);
                    final String CompanyName = companyClass.getComp_Name();
                    final String CompanyAdd = companyClass.getComp_Address();

                    database.getReference().child("Users").child(auth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            Users users = dataSnapshot.getValue(Users.class);
                            String Uname = users.getName();

                            dealName.setText(DealName);
                            dealPrice.setText(DealPrice);
                            dealDes.setText(DealDes);
                            Companyname.setText(CompanyName);
                            CompanyAddress.setText(CompanyAdd);
                            BookedBy.setText(Uname);
                            Picasso.get().load(DealImage).into(pkgImage);


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


///loopFinish


        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });
}

        dealBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(DealsPackagesDetailActivity.this,BookingActivity.class);
                it.putExtra("Node",DealId);
                it.putExtra("CNode",cId);
                it.putExtra("Type",type);

                startActivity(it);
            }
        });





    }
}
