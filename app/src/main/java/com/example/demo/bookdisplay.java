package com.example.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class bookdisplay extends AppCompatActivity {
    @SuppressLint("WrongViewCast")
    RecyclerView recyclerView;
    ArrayList<bookingmodel> llist;
    bookadapter Bookad;
    ValueEventListener valueEventListener;

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    DatabaseReference ref1 = ref.child("booking");

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookdisplay);

        recyclerView = findViewById(R.id.recyclebook1);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        GridLayoutManager gridLayoutManager = new GridLayoutManager(bookdisplay.this,1);
        recyclerView.setLayoutManager(gridLayoutManager);

        llist = new ArrayList<>();
        Bookad = new bookadapter(bookdisplay.this,llist);
        recyclerView.setAdapter(Bookad);

        valueEventListener = ref1.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                llist.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    bookingmodel Bookinf = dataSnapshot.getValue(bookingmodel.class);
                    llist.add(Bookinf);
                }
                Bookad.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}