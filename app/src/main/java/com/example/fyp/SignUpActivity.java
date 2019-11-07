package com.example.fyp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.fyp.Model.Classes.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
    EditText user_name,user_email,user_password,user_phone,user_cnic,user_city,user_address;
    RadioGroup radioGroup;
    String user_type="Customer",set_user_name,set_user_address,set_user_email,set_user_password,set_user_phone,set_user_cnic,set_user_city;
    Button signup;
    FirebaseAuth auth;
    FirebaseDatabase database;
    Users users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        user_name = findViewById(R.id.Signup_name);
        user_email = findViewById(R.id.Signup_email);
        user_password = findViewById(R.id.Signup_password);
        user_phone = findViewById(R.id.Signup_phone);
        user_cnic = findViewById(R.id.Signup_cnic);
        user_city = findViewById(R.id.Signup_city);
        user_address = findViewById(R.id.Signup_address);

        radioGroup = findViewById(R.id.UserTypeRadioGrp);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        signup = findViewById(R.id.signup_btn);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if(checkedId == R.id.CustomerBtn) {
                    user_type = "Customer";
                } else if(checkedId == R.id.VendorBtn) {
                    user_type = "Vendor";
                } else {
                    user_type = "Admin";

                }
            }

        });







        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                set_user_cnic = user_cnic.getText().toString();
                set_user_email = user_email.getText().toString();
                set_user_name = user_name.getText().toString();
                set_user_password = user_password.getText().toString();
                set_user_phone = user_phone.getText().toString();
                set_user_city = user_city.getText().toString();
                set_user_address = user_address.getText().toString();

                users = new Users(set_user_name,set_user_email,set_user_cnic,set_user_phone,user_type,set_user_city,"https://www.volanno.com/wp-content/uploads/MaleAvatar.jpg",set_user_address);
                auth.createUserWithEmailAndPassword(set_user_email,set_user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            database.getReference().child("Users").
                                    child(auth.getCurrentUser().getUid()).setValue(users);
                            startActivity(new Intent(SignUpActivity.this,MainActivity.class));
                            finish();


                        }

                        else {
                            Toast.makeText(SignUpActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }

                    }
                });


            }
        });












    }
}
