package com.example.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class managetable extends AppCompatActivity {

    Spinner spinner;
    String value;

    Button add;

    EditText tableadd;


    TextView tperson,fperson,sperson,eperson;

    String[] type = {"2person","4person","6person","8person"};

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    DatabaseReference rootr;
    DatabaseReference reference = database.getReference("table");
    DatabaseReference two = reference.child("2person");

    DatabaseReference four = reference.child("4person");
    DatabaseReference six = reference.child("6person");
    DatabaseReference eight = reference.child("8person");

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managetable);
        tableadd = findViewById(R.id.name1);
        spinner = findViewById(R.id.spinne4);
        tperson = findViewById(R.id.tperson);
        fperson = findViewById(R.id.fperson);
        sperson = findViewById(R.id.sperson);
        eperson = findViewById(R.id.eperson);
        add = findViewById(R.id.addtablee);

        two.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Object data = snapshot.getValue();

                tperson.setText("2 Person Table Availabele Now : " + data.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        four.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Object data = snapshot.getValue();
                if (data != null){
                    fperson.setText("4 Person Table Availabele Now : " + data.toString());
                }
                else
                {
                    fperson.setText("4 Person Table Availabele Now : 0");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        six.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Object data = snapshot.getValue();
                sperson.setText("6 Person Table Availabele Now : " + data.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        eight.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Object data = snapshot.getValue();
                eperson.setText("8 Person Table Availabele Now : " + data.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(managetable.this, android.R.layout.simple_spinner_item,type);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                value = adapterView.getItemAtPosition(i).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootr = reference.child(value);
                Integer valuews = Integer.valueOf(tableadd.getText().toString());
                Map<String,Object> update = new HashMap<>();
                rootr.setValue(valuews);

                rootr.updateChildren(update).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Intent intent = new Intent(managetable.this,adminside.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(managetable.this, "success added", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }
}