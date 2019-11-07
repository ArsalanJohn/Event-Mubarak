package com.example.fyp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.fyp.Adapters.MessagesAdapter;
import com.example.fyp.Model.Classes.Message;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {
RecyclerView msgRecycle;
EditText messageText;
FirebaseAuth auth;
FirebaseDatabase database;
String ChkType = "Abc";
    ArrayList<Message> messages;
    String rec_id = "tzI9AX5zu8QTBEGeSUD18IZmNu52";

Button snd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        msgRecycle = findViewById(R.id.msgRecycleView);
        messageText = findViewById(R.id.textEdit);
        snd = findViewById(R.id.sendText);
        messages = new ArrayList<>();
        ChkType = getIntent().getStringExtra("TypeAdmin");
        String sender_id = FirebaseAuth.getInstance().getCurrentUser().getUid();
if ("Admin".equals(ChkType)){
    rec_id = getIntent().getStringExtra("rec_id");
}
auth = FirebaseAuth.getInstance();
database = FirebaseDatabase.getInstance();
        final String reciever_sender = rec_id + sender_id;
        final String sender_reciever = sender_id + rec_id;

        final MessagesAdapter adapter = new MessagesAdapter(this,messages);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        msgRecycle.setLayoutManager(manager);
        msgRecycle.setAdapter(adapter);

//Read Message
        database.getReference().child("Messages").child(sender_reciever).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Clearing Messages of ArryList
                messages.clear();

                // Iterating All elements of Messages Array
                for(DataSnapshot child : dataSnapshot.getChildren()) {
                    // Converting Snapshot to Message Object
                    Message message = child.getValue(Message.class);
                    message.setMessageId(child.getKey());
                    // Adding Message to Messages ArrayList
                    messages.add(message);
                }

                // When new item is added scroll to bottom.
                msgRecycle.scrollToPosition(messages.size() - 1);

                // Notify adapter about change.
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


//OnClickStart
        snd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Getting Sender Id
                final String senderId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                // Getting Message of Message Box
                final String message = messageText.getText().toString();

                // Clear messagebox
                messageText.setText("");

                // Adding element in sender chat room
                database.getReference().child("Messages").child(sender_reciever).push().setValue(new Message(message, senderId)).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // If addition is successful add in reciever chat room.
                        database.getReference().child("Messages").child(reciever_sender).push().setValue(new Message(message, senderId));
                    }
                });
            }
        });


    }
}
