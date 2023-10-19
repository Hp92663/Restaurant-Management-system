package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class adminside extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminside);

        Button addmenu,addman,menuv,viewbooking,managetable;

        addmenu = findViewById(R.id.booktab);

        addman = findViewById(R.id.addmenager);

        menuv = findViewById(R.id.viewmenuadmin);

        viewbooking = findViewById(R.id.viewbooking);

        managetable = findViewById(R.id.addtable);

        addmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(adminside.this,addmenuform.class);
                startActivity(intent);
            }
        });

        addman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(adminside.this,addmn.class);
                startActivity(intent);
            }
        });

        menuv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(adminside.this, menuoption.class);
                startActivity(intent);
            }
        });

        viewbooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(adminside.this, bookdisplay.class);
                startActivity(intent);

            }
        });

        managetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(adminside.this, com.example.demo.managetable.class);
                startActivity(intent);

            }
        });
    }
}