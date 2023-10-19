package com.example.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class tablebook extends AppCompatActivity {

    TextView txt,fourtxt,sixtxt,eighttxt;

    ImageView timg,fimg,simg,eimg;

    String name,email;
    Integer tob,fob,sob,eob,lob;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference("table");
    DatabaseReference two = reference.child("2person");

    DatabaseReference four = reference.child("4person");
    DatabaseReference six = reference.child("6person");
    DatabaseReference eight = reference.child("8person");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablebook);

        txt = findViewById(R.id.textView99);
        fourtxt = findViewById(R.id.textView98);
        sixtxt = findViewById(R.id.textView97);
        eighttxt = findViewById(R.id.textView96);
        timg = findViewById(R.id.imageView10);
        fimg = findViewById(R.id.imageView11);
        simg = findViewById(R.id.imageView12);
        eimg = findViewById(R.id.imageView13);

        name = getIntent().getStringExtra("name");
        email = getIntent().getStringExtra("email");

        two.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                tob = snapshot.getValue(Integer.class);
                txt.setText("Availabele " + tob.toString() + " Tables ");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        four.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                fob = snapshot.getValue(Integer.class);
                fourtxt.setText("Available " + fob.toString() + " Tables");
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        six.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                sob = snapshot.getValue(Integer.class);
                sixtxt.setText("Available " + sob.toString() + " Tables");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        eight.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                eob = snapshot.getValue(Integer.class);
                eighttxt.setText("Available " + eob.toString() + " Tables");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
       // Toast.makeText(this, "Ava : "+tob, Toast.LENGTH_SHORT).show();

            timg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        if (tob != 0){
                            Intent intent = new Intent(tablebook.this,booksuccess.class);
                            String rs = "500";
                            String value = "2person";
                            intent.putExtra("value",value);
                            intent.putExtra("name",name);
                            intent.putExtra("email",email);
                            intent.putExtra("ruppes",rs);
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(tablebook.this, "Table not available", Toast.LENGTH_SHORT).show();
                        }





                }
            });





        fimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fob != 0)
                {
                    Intent intent = new Intent(tablebook.this,booksuccess.class);
                    String rs = "800";
                    String value = "4person";
                    intent.putExtra("value",value);
                    intent.putExtra("name",name);
                    intent.putExtra("email",email);
                    intent.putExtra("ruppes",rs);
                    startActivity(intent);

                }
                else {
                    Toast.makeText(tablebook.this, "Table not available", Toast.LENGTH_SHORT).show();
                }




            }
        });

        simg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sob != 0)
                {
                    Intent intent = new Intent(tablebook.this,booksuccess.class);
                    String rs = "1200";
                    String value = "6person";
                    intent.putExtra("value",value);
                    intent.putExtra("name",name);
                    intent.putExtra("email",email);
                    intent.putExtra("ruppes",rs);
                    startActivity(intent);

                }
                else {
                    Toast.makeText(tablebook.this, "Table not available", Toast.LENGTH_SHORT).show();
                }





            }
        });

        eimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (eob != 0)
                {
                    Intent intent = new Intent(tablebook.this,booksuccess.class);
                    String rs = "1500";
                    String value = "8person";
                    intent.putExtra("value",value);
                    intent.putExtra("name",name);
                    intent.putExtra("email",email);
                    intent.putExtra("ruppes",rs);
                    startActivity(intent);

                }
                else {
                    Toast.makeText(tablebook.this, "Table not available", Toast.LENGTH_SHORT).show();
                }
                //Toast.makeText(tablebook.this, "availa : "+eob, Toast.LENGTH_SHORT).show();





            }
        });

    }
}