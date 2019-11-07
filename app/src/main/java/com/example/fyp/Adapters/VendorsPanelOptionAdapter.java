package com.example.fyp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fyp.Model.Classes.VendorsOption;
import com.example.fyp.R;

import java.util.ArrayList;

public class VendorsPanelOptionAdapter extends RecyclerView.Adapter<VendorsPanelOptionAdapter.VendorsListViewHolder> {
Context context;
ArrayList<VendorsOption> list;

    public VendorsPanelOptionAdapter(Context context, ArrayList<VendorsOption> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public VendorsListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.vendorpaneloptiondesign,null);

        return new VendorsListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VendorsListViewHolder vendorsListViewHolder, int i) {

        VendorsOption listobj = list.get(i);
         vendorsListViewHolder.VendorsImg.setImageResource(listobj.getImg());
        vendorsListViewHolder.VendorsName.setText(listobj.getName());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class VendorsListViewHolder extends RecyclerView.ViewHolder {
        ImageView VendorsImg;
        TextView VendorsName;

        public VendorsListViewHolder(@NonNull View itemView) {
            super(itemView);

            VendorsImg = itemView.findViewById(R.id.iconimg);
            VendorsName = itemView.findViewById(R.id.optionName);

        }
    }

}
