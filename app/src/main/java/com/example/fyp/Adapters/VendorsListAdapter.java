package com.example.fyp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fyp.Model.Classes.VendorsList;
import com.example.fyp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class VendorsListAdapter extends RecyclerView.Adapter<VendorsListAdapter.VendorsListViewHolder> {
Context context;
ArrayList<VendorsList> list;

    public VendorsListAdapter(Context context, ArrayList<VendorsList> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public VendorsListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.vendorslistdesign,null);

        return new VendorsListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VendorsListViewHolder vendorsListViewHolder, int i) {

        VendorsList listobj = list.get(i);
        Picasso.get().load(listobj.getVendorsImg()).into(vendorsListViewHolder.VendorsImg);
        vendorsListViewHolder.VendorsName.setText(listobj.getVendorsName());
        vendorsListViewHolder.VendorsAddress.setText(listobj.getVendorsAddress());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class VendorsListViewHolder extends RecyclerView.ViewHolder {
        ImageView VendorsImg;
        TextView VendorsName;
        TextView VendorsAddress;

        public VendorsListViewHolder(@NonNull View itemView) {
            super(itemView);

            VendorsImg = itemView.findViewById(R.id.vendorimg);
            VendorsName = itemView.findViewById(R.id.vendors_company_name);
            VendorsAddress = itemView.findViewById(R.id.vendors_company_address);

        }
    }

}
