package com.example.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.demo.databinding.ActivityMainBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class menudisplay extends AppCompatActivity {

    RecyclerView recyclerView;

    String[] permisssion = {"android.permission.READ_EXTERNAL_STORAGE","android.permission.MANAGE_EXTERNAL_STORAGE","android.permission.WRITE_EXTERNAL_STORAGE"};

    ArrayList<imageupload> list;

    menuadpter Menuadpter;
    Button book;

    ValueEventListener valueEventListener;

    String valuee;




    DatabaseReference root = FirebaseDatabase.getInstance().getReference();
    DatabaseReference root1 = root.child("menu");

    DatabaseReference root2;

    ActivityMainBinding binding;





    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menudisplay);



        requestPermissions(permisssion,20);
        valuee = getIntent().getStringExtra("source");
        recyclerView = findViewById(R.id.recyclee);
        book = findViewById(R.id.go);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        GridLayoutManager gridLayoutManager = new GridLayoutManager(menudisplay.this,1);
        recyclerView.setLayoutManager(gridLayoutManager);
        if (valuee == null)
        {
            Intent intent = new Intent(menudisplay.this,menuoption.class);
            startActivity(intent);
        }
        else {
            root2 = root1.child(valuee);
        }

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(menudisplay.this,userhome.class);
                startActivity(intent);
                finish();
            }
        });









        list = new ArrayList<>();
        Menuadpter = new menuadpter(menudisplay.this,list);
        recyclerView.setAdapter(Menuadpter);

        valueEventListener = root2.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    imageupload Imageupload = dataSnapshot.getValue(imageupload.class);
                    list.add(Imageupload);
                }
                Menuadpter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {



            }
        });
        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menudisplay.this,menuoption.class);
                startActivity(intent);
            }
        });






    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


        if (requestCode == 20) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            } else {
                Toast.makeText(this, "Please give permission", Toast.LENGTH_SHORT).show();
            }
        }
    }
}