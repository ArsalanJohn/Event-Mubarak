package com.example.fyp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.fyp.Adapters.DealsAdapter;
import com.example.fyp.Adapters.HomeCatAdapter;
import com.example.fyp.Model.Classes.AddCompanyPackage;
import com.example.fyp.Model.Classes.CompanyClass;
import com.example.fyp.Model.Classes.HomeCategory;
import com.example.fyp.Model.Classes.HomeDeals;
import com.example.fyp.Model.Classes.Users;
import com.example.fyp.RecycleListener.RecyclerItemClickListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
RecyclerView catRecyclerView,dealRecylerView;
DrawerLayout drawerLayout;
NavigationView navigationView;
Toolbar toolbar;
ProgressDialog pd;
    FirebaseAuth auth;
    ArrayList<String> nodeKeyList;
    FirebaseDatabase database;
    Menu menu;
    MenuItem admin,vendor;
ActionBarDrawerToggle actionBarDrawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        * Intializing Variables of Xml*/
        catRecyclerView = findViewById(R.id.categories_recycleView);
        dealRecylerView = findViewById(R.id.deals_recycleView);
pd = new ProgressDialog(this);
pd.setMessage("Loading...");
pd.setCancelable(false);
pd.show();
        catRecyclerView.setNestedScrollingEnabled(false);
        dealRecylerView.setNestedScrollingEnabled(false);
        nodeKeyList = new ArrayList<>();
        navigationView= findViewById(R.id.navigationView);
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        final ArrayList<HomeDeals> list = new ArrayList<>();
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this);
        dealRecylerView.setLayoutManager(linearLayoutManager1);
menu = navigationView.getMenu();
admin = menu.findItem(R.id.adminpanel);
        vendor = menu.findItem(R.id.vendorPanel);

        final DealsAdapter adapter1 = new DealsAdapter(this,list);
        dealRecylerView.setAdapter(adapter1);
/*
* Intializing complete
* */


/* Calling function for setting Toolbar*/
        setUpToolbar();


        /*Setting Drawer Navigation icon color to original color that is #E009DE*/
navigationView.setItemIconTintList(null);
/*navigationSetClickListener*/
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.account:
                        if (auth.getCurrentUser()!=null){
                        startActivity(new Intent(MainActivity.this,CustomerProfileActivity.class));
                        }
                        else {
                            startActivity(new Intent(MainActivity.this,SignInActivity.class));
                        }
                    break;
                    case R.id.bookEvent:
                        startActivity(new Intent(MainActivity.this,CategoryActivity.class));
                        break;
                    case R.id.companies:
                        startActivity(new Intent(MainActivity.this,CategoryActivity.class));
                        break;
                    case R.id.chat:
                        if (auth.getCurrentUser() !=null){
                            database.getReference().child("Users").child(auth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    Users users = dataSnapshot.getValue(Users.class);
                                    String tA = users.getUserType();
                                    if (tA.equals("Admin")){
                                        startActivity(new Intent(MainActivity.this,UserListActivity.class));

                                    }
                                    else {

                                        startActivity(new Intent(MainActivity.this,ChatActivity.class));

                                    }

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }
                        else {
                            startActivity(new Intent(MainActivity.this,SignInActivity.class));
                        }
                        break;
                    case R.id.aboutUs:
                        startActivity(new Intent(MainActivity.this,AboutUs.class));
                        break;
                    case R.id.vendorPanel:
                        startActivity(new Intent(MainActivity.this,VendorPanelActivity.class));
                        break;
                    case R.id.adminpanel:
                        startActivity(new Intent(MainActivity.this,AdminPanel.class));
                        break;
                }

                return false;
            }
        });

try{
database.getReference().child("Users").child(auth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
   Users users = dataSnapshot.getValue(Users.class);
     if ("Admin".equals(users.getUserType())){
         admin.setVisible(true);
     }
     else if ("Vendor".equals(users.getUserType())){
         vendor.setVisible(true);
     }
     else {
         admin.setVisible(false);
         vendor.setVisible(false);


     }

    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
});

}
catch (Exception e){
    e.toString();
}











/* List for Categories*/
        final ArrayList<HomeCategory> arrayList = new ArrayList<>();
        arrayList.add(new HomeCategory(R.drawable.photography,"Photographers"));
        arrayList.add(new HomeCategory(R.drawable.catering,"Caterers"));
        arrayList.add(new HomeCategory(R.drawable.transport,"Transports"));
        arrayList.add(new HomeCategory(R.drawable.place,"Places"));
        /* list complete*/

        /* List for Deals*/
        database.getReference().child("Deals").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                nodeKeyList.clear();
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    nodeKeyList.add(snapshot.getKey());
                    AddCompanyPackage companypkg = snapshot.getValue(AddCompanyPackage.class);
final String DealName = companypkg.getPkgName();
final String DealImage =companypkg.getPkgImage();
database.getReference().child("Company").child(companypkg.getCompanyId()).addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        CompanyClass companyClass = dataSnapshot.getValue(CompanyClass.class);
        String CompanyName = companyClass.getComp_Name();
        String CompanyAdd = companyClass.getComp_Address();
        list.add(new HomeDeals(DealImage,DealName,CompanyName,CompanyAdd));

        adapter1.notifyDataSetChanged();

    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
});


///loopFinish
                }
pd.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



//       list.add(new HomeDeals(R.drawable.catering,"Eid Offer","Monal","Liberty Market Lahore"));
//        list.add(new HomeDeals(R.drawable.photography,"Birthday Offer","Disnep Land","New Garden Lahore"));
//        list.add(new HomeDeals(R.drawable.catering,"Birthday Offer","Disnep Land","New Garden Lahore"));
//        list.add(new HomeDeals(R.drawable.place,"Birthday Offer","Disnep Land","New Garden Lahore"));
//        list.add(new HomeDeals(R.drawable.catering,"Birthday Offer","Disnep Land","New Garden Lahore"));
//        list.add(new HomeDeals(R.drawable.transport,"Birthday Offer","Disnep Land","New Garden Lahore"));

        /* list complete*/


        /* Setting LAyout Managers for RecyclerView*/
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        catRecyclerView.setLayoutManager(linearLayoutManager);

//        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3,LinearLayoutManager.HORIZONTAL);
//        catRecyclerView.setLayoutManager(staggeredGridLayoutManager);




        /* Layout Manager complete*/


        /* Setting Adapters for RecyclerView */
        HomeCatAdapter adapter = new HomeCatAdapter(this,arrayList);
        catRecyclerView.setAdapter(adapter);

        /* Setting Adapters for RecyclerView completed */


        //OnclickListener

        catRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, catRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent it =new Intent(MainActivity.this,VendorsListActivity.class);
                it.putExtra("Cat_Name",arrayList.get(position).getHomeCatText());
                startActivity(it);

            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
        dealRecylerView.addOnItemTouchListener(new RecyclerItemClickListener(this, catRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (FirebaseAuth.getInstance().getCurrentUser()!=null){
        Intent it =new Intent(MainActivity.this,DealsPackagesDetailActivity.class);
        it.putExtra("DealId",nodeKeyList.get(position));
                    it.putExtra("Type","Deals");

                    startActivity(it);
                }
                else {
                    startActivity(new Intent(MainActivity.this,SignInActivity.class));
                }
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));







    }

    public void setUpToolbar(){
        drawerLayout= findViewById(R.id.drawerLayout);
        toolbar= findViewById(R.id.homeToolbar);
        /*Setting up toolbar*/
        setSupportActionBar(toolbar);
actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.DrawerOpen,R.string.DrawerClose);
        actionBarDrawerToggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.colorPrimaryDark));

drawerLayout.addDrawerListener(actionBarDrawerToggle);
actionBarDrawerToggle.syncState();
    }

    public void onBackPressed() {
        DrawerLayout layout = (DrawerLayout)findViewById(R.id.drawerLayout);
        if (layout.isDrawerOpen(GravityCompat.START)) {
            layout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}
