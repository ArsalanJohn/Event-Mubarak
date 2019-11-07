package com.example.fyp.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.fyp.Model.Classes.AddCompanyPackage;
import com.example.fyp.Model.Classes.AddCompanyPackageReq;
import com.example.fyp.Model.Classes.CompanyClass;
import com.example.fyp.Model.Classes.CompanyImageBar;
import com.example.fyp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;


public class AddPackage extends Fragment {
    Spinner SpinnerType;
    String SpinnerType_Text;
    ArrayList<String> spin_data;
    EditText pkgName,pkgPrice,pkgDes;
    String SetpkgName,SetpkgPrice,SetpkgDes;
FirebaseDatabase database;
FirebaseAuth auth;
    Button AddPkg;
    ImageView pkgImg;
    String imgURL = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT0X55iRKuvZX8QiFFD6TvGBfa74Rr09FY1Ej6jVTssmzywB9B7";
    int a =0;
    String id = "abc";
    public AddPackage() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_package, container, false);
        pkgDes =view.findViewById(R.id.vendor_pkg_description);
        pkgName =view.findViewById(R.id.pkg_name_add);
        pkgPrice =view.findViewById(R.id.vendor_pkg_price);
        AddPkg =view.findViewById(R.id.add_pkg_btn);
        pkgImg =view.findViewById(R.id.pkg_pic);

        database = FirebaseDatabase.getInstance();
auth = FirebaseAuth.getInstance();
final ProgressDialog pd = new ProgressDialog(getContext());
pd.setMessage("wait");
pd.setCancelable(false);
pd.show();
        Picasso.get().load(imgURL).into(pkgImg);

        SpinnerType = view.findViewById(R.id.packageSpinner);
        spin_data = new ArrayList<>();
        spin_data.add("Deals");
        spin_data.add("Packages");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1,spin_data);
        SpinnerType.setAdapter(adapter);

        SpinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinnerType_Text = spin_data.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        database.getReference().child("Company").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    String uID = snapshot.getKey();
                    if (uID.equals(auth.getCurrentUser().getUid())){
                        id = uID;
                    }
                }
                pd.dismiss();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
AddPkg.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        if (auth.getCurrentUser().getUid().equals(id)){

            SetpkgDes = pkgDes.getText().toString();
            SetpkgPrice = pkgPrice.getText().toString();
            SetpkgName = pkgName.getText().toString();

            if (SetpkgName.isEmpty() || SetpkgPrice.isEmpty() || SetpkgDes.isEmpty()){
                Toast.makeText(getContext(), "Must Fill all Fields", Toast.LENGTH_SHORT).show();
            }
            else {

                AddCompanyPackageReq addCompanyPackage = new AddCompanyPackageReq(SetpkgName,SetpkgPrice,SetpkgDes,
                        auth.getCurrentUser().getUid(),imgURL,SpinnerType_Text
                );

      database.getReference().child("PkgRequest").push().setValue(addCompanyPackage).addOnCompleteListener(new OnCompleteListener<Void>() {
          @Override
          public void onComplete(@NonNull Task<Void> task) {
         if (task.isSuccessful()){
             Toast.makeText(getContext(), "Succesfully Requested", Toast.LENGTH_SHORT).show();
         }

         else {
             Toast.makeText(getContext(), task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();

         }


          }
      });



//                if ("Packages".equals(SpinnerType_Text)){
//                    database.getReference().child("Packages").push().setValue(addCompanyPackage).addOnCompleteListener(new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//                            if (task.isSuccessful()){
//                                Toast.makeText(getContext(), "Package Added", Toast.LENGTH_SHORT).show();
//                            }
//                            else {
//                                Toast.makeText(getContext(), task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });
//                    //IfFinish
//                }
//                else {
//
//                    database.getReference().child("Deals").push().setValue(addCompanyPackage).addOnCompleteListener(new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//                            if (task.isSuccessful()){
//                                Toast.makeText(getContext(), "Deal Added", Toast.LENGTH_SHORT).show();
//                            }
//                            else {
//                                Toast.makeText(getContext(), task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });
//
//
//                }
                //elsefininsh
            }



        }

else {
            Toast.makeText(getContext(), "First Registered Your Company", Toast.LENGTH_SHORT).show();
        }



    }
});

        pkgImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent();
                i.setAction(Intent.ACTION_GET_CONTENT);
                i.setType("image/*");
                startActivityForResult(i,20);


            }
        });






        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==20){
            Date date = new Date();
            Uri uri = data.getData();
            FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
            StorageReference ref = firebaseStorage.getReference().child("Images").child(date+" ");
            ref.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            imgURL = uri.toString();
                            Picasso.get().load(imgURL).into(pkgImg);


                        }
                    });
                }
            });



        }




    }





}
