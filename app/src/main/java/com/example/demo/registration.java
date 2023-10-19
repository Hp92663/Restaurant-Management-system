package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.demo.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class registration extends AppCompatActivity {

    ActivityMainBinding binding;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    EditText username;
    EditText password,mobile,email,name;
    Button loginButton,registerbtn;

    FirebaseAuth auth;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Objects.requireNonNull(getSupportActionBar()).hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);



        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email1);
        mobile = findViewById(R.id.mobile);
        name = findViewById(R.id.name);
        loginButton = findViewById(R.id.loginButton);
        registerbtn = findViewById(R.id.addtablee);

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String ema = email.getText().toString();
                String mob = mobile.getText().toString();
                String na = name.getText().toString();
                String ex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                String n="[a-zA-Z]+[0-9]";
                String m = "^[0-9\\-\\+]{9,15}$";
                String p = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$";

                if (!user.isEmpty()) {
                    username.setError(null);
                    if (!ema.isEmpty() && ema.matches(ex)) {
                        email.setError(null);
                        if (!mob.isEmpty() && mob.matches(m)) {
                            mobile.setError(null);
                            if ((!na.isEmpty() )) {
                                name.setError(null);
                                if (!pass.isEmpty() && pass.matches(p)) {
                                    password.setError(null);
                                    String userv = username.getText().toString();
                                    String passv = password.getText().toString();
                                    String emav = email.getText().toString();
                                    String mobv = mobile.getText().toString();
                                    String nav = name.getText().toString();

                                    String us = "user";

                                    firebaseDatabase = FirebaseDatabase.getInstance();
                                    databaseReference = firebaseDatabase.getReference("user");
                                    auth = FirebaseAuth.getInstance();

                                    auth.createUserWithEmailAndPassword(emav,passv);




                                    storedata storedata = new storedata(userv,emav,mobv,nav,passv,us);
                                    databaseReference.child(userv).setValue(storedata);
                                    Intent intent = new Intent(registration.this , MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                    Toast.makeText(registration.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                                } else {
                                    password.setError("passwor must be in min 8 char ,uper,lower,number,specialsymbol..");
                                }
                            } else {
                                name.setError("Please Enter name..");
                            }
                        } else {
                            mobile.setError("Please Enter valid mobile..");
                        }
                    } else {
                        email.setError("Please Enter valid email..");
                    }
                } else {
                    username.setError("Please Enter username in string..");
                }

            }

        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(registration.this , MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public static class model {

        String imgaeurl;

        public model(String imgaeurl) {
            this.imgaeurl = imgaeurl;
        }

        public String getImgaeurl() {
            return imgaeurl;
        }

        public void setImgaeurl(String imgaeurl) {
            this.imgaeurl = imgaeurl;
        }
    }
}