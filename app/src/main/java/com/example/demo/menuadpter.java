package com.example.demo;

import static com.example.demo.R.id.m_image;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.transition.Hold;

import java.util.ArrayList;

public class menuadpter extends RecyclerView.Adapter<menuadpter.Myviewholder> {

    ArrayList<imageupload> mlist;
    android.content.Context context;

    public menuadpter(Context context, ArrayList<imageupload> mlist){
        this.context = context;
        this.mlist = mlist;
    }

    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.ityem,parent,false);
        return new Myviewholder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull Myviewholder holder, int position) {



        String link = mlist.get(position).getImageurl();
        Glide.with(context).load(link).into(holder.imageView);
        holder.name.setText("Name :"+mlist.get(position).getName());
        holder.price.setText("Price RS : "+mlist.get(position).getPrice());
        holder.desc.setText("Description :"+mlist.get(position).getDesc());

       /* holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(context,menudisplay.class);
                intent.putExtra("desc",mlist.get(holder.getAdapterPosition()).getDesc());
                intent.putExtra("imageurl",mlist.get(holder.getAdapterPosition()).getImageurl());
                intent.putExtra("name",mlist.get(holder.getAdapterPosition()).getName());
                intent.putExtra("price",mlist.get(holder.getAdapterPosition()).getPrice());
                intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
                intent.setAction(Intent.ACTION_GET_CONTENT);




                context.startActivity(intent);
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public static class Myviewholder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView name,price,desc;
        CardView cardView;
        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(m_image);
            name = itemView.findViewById(R.id.textView2);
            price = itemView.findViewById(R.id.textView3);
            desc = itemView.findViewById(R.id.textView4);
            cardView = itemView.findViewById(R.id.card1);
        }
    }
}
