package com.example.demo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class bookinginfo extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<bookingmodel> llist;
    bookadapter Bookad;
    ValueEventListener valueEventListener;

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    DatabaseReference ref1 = ref.child("booking");

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    String ka = "kalpesh";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookinginfo);

        recyclerView = findViewById(R.id.recyclebook11);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        GridLayoutManager gridLayoutManager = new GridLayoutManager(bookinginfo.this,1);
        recyclerView.setLayoutManager(gridLayoutManager);

        llist = new ArrayList<>();
        Bookad = new bookadapter(bookinginfo.this,llist);
        recyclerView.setAdapter(Bookad);

        DatabaseReference databaseReference = firebaseDatabase.getReference("booking");
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Map map = (Map) snapshot.getValue();
                String namee = map.get("name").toString();
                if (namee.equals(ka))
                {
                    Toast.makeText(bookinginfo.this, "already therer", Toast.LENGTH_SHORT).show();
                    databaseReference.removeEventListener(this);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}