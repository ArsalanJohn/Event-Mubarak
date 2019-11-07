package com.example.fyp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fyp.Model.Classes.HomeCategory;
import com.example.fyp.R;

import java.util.ArrayList;

public class HomeCatAdapter extends RecyclerView.Adapter<HomeCatAdapter.HomeCatViewHolder> {
Context context;
ArrayList<HomeCategory> arrayList;

    public HomeCatAdapter(Context context, ArrayList<HomeCategory> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public HomeCatViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.categorydesign,null);

        return new HomeCatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeCatViewHolder homeCatViewHolder, int i) {

        HomeCategory catObject = arrayList.get(i);

        homeCatViewHolder.CatImg.setImageResource(catObject.getHomeCatImg());
        homeCatViewHolder.CatText.setText(catObject.getHomeCatText());



    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class HomeCatViewHolder extends RecyclerView.ViewHolder {
        ImageView CatImg;
        TextView CatText;

        public HomeCatViewHolder(@NonNull View itemView) {
            super(itemView);


            CatImg = itemView.findViewById(R.id.home_category_img);

            CatText = itemView.findViewById(R.id.home_category_text);



        }
    }

}
