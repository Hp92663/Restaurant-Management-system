package com.example.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    EditText username;
    EditText password, email;
    Button loginButton;

    TextView sign;

    TextView forgate;

    ProgressBar pro;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        sign = findViewById(R.id.signupText);
        forgate = findViewById(R.id.forgate);
        pro = findViewById(R.id.progressBar);
        email = findViewById(R.id.emaillogin);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                pro.setVisibility(View.VISIBLE);

                if (!user.isEmpty()) {
                    username.setError(null);
                    if (!pass.isEmpty()) {
                        password.setError(null);

                        final String userv = username.getText().toString();
                        final String passv = password.getText().toString();
                        final String emil = email.getText().toString();
                        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                        DatabaseReference databaseReference = firebaseDatabase.getReference("user");

                        Query check = databaseReference.orderByChild("email").equalTo(userv);
                        //Query check1 = databaseReference.orderByChild("isuser");
                        auth = FirebaseAuth.getInstance();

                        check.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists()) {
                                    username.setError(null);
                                    // String pas = snapshot.child(userv).child("password").getValue(String.class);
                                    String num = snapshot.child(emil).child("isuser").getValue(String.class);
                                    if (num != null) {

                                        auth.signInWithEmailAndPassword(userv, passv).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                if (task.isSuccessful()) {
                                                    if (num.equals("admin")) {
                                                        email.setError(null);
                                                        Toast.makeText(MainActivity.this, "Admin Login Successful!", Toast.LENGTH_SHORT).show();
                                                        Intent intent = new Intent(MainActivity.this, adminside.class);
                                                        startActivity(intent);
                                                        finish();

                                                    } else if (num.equals("manager")) {
                                                        Toast.makeText(MainActivity.this, "Manager Login Successful!", Toast.LENGTH_SHORT).show();
                                                        Intent intent = new Intent(MainActivity.this, managerside.class);
                                                        startActivity(intent);
                                                        finish();

                                                    } else if (num.equals("user")) {
                                                        Toast.makeText(MainActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                                                        Intent intent = new Intent(MainActivity.this, userhome.class);
                                                        intent.putExtra("name", emil);
                                                        intent.putExtra("email", userv);
                                                        startActivity(intent);
                                                        finish();
                                                    } else {
                                                        email.setError("user not available");
                                                    }


                                                } else {
                                                    pro.setVisibility(View.INVISIBLE);
                                                    password.setError("Enter valid detail");
                                                }
                                            }
                                        });


                                    }
                                } else {
                                    email.setError("User does not exist");
                                    pro.setVisibility(View.INVISIBLE);
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                    } else {
                        password.setError("please enter password..");
                    }
                } else {
                    username.setError("please enter username..");
                }
                password.setError(null);
            }
        });
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, registration.class);
                startActivity(intent);
                finish();
            }
        });

        forgate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, home.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

