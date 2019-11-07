package com.example.fyp.Fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.fyp.Model.Classes.BookingClass;
import com.example.fyp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;


public class AddOrders extends Fragment {
TextView SelectDate,Date,SelectTime,Time;
Button chk,book;
Calendar calendar;
DatePickerDialog dpd;
TimePickerDialog tpd;
int Day_of_month,Month,Year,hours,mints;
int dd,mm,yy,hr,mn=0;
int abc = 0;
    int abc2 = 0;

FirebaseAuth auth;
FirebaseDatabase database;
    public AddOrders() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_orders, container, false);






auth = FirebaseAuth.getInstance();
database = FirebaseDatabase.getInstance();
    SelectDate = view.findViewById(R.id.selectDate);
        Date = view.findViewById(R.id.Date);
        SelectTime = view.findViewById(R.id.selectTime);
        Time = view.findViewById(R.id.Time);

        chk = view.findViewById(R.id.chkAvail);
        book = view.findViewById(R.id.BookAvail);


        SelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calendar = Calendar.getInstance();
                Day_of_month = calendar.get(Calendar.DAY_OF_MONTH);
                Month = calendar.get(Calendar.MONTH);
                Year = calendar.get(Calendar.YEAR);

                dpd = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Date.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                        dd= dayOfMonth;
                        mm=month;
                        yy=year;
                    }
                },Year,Month,Day_of_month);
dpd.show();


            }
        });

        SelectTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calendar = Calendar.getInstance();
                hours = calendar.get(Calendar.HOUR_OF_DAY);
                mints = calendar.get(Calendar.MINUTE);

                tpd = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Time.setText(hourOfDay+" : "+minute);
                        hr = hourOfDay;
                        mn = minute;

                    }
                },hours,mints,false);
                tpd.show();


            }
        });

chk.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if (yy==0 || hr ==0){
            Toast.makeText(getContext(), "Must Put Date and Time", Toast.LENGTH_SHORT).show();
        }
        else {

            /**/
            abc=0;
            abc2=0;
            database.getReference().child("Bookings").child(auth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot:dataSnapshot.getChildren()){
int h1 = hr+1;
int h2 = hr+2;
                        BookingClass bookingClass = snapshot.getValue(BookingClass.class);

                        if (bookingClass.getDd().equals(dd+"")&&
                                bookingClass.getMm().equals(mm+"")&&bookingClass.getYy().equals(yy+"")){
                            abc++;
                            if (bookingClass.getHr().equals(hr+"")|| bookingClass.getHr().equals(h1+"")||
                                    bookingClass.getHr().equals(h2+"")){
                                 abc2++;
                                Toast.makeText(getContext(), "Sorry not Available", Toast.LENGTH_SHORT).show();
                                book.setVisibility(View.INVISIBLE);

                            }
                            else {
                                if (abc2==0){
                                book.setVisibility(View.VISIBLE);
                                }
                              else {
                                    book.setVisibility(View.INVISIBLE);

                                    Toast.makeText(getContext(), "Sorry not Available", Toast.LENGTH_SHORT).show();

                                }

                            }
                       ///
                        }


                    }
                    if (abc==0){
                        book.setVisibility(View.VISIBLE);
                    }
                    //
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });



            /**/







        }
    }
});

book.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        String ui = auth.getCurrentUser().getUid();
        BookingClass bk = new BookingClass(ui,ui," ",dd+"",
                mm+"",yy+"",hr+"",mn+"","Vendor");
        database.getReference().child("BookingRequest").push().setValue(bk).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(getContext(), "Request Successful", Toast.LENGTH_SHORT).show();
                    book.setVisibility(View.INVISIBLE);
                    Time.setText(" ");
                    Date.setText(" ");

                }
                else {
                    Toast.makeText(getContext(), task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        });


    }
});





        return view;
    }


}
