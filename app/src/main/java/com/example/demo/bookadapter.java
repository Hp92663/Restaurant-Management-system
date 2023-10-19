package com.example.demo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class bookadapter extends RecyclerView.Adapter<bookadapter.Myviewholder> {

    Context context;
    ArrayList<bookingmodel> llist;

    public bookadapter(Context context, ArrayList<bookingmodel> llist) {
        this.context = context;
        this.llist = llist;
    }

    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.itembook,parent,false);

        return new Myviewholder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull Myviewholder holder, int position) {
        holder.name.setText("Name : "+llist.get(position).getName());
        holder.date.setText("Date : "+llist.get(position).getDate());
        holder.email.setText("Email : "+llist.get(position).getEmail());
        holder.person.setText("No of Person : "+llist.get(position).getPerson());

    }


    @Override
    public int getItemCount() {
        return llist.size();
    }

    public static class Myviewholder extends RecyclerView.ViewHolder {
        TextView name,email,date,person;
        CardView card;
        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.text1);
            email = itemView.findViewById(R.id.text2);
            date = itemView.findViewById(R.id.text3);
            card = itemView.findViewById(R.id.card3);
            person = itemView.findViewById(R.id.uperson);
        }
    }
}
