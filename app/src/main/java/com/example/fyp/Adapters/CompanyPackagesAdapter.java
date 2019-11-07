package com.example.fyp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fyp.Model.Classes.CompanyDeals;
import com.example.fyp.Model.Classes.CompanyPackages;
import com.example.fyp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CompanyPackagesAdapter extends RecyclerView.Adapter<CompanyPackagesAdapter.DealsViewHolder> {
Context context;
ArrayList<CompanyPackages> arrayList;

    public CompanyPackagesAdapter(Context context, ArrayList<CompanyPackages> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public DealsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.companypackagedesign,null);

        return new DealsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DealsViewHolder dealsViewHolder, int i) {
        CompanyPackages dealsObject = arrayList.get(i);
        dealsViewHolder.dealName.setText(dealsObject.getDealName());
        Picasso.get().load(dealsObject.getImage()).into(dealsViewHolder.dealImg);

       // dealsViewHolder.dealImg.setImageResource(dealsObject.getImage());
        dealsViewHolder.price.setText(dealsObject.getPrice()+"/-");

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class DealsViewHolder extends RecyclerView.ViewHolder {
        ImageView dealImg;
        TextView dealName;
        TextView price;

        public DealsViewHolder(@NonNull View itemView) {
            super(itemView);


            dealImg = itemView.findViewById(R.id.deal_img);
            dealName = itemView.findViewById(R.id.deal_name);
            price = itemView.findViewById(R.id.deal_details_price);



        }
    }


}
