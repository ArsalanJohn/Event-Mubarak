package com.example.fyp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fyp.Model.Classes.HomeDeals;
import com.example.fyp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DealsAdapter extends RecyclerView.Adapter<DealsAdapter.DealsViewHolder> {
Context context;
ArrayList<HomeDeals> arrayList;

    public DealsAdapter(Context context, ArrayList<HomeDeals> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public DealsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.dealdesign,null);

        return new DealsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DealsViewHolder dealsViewHolder, int i) {
        HomeDeals dealsObject = arrayList.get(i);
        dealsViewHolder.companyAddress.setText(dealsObject.getCompanyAddress());
        dealsViewHolder.companyName.setText(dealsObject.getCompanyName());
        dealsViewHolder.dealName.setText(dealsObject.getDealName());
        Picasso.get().load(dealsObject.getImage()).into(dealsViewHolder.dealImg);



    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class DealsViewHolder extends RecyclerView.ViewHolder {
        ImageView dealImg;
        TextView dealName;
        TextView companyName;
        TextView companyAddress;
        TextView details;

        public DealsViewHolder(@NonNull View itemView) {
            super(itemView);


            dealImg = itemView.findViewById(R.id.home_deal_img);
            dealName = itemView.findViewById(R.id.home_deal_name);
            companyName = itemView.findViewById(R.id.home_company_name);
            companyAddress = itemView.findViewById(R.id.home_company_address);
            details = itemView.findViewById(R.id.home_deal_details);



        }
    }


}
