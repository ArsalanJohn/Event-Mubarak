package com.example.fyp.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fyp.Adapters.CompanyImageAdapter;
import com.example.fyp.Model.Classes.CompanyClass;
import com.example.fyp.Model.Classes.CompanyImageBar;
import com.example.fyp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;


public class AddCompany extends Fragment {
Spinner CompanyType;
EditText C_name,C_address,C_owner,C_est,C_sp,C_num;
    String set_C_name,set_C_address,set_C_owner,set_C_est,set_C_sp,set_C_num;
FirebaseAuth auth;
FirebaseDatabase database;
TextView ImageSel;
Button C_add_btn;
ArrayList<String> C_image_url_array;
String C_type_C;
RecyclerView imgRecycle;
String Image_url;
    CompanyImageAdapter adapter1;
ArrayList<CompanyImageBar> image_array;
    ArrayList<String> image_c;
    public AddCompany() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_company, container, false);
        CompanyType = view.findViewById(R.id.company_type_spinner);
        C_name = view.findViewById(R.id.company_name_add);
        C_address = view.findViewById(R.id.company_address_add);
        CompanyType = view.findViewById(R.id.company_type_spinner);
        C_owner = view.findViewById(R.id.company_owner_add);
        C_est = view.findViewById(R.id.company_established_add);
        C_sp = view.findViewById(R.id.company_startPrice_add);
        C_num = view.findViewById(R.id.company_num);
        ImageSel = view.findViewById(R.id.selImgTxt);
        C_add_btn = view.findViewById(R.id.add_company_btn);
        imgRecycle = view.findViewById(R.id.addedcompanyimages);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        image_array = new ArrayList<>();
        image_c = new ArrayList<>();


LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
imgRecycle.setLayoutManager(linearLayoutManager);
        adapter1 = new CompanyImageAdapter(getContext(),image_array);
        imgRecycle.setAdapter(adapter1);


        ImageSel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent();
                i.setAction(Intent.ACTION_GET_CONTENT);
                i.setType("image/*");
                startActivityForResult(i,20);

            }
        });




        C_image_url_array = new ArrayList<>();
        final ArrayList<String> C_type = new ArrayList<>();
        C_type.add("Photographers");
        C_type.add("Caterers");
        C_type.add("Transports");
        C_type.add("Places");
        C_type.add("Invitation Cards");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1,C_type);
        CompanyType.setAdapter(adapter);

        CompanyType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                C_type_C= C_type.get(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        C_add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_C_name = C_name.getText().toString();
                set_C_address = C_address.getText().toString();
                set_C_owner = C_owner.getText().toString();
                set_C_est = C_est.getText().toString();
                set_C_sp = C_sp.getText().toString();
                set_C_num = C_num.getText().toString();

                if (set_C_name.isEmpty() || set_C_address.isEmpty() || set_C_owner.isEmpty() ||
                        set_C_est.isEmpty()|| set_C_sp.isEmpty() ||
                        set_C_num.isEmpty()){
                    Toast.makeText(getContext(), "Must Fill all Fields", Toast.LENGTH_SHORT).show();

                }
                else {
                    if (image_array.size()==0){
                        Toast.makeText(getContext(), "Must Upload Image of Company", Toast.LENGTH_SHORT).show();

                    }
                    else {

                        CompanyClass companyClass = new CompanyClass(set_C_name,set_C_address,set_C_num,
                                set_C_owner,C_type_C,set_C_est,set_C_sp,image_c
                                );
database.getReference().child("Company").child(auth.getCurrentUser().getUid()).setValue(companyClass).addOnCompleteListener(new OnCompleteListener<Void>() {
    @Override
    public void onComplete(@NonNull Task<Void> task) {
        if (task.isSuccessful()){
            Toast.makeText(getContext(), "Company Registered", Toast.LENGTH_SHORT).show();
        }
        else {

            Toast.makeText(getContext(), "Company Not Registered", Toast.LENGTH_SHORT).show();

        }
    }
});

//Else Finish
                    }

                }





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
                            Image_url = uri.toString();
                            image_array.add(new CompanyImageBar(Image_url));
                            image_c.add(Image_url);
                            adapter1.notifyDataSetChanged();
                        }
                    });
                }
            });



        }




    }



}
