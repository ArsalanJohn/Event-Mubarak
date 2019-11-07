package com.example.fyp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.fyp.Adapters.CategoryActivityAdapter;
import com.example.fyp.Adapters.HomeCatAdapter;
import com.example.fyp.Model.Classes.HomeCategory;
import com.example.fyp.RecycleListener.RecyclerItemClickListener;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {
    RecyclerView catRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        catRecyclerView = findViewById(R.id.category_recycleView);
        final ArrayList<HomeCategory> arrayList = new ArrayList<>();
        arrayList.add(new HomeCategory(R.drawable.photography,"Photographers"));
        arrayList.add(new HomeCategory(R.drawable.catering,"Caterers"));
        arrayList.add(new HomeCategory(R.drawable.transport,"Transports"));
        arrayList.add(new HomeCategory(R.drawable.place,"Places"));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        catRecyclerView.setLayoutManager(linearLayoutManager);
        CategoryActivityAdapter adapter = new CategoryActivityAdapter(this,arrayList);
        catRecyclerView.setAdapter(adapter);

        catRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, catRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent it =new Intent(CategoryActivity.this,VendorsListActivity.class);
                it.putExtra("Cat_Name",arrayList.get(position).getHomeCatText());
                startActivity(it);

            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));


    }
}
