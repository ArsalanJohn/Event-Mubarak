package com.example.fyp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fyp.Model.Classes.Message;
import com.example.fyp.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MessagesAdapter extends RecyclerView.Adapter {

    /*
    1- Create two layouts
    2- Create an message class
    3- Create an adapter class
    4- Create two View Holders
    5- Create ItemViewType
    6- Check if message sender id equals to current logged in user id
    7- Inflate layouts according to layout types
    8- Bind data with the same logic in itemviewtype
    9- Happy Birthday To You.
     */

     Context context;
     ArrayList<Message> messages;

    int ITEM_VIEW_TYPE_SENDER = 1;
    int ITEM_VIEW_TYPE_RECEIVER = 2;

    public MessagesAdapter(Context context, ArrayList<Message> messages) {
        this.context = context;
        this.messages = messages;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = null;
        if(i == ITEM_VIEW_TYPE_SENDER) {
            view = LayoutInflater.from(context).inflate(R.layout.sendermsgdesign, null);
            return new SenderViewHolder(view);
        }
        else {
            view = LayoutInflater.from(context).inflate(R.layout.receivermsgdesign, null);
            return new RecieverViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        if(messages.get(position).getSenderId().equals(userid)) {
            return ITEM_VIEW_TYPE_SENDER;
        } else {
            return ITEM_VIEW_TYPE_RECEIVER;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        if(messages.get(position).getSenderId().equals(userid)) {

            SenderViewHolder s = ((SenderViewHolder)viewHolder);

            s.bindMessage(messages.get(position).getMessage());

        } else {
            ((RecieverViewHolder)viewHolder).bindMessage(messages.get(position).getMessage());
        }



    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class SenderViewHolder extends RecyclerView.ViewHolder {

        TextView message;
        public SenderViewHolder(@NonNull View itemView) {
            super(itemView);
            this.message = itemView.findViewById(R.id.senderMsg);
        }

        // At Line#75 we are sending message content into this function and
        // setting into textview.
        void bindMessage(String content) {
            this.message.setText(content);
        }

    }

    public class RecieverViewHolder extends RecyclerView.ViewHolder {

        TextView message;
        public RecieverViewHolder(@NonNull View itemView) {
            super(itemView);
            this.message = itemView.findViewById(R.id.receiverMsg);
        }
        void bindMessage(String content)
        {
            this.message.setText(content);
        }
    }
}
