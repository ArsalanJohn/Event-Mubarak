package com.example.fyp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.fyp.Model.Classes.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class AdminLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        final FirebaseAuth auth = FirebaseAuth.getInstance();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        auth.createUserWithEmailAndPassword("fyp@gmail.com","123456").addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Users users = new Users("FYP","fyp@gmail.com","6568687486",
                            "03364526934","Admin","Lahore", "https://www.volanno.com/wp-content/uploads/MaleAvatar.jpg",
                            "Nishat"
                            );
                    database.getReference().child("Users").child(auth.getCurrentUser().getUid()).setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                       if (task.isSuccessful()){
                           Toast.makeText(AdminLogin.this, "SuccessFull", Toast.LENGTH_SHORT).show();
                       }
                           else {
                           Toast.makeText(AdminLogin.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                       }


                        }
                    });




                }
                else {
                    Toast.makeText(AdminLogin.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }




            }
        });









    }
}
