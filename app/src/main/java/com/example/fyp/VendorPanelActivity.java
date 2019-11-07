package com.example.fyp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.fyp.Adapters.VendorsListAdapter;
import com.example.fyp.Adapters.VendorsPanelOptionAdapter;
import com.example.fyp.Model.Classes.VendorsList;
import com.example.fyp.Model.Classes.VendorsOption;
import com.example.fyp.RecycleListener.RecyclerItemClickListener;

import java.util.ArrayList;

public class VendorPanelActivity extends AppCompatActivity {
    RecyclerView vendorPanelRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_panel);

        vendorPanelRecyclerView = findViewById(R.id.vendorpaneloptions);

        ArrayList<VendorsOption> arrayList = new ArrayList<>();
        arrayList.add(new VendorsOption("Add",R.drawable.plus));
        arrayList.add(new VendorsOption("View",R.drawable.eye));






        LinearLayoutManager linearLayoutManager = new
                LinearLayoutManager(this);

        vendorPanelRecyclerView.setLayoutManager(linearLayoutManager);
        vendorPanelRecyclerView.addItemDecoration(new DividerItemDecoration(vendorPanelRecyclerView.getContext(), DividerItemDecoration.VERTICAL));

        VendorsPanelOptionAdapter adapter = new VendorsPanelOptionAdapter(this,arrayList);
        vendorPanelRecyclerView.setAdapter(adapter);

        vendorPanelRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, vendorPanelRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
if (position==0){
                startActivity(new Intent(VendorPanelActivity.this,VendorEditDetails.class));
}
else {

    startActivity(new Intent(VendorPanelActivity.this,VendorView.class));

}

            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));

    }
}
