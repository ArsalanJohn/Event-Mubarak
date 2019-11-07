package com.example.fyp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignInActivity extends AppCompatActivity {

    TextView create_account;
    EditText usernameSignin,passwordSignin;
    String set_uernameSignin,set_passwordSignin;
    Button SigninBtn;
    FirebaseAuth auth;
    FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        usernameSignin = findViewById(R.id.Signin_username);
        passwordSignin = findViewById(R.id.Signin_password);
        SigninBtn = findViewById(R.id.signin_btn);
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        create_account = findViewById(R.id.go_to_signup);



        create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInActivity.this,SignUpActivity.class));
            }
        });


        SigninBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                set_passwordSignin = passwordSignin.getText().toString();
                set_uernameSignin = usernameSignin.getText().toString();

                auth.signInWithEmailAndPassword(set_uernameSignin,set_passwordSignin).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            startActivity(new Intent(SignInActivity.this,MainActivity.class));
                            finish();


                        }
                        else {

                            Toast.makeText(SignInActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();


                        }
                    }
                });

            }
        });






    }
}
