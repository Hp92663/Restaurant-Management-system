package com.example.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class home extends AppCompatActivity {

    EditText email;
    Button forget;

    FirebaseAuth auth;
    String e;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        email = findViewById(R.id.email1);
        forget = findViewById(R.id.forgotButton);

        auth = FirebaseAuth.getInstance();



        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
            }
        });
    }

    public void validate(){
        e = email.getText().toString();
        if(e.isEmpty()){
            email.setError("Enter email");
        }
        else {
            forgatepassword();
        }

    }

    public void forgatepassword(){
        e = email.getText().toString();
        auth.sendPasswordResetEmail(e)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(home.this,"check Your male",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(home.this,MainActivity.class));
                    finish();
                }else {

                    Toast.makeText(home.this,"error",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}