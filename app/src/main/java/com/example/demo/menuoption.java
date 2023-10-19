package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.demo.databinding.ActivityMainBinding;

import java.util.Objects;

public class menuoption extends AppCompatActivity {

    ImageView drinkk,fast,regu;

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuoption);



        drinkk =findViewById(R.id.imageView4);
        fast = findViewById(R.id.imageView5);
        regu = findViewById(R.id.imageView6);

        drinkk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String source = "drink";
                Intent intent = new Intent(menuoption.this,menudisplay.class);
                intent.putExtra("source",source);
                startActivity(intent);
            }
        });

        fast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String source = "fastfood";
                Intent intent = new Intent(menuoption.this,menudisplay.class);
                intent.putExtra("source",source);
                startActivity(intent);
            }
        });

        regu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String source = "regular";
                Intent intent = new Intent(menuoption.this,menudisplay.class);
                intent.putExtra("source",source);
                startActivity(intent);
            }
        });


    }
}