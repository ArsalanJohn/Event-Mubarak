package com.example.fyp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fyp.Model.Classes.CompanyImageBar;
import com.example.fyp.Model.Classes.HomeDeals;
import com.example.fyp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CompanyImageAdapter extends RecyclerView.Adapter<CompanyImageAdapter.DealsViewHolder> {
Context context;
ArrayList<CompanyImageBar> arrayList;

    public CompanyImageAdapter(Context context, ArrayList<CompanyImageBar> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public DealsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.companyimagedesign,null);

        return new DealsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DealsViewHolder dealsViewHolder, int i) {
        CompanyImageBar dealsObject = arrayList.get(i);
        Picasso.get().load(dealsObject.getCImage()).into( dealsViewHolder.Img);


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class DealsViewHolder extends RecyclerView.ViewHolder {
        ImageView Img;


        public DealsViewHolder(@NonNull View itemView) {
            super(itemView);


            Img = itemView.findViewById(R.id.company_img_bar);




        }
    }


}
