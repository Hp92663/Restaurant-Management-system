package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class userhome extends AppCompatActivity {

    Spinner spinner;

    Button book,view,viewbook,viewpro;

    TextView selectp;

    String name,email;

    Integer[] type = {2,4,6};

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userhome);


        book = findViewById(R.id.booktab);
        viewbook = findViewById(R.id.viewbooking);
        viewpro = findViewById(R.id.viewprofile);


        view = findViewById(R.id.view);

        name = getIntent().getStringExtra("name");
        email = getIntent().getStringExtra("email");






        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(userhome.this,tablebook.class);
               intent.putExtra("name",name);
               intent.putExtra("email",email);
               startActivity(intent);
                Toast.makeText(userhome.this,"Please select table ",Toast.LENGTH_SHORT).show();

            }
        });

        viewbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inntent = new Intent(userhome.this,bookinginfo.class);
                startActivity(inntent);
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(userhome.this, menuoption.class);
                startActivity(intent);
            }
        });

    }

}