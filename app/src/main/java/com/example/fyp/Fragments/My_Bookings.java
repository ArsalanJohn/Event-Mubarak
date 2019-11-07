package com.example.fyp.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.fyp.Adapters.BookedDealsAdapter;
import com.example.fyp.Adapters.CompanyDealsAdapter;
import com.example.fyp.Model.Classes.AddCompanyPackage;
import com.example.fyp.Model.Classes.BookedDeals;
import com.example.fyp.Model.Classes.BookingClass;
import com.example.fyp.Model.Classes.CompanyClass;
import com.example.fyp.Model.Classes.CompanyDeals;
import com.example.fyp.Model.Classes.Users;
import com.example.fyp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class My_Bookings extends Fragment {

    RecyclerView recyclerView;
FirebaseAuth auth;
    String pkgName;
    String PImg;
    String Pprice;
    String pkg_Name,CompanyName,CompanyAddress,PkgPrice,datee,URLL;
    String Type,NodeType="abc";
    ArrayList<String> nodeName;
FirebaseDatabase database;
String URL= "https://ak5.picdn.net/shutterstock/videos/24678605/thumb/1.jpg";
    public My_Bookings() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my__bookings, container, false);
        recyclerView = view.findViewById(R.id.bookinglistrecyclerview);
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
nodeName =new ArrayList<>();
  final ArrayList<BookedDeals> list = new ArrayList<>();
//        list.add(new BookedDeals(URL,"January Offer","Riaz Photographer","Nishat RA bazar"
//                ,"05-05-2019","20000"
//        ));
//        list.add(new BookedDeals(URL,"January Offer","Riaz Photographer","Nishat RA bazar"
//                ,"21-05-2019","20000"
//        ));
//        list.add(new BookedDeals(URL,"January Offer","Riaz Photographer","Nishat RA bazar"
//                ,"25-05-2019","20000"
//        ));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));

       final BookedDealsAdapter adapter = new BookedDealsAdapter(getContext(),list);
        recyclerView.setAdapter(adapter);

        database.getReference().child("Users").child(auth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Users users = dataSnapshot.getValue(Users.class);
                Type = users.getUserType();
    if ("Vendor".equals(Type)){
        NodeType="Bookings";
    }
    if ("Customer".equals(Type)){
        NodeType = "CusBooking";
    }
        //IfFinish
   database.getReference().child("CusBooking").child(auth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
       @Override
       public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
for (DataSnapshot snapshot:dataSnapshot.getChildren()){
    nodeName.add(snapshot.getKey());
    final BookingClass bookingClass = snapshot.getValue(BookingClass.class);

    datee = bookingClass.getDd()+"-"+bookingClass.getMm()+"-"+bookingClass.getYy();

database.getReference().child(bookingClass.getType()).child(bookingClass.getDealPackageId()).addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
   AddCompanyPackage addCompanyPackage = dataSnapshot.getValue(AddCompanyPackage.class);
try{
   pkg_Name = addCompanyPackage.getPkgName();
   PkgPrice = addCompanyPackage.getPkgPrice();
URLL = addCompanyPackage.getPkgImage();
}
catch (Exception e){
    e.toString();
}
   database.getReference().child("Company").child(bookingClass.getCompanyId()).addValueEventListener(new ValueEventListener() {
       @Override
       public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

           CompanyClass companyClass = dataSnapshot.getValue(CompanyClass.class);
           CompanyName = companyClass.getComp_Name();
           CompanyAddress = companyClass.getComp_Address();



           list.add(new BookedDeals(URLL,pkg_Name,CompanyName,CompanyAddress,datee,PkgPrice));
           adapter.notifyDataSetChanged();


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

    //BookedDeals bookedDeals = new BookedDeals()
   // tobeContinued



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



//database.getReference().child("Bookings").addValueEventListener(new ValueEventListener() {
//    @Override
//    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//   for (DataSnapshot snapshot:dataSnapshot.getChildren()){
//
//       for (DataSnapshot snapshot1:snapshot.getChildren()){
//
//          final BookingClass bookingClass = snapshot1.getValue(BookingClass.class);
//           if (bookingClass.getUserId().equals(auth.getCurrentUser().getUid())){
//
//               final String dd = bookingClass.getDd();
//               final  String mm = bookingClass.getMm();
//               final  String yy = bookingClass.getYy();
//
/////packageInfo
//database.getReference().child("Packages").child(bookingClass.getDealPackageId()).addValueEventListener(new ValueEventListener() {
//    @Override
//    public void onDataChange(@NonNull DataSnapshot dataSnapshot3) {
//        AddCompanyPackage addCompanyPackage = dataSnapshot3.getValue(AddCompanyPackage.class);
//
//    pkgName = addCompanyPackage.getPkgName();
//   PImg = addCompanyPackage.getPkgImage();
//    Pprice = addCompanyPackage.getPkgPrice();
//
//
//
//        ///companyStart
//
//        database.getReference().child("Company").child(bookingClass.getCompanyId()).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                CompanyClass companyClass = dataSnapshot.getValue(CompanyClass.class);
//                String Cname = companyClass.getComp_Name();
//                String CAdd = companyClass.getComp_Address();
//String DateFormat = dd + "-"+ mm +"-"+yy;
//                list.add(new BookedDeals(PImg,pkgName,Cname,CAdd
//                        ,DateFormat,Pprice
//                ));
//
//                adapter.notifyDataSetChanged();
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//
//
//
//        ///companyClose
//
//    }
//
//    @Override
//    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//    }
//});
//
//
/////packageINfoClose
//               ///DealStart
//
//               database.getReference().child("Deals").child(bookingClass.getDealPackageId()).addValueEventListener(new ValueEventListener() {
//                   @Override
//                   public void onDataChange(@NonNull DataSnapshot dataSnapshot3) {
//                       AddCompanyPackage addCompanyPackage = dataSnapshot3.getValue(AddCompanyPackage.class);
//
//                       pkgName = addCompanyPackage.getPkgName();
//                       PImg = addCompanyPackage.getPkgImage();
//                       Pprice = addCompanyPackage.getPkgPrice();
//
//
//
//                       ///companyStart
//
//                       database.getReference().child("Company").child(bookingClass.getCompanyId()).addValueEventListener(new ValueEventListener() {
//                           @Override
//                           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                               CompanyClass companyClass = dataSnapshot.getValue(CompanyClass.class);
//                               String Cname = companyClass.getComp_Name();
//                               String CAdd = companyClass.getComp_Address();
//                               String DateFormat = dd + "-"+ mm +"-"+yy;
//                               list.add(new BookedDeals(PImg,pkgName,Cname,CAdd
//                                       ,DateFormat,Pprice
//                               ));
//
//                               adapter.notifyDataSetChanged();
//
//                           }
//
//                           @Override
//                           public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                           }
//                       });
//
//
//
//                       ///companyClose
//
//                   }
//
//                   @Override
//                   public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                   }
//               });
//
//
//
//               ///DealClose
//           }
//       }
//
//
//
//
//
//   }
//    }
//
//    @Override
//    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//    }
//});











        return view;
    }

}
