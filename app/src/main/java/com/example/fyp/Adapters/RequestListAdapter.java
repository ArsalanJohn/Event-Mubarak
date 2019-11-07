package com.example.fyp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fyp.Model.Classes.BookingClass;
import com.example.fyp.Model.Classes.RequestListClass;
import com.example.fyp.Model.Classes.Users;
import com.example.fyp.Model.Classes.VendorsList;
import com.example.fyp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RequestListAdapter extends RecyclerView.Adapter<RequestListAdapter.RequestListViewHolder> {
Context context;
    String Uid;
ArrayList<RequestListClass> list;
ArrayList<String> Node;
FirebaseDatabase database;
FirebaseAuth auth;
String userType;
int index;

    public RequestListAdapter(Context context, ArrayList<RequestListClass> list,ArrayList<String> node) {
        this.context = context;
        this.list = list;
        this.Node = node;

    }

    @NonNull
    @Override
    public RequestListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.requestlistdesign,null);

        return new RequestListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RequestListViewHolder requestListViewHolder, int a) {

        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        index =a;

        RequestListClass listobj = list.get(index);

        Picasso.get().load(listobj.getImg()).into(requestListViewHolder.PkgImg);

        requestListViewHolder.PkgName.setText(listobj.getPkgName());

        requestListViewHolder.Company.setText(listobj.getAddress());


        requestListViewHolder.approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

database.getReference().child("BookingRequest").child(Node.get(index)).addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

   final BookingClass bookingClass = dataSnapshot.getValue(BookingClass.class);
        try {
            //  Block of code to try

            Uid = bookingClass.getUserId();
        }
        catch(Exception e) {
            //  Block of code to handle errors
            e.toString();
        }

   database.getReference().child("Users").child(Uid).addValueEventListener(new ValueEventListener() {
       @Override
       public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
Users users = dataSnapshot.getValue(Users.class);
           userType =  users.getUserType();


if ("Vendor".equals(userType)){

 database.getReference().child("Bookings").child(Uid).push().setValue(bookingClass).addOnCompleteListener(new OnCompleteListener<Void>() {
     @Override
     public void onComplete(@NonNull Task<Void> task) {

if (task.isSuccessful()){

    database.getReference().child("BookingRequest").child(Node.get(index)).setValue(null).addOnCompleteListener(new OnCompleteListener<Void>() {
        @Override
        public void onComplete(@NonNull Task<Void> task) {

            if (task.isSuccessful()){
                Node.remove(index);

                list.remove(index);
                notifyDataSetChanged();

            }

        }
    });

}

     }
 });
}
//ifFinish
else {

    database.getReference().child("CusBooking").child(Uid).push().setValue(bookingClass).addOnCompleteListener(new OnCompleteListener<Void>() {
        @Override
        public void onComplete(@NonNull Task<Void> task) {
       if (task.isSuccessful()){

           database.getReference().child("BookingRequest").child(Node.get(index)).setValue(null).addOnCompleteListener(new OnCompleteListener<Void>() {
               @Override
               public void onComplete(@NonNull Task<Void> task) {

                   if (task.isSuccessful()){
                       Node.remove(index);

                       list.remove(index);
                       notifyDataSetChanged();

                   }

               }
           });





       }
        }
    });


}



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



            }
        });


        requestListViewHolder.reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

  database.getReference().child("BookingRequest").child(Node.get(index)).setValue(null).addOnCompleteListener(new OnCompleteListener<Void>() {
      @Override
      public void onComplete(@NonNull Task<Void> task) {
     if (task.isSuccessful()){

         list.remove(index);
         Node.remove(index);

         notifyDataSetChanged();


     }


      }
  });






            }
        });






    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RequestListViewHolder extends RecyclerView.ViewHolder {
        ImageView PkgImg;
        TextView PkgName;
        TextView Company;
        TextView approve,reject;

        public RequestListViewHolder(@NonNull View itemView) {
            super(itemView);

            PkgImg = itemView.findViewById(R.id.req_img);
            PkgName = itemView.findViewById(R.id.req_name);
            Company = itemView.findViewById(R.id.comp_name);
            approve = itemView.findViewById(R.id.approvetxt);
            reject = itemView.findViewById(R.id.rejecttxt);


        }
    }

}
