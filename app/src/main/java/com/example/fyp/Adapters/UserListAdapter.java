package com.example.fyp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fyp.Model.Classes.Users;
import com.example.fyp.Model.Classes.VendorsList;
import com.example.fyp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.VendorsListViewHolder> {
Context context;
ArrayList<Users> list;

    public UserListAdapter(Context context, ArrayList<Users> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public VendorsListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.userlistdesign,null);

        return new VendorsListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VendorsListViewHolder vendorsListViewHolder, int i) {

        Users listobj = list.get(i);
        Picasso.get().load(listobj.getProfileimage()).into(vendorsListViewHolder.user_img);

//         vendorsListViewHolder.user_img.setImageResource(listobj.getVendorsImg());
        vendorsListViewHolder.User_name.setText(listobj.getName());
        vendorsListViewHolder.User_address.setText(listobj.getAddress());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class VendorsListViewHolder extends RecyclerView.ViewHolder {
        ImageView user_img;
        TextView User_name;
        TextView User_address;

        public VendorsListViewHolder(@NonNull View itemView) {
            super(itemView);

            user_img = itemView.findViewById(R.id.userimg);
            User_name = itemView.findViewById(R.id.user_name);
            User_address = itemView.findViewById(R.id.user_address);

        }
    }

}
