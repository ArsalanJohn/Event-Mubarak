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

import java.util.ArrayList;

public class PackageRequestAdapter extends RecyclerView.Adapter<PackageRequestAdapter.VendorsListViewHolder> {
Context context;
ArrayList<VendorsList> list;

    public PackageRequestAdapter(Context context, ArrayList<VendorsList> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public VendorsListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.packagerequestlistdesign,null);

        return new VendorsListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VendorsListViewHolder vendorsListViewHolder, int i) {

        VendorsList listobj = list.get(i);
        // vendorsListViewHolder.PackageImg.setImageResource(listobj.getVendorsImg());
        vendorsListViewHolder.PackageName.setText(listobj.getVendorsName());
        vendorsListViewHolder.PackageCompanyName.setText(listobj.getVendorsAddress());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class VendorsListViewHolder extends RecyclerView.ViewHolder {
        ImageView PackageImg;
        TextView PackageName;
        TextView PackageCompanyName;

        public VendorsListViewHolder(@NonNull View itemView) {
            super(itemView);

            PackageImg = itemView.findViewById(R.id.packageImg);
            PackageName = itemView.findViewById(R.id.pkg_name);
            PackageCompanyName = itemView.findViewById(R.id.pkg_company_name);

        }
    }

}
