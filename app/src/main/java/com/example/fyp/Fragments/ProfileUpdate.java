package com.example.fyp.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fyp.Model.Classes.Users;
import com.example.fyp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class ProfileUpdate extends Fragment {
    FirebaseAuth auth;
    FirebaseDatabase database;
    Users users;
    EditText name,city,cnic,num,add,oldpass,newpass;
    ImageView cus_dp;
    Button update;
    String ImageUrl = "https://www.volanno.com/wp-content/uploads/MaleAvatar.jpg";
    String Setname,Setcity,Setcnic,Setnum,Setadd,Setoldpass,Setnewpass,Setemail,Setusertype;
int ImageReqCode = 75;
    public ProfileUpdate() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_update, container, false);
        name = view.findViewById(R.id.cus_name_update_d);
        city = view.findViewById(R.id.cus_update_city);
        cnic = view.findViewById(R.id.cus_update_cnic_num);
        num = view.findViewById(R.id.cus_update_num);
        add = view.findViewById(R.id.cus_address_update_d);
        oldpass = view.findViewById(R.id.cus_old_password);
        newpass = view.findViewById(R.id.cus_new_password);
        update = view.findViewById(R.id.update_cus_btn);
        cus_dp = view.findViewById(R.id.cus_update_dp);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        database.getReference().child("Users").child(auth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                users = dataSnapshot.getValue(Users.class);

           Setemail = users.getEmail();
                Setusertype = users.getUserType();
                ImageUrl = users.getProfileimage();
                Picasso.get().load(ImageUrl).into(cus_dp);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Setname = name.getText().toString();
                Setcity = city.getText().toString();
                Setcnic = cnic.getText().toString();
                Setnum = num.getText().toString();
                Setadd = add.getText().toString();
                Setoldpass = oldpass.getText().toString();
                Setnewpass = newpass.getText().toString();
if (Setnewpass.isEmpty() && Setoldpass.isEmpty()){
    if (Setname.isEmpty() || Setemail.isEmpty() || Setcnic.isEmpty() ||
            Setnum.isEmpty()|| Setusertype.isEmpty() ||
            Setcity.isEmpty()|| Setadd.isEmpty()){
        Toast.makeText(getContext(), "Must Fill all Fields", Toast.LENGTH_SHORT).show();

    }
    else {
        Users user_update = new Users(Setname,Setemail,Setcnic,
                Setnum,Setusertype,
                Setcity,ImageUrl,Setadd
        );
        database.getReference().child("Users").
                child(auth.getCurrentUser().getUid()).setValue(user_update);
        Toast.makeText(getContext(), "Update Successful", Toast.LENGTH_SHORT).show();
    }
}

else {


    if (Setname.isEmpty() || Setemail.isEmpty() || Setcnic.isEmpty() ||
            Setnum.isEmpty()|| Setusertype.isEmpty() ||
            Setcity.isEmpty()|| Setadd.isEmpty()){
        Toast.makeText(getContext(), "Must Fill all Fields", Toast.LENGTH_SHORT).show();

    }
    else {

        AuthCredential credential = EmailAuthProvider.getCredential(Setemail,Setoldpass);

        auth.getCurrentUser().reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    auth.getCurrentUser().updatePassword(Setnewpass).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Users user_update = new Users(Setname,Setemail,Setcnic,
                                        Setnum,Setusertype,
                                        Setcity,ImageUrl,Setadd
                                );
                                database.getReference().child("Users").
                                        child(auth.getCurrentUser().getUid()).setValue(user_update);
                                Toast.makeText(getContext(), "Update Successful", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(getContext(), task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }else {
                    Toast.makeText(getContext(), task.getException().getLocalizedMessage()+"hello", Toast.LENGTH_SHORT).show();

                }
            }
        });






    }



            //Else Finish
}




            }
        });


cus_dp.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        Intent i = new Intent();
        i.setAction(Intent.ACTION_GET_CONTENT);
        i.setType("image/*");
        startActivityForResult(i, ImageReqCode);



    }
});









        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ImageReqCode) {
            final ProgressDialog pd = new ProgressDialog(getContext());
            pd.setMessage("Loading");
            pd.setCancelable(false);
            pd.show();
            Uri uri = data.getData();

            FirebaseStorage storage = FirebaseStorage.getInstance();
            String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
            StorageReference ref = storage.getReference().child("Images").child(id);
            ref.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            ImageUrl = uri.toString();
                            Picasso.get().load(ImageUrl).into(cus_dp);
pd.dismiss();
                        }
                    });
                }
            });
        }


    }





}
