package com.example.fyp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fyp.Model.Classes.BookedDeals;
import com.example.fyp.Model.Classes.CompanyDeals;
import com.example.fyp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BookedDealsAdapter extends RecyclerView.Adapter<BookedDealsAdapter.DealsViewHolder> {
Context context;
ArrayList<BookedDeals> arrayList;

    public BookedDealsAdapter(Context context, ArrayList<BookedDeals> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public DealsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.bookinglist,null);

        return new DealsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DealsViewHolder dealsViewHolder, int i) {
        BookedDeals dealsObject = arrayList.get(i);
        dealsViewHolder.companyAddress.setText(dealsObject.getCompanyAddress());
        dealsViewHolder.companyName.setText(dealsObject.getCompanyName());
        dealsViewHolder.dealName.setText(dealsObject.getDealName());
        Picasso.get().load(dealsObject.getImage()).into(dealsViewHolder.dealImg);
        dealsViewHolder.Date.setText(dealsObject.getDate());
        dealsViewHolder.BookDealPrice.setText(dealsObject.getPrice());

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
        TextView BookDealPrice;

        TextView Date;

        public DealsViewHolder(@NonNull View itemView) {
            super(itemView);

            BookDealPrice = itemView.findViewById(R.id.booked_deal_price);
            dealImg = itemView.findViewById(R.id.booked_deal_img);
            dealName = itemView.findViewById(R.id.booked_deal_name);
            companyName = itemView.findViewById(R.id.booked_deal_company_name);
            companyAddress = itemView.findViewById(R.id.booked_deal_company_address);
            Date = itemView.findViewById(R.id.booked_deal_date);



        }
    }


}
