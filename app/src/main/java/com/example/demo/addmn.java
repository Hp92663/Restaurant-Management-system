package com.example.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addmn extends AppCompatActivity {

    EditText user;
    EditText email;
    EditText mobile;
    EditText name;
    EditText pass;
    Button ad;
    String[] permisssion = {"android.permission.SEND_SMS"};

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmn);

        user = findViewById(R.id.username);
        email = findViewById(R.id.email);
        mobile = findViewById(R.id.mobile);
        name = findViewById(R.id.name);
        pass = findViewById(R.id.password);

        ad = findViewById(R.id.addd);
        requestPermissions(permisssion,20);

        ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String u = user.getText().toString();
                String e = email.getText().toString();
                String m = mobile.getText().toString();
                String n = name.getText().toString();
                String p = pass.getText().toString();
                String ex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                String nn="[a-zA-Z]+[0-9]";
                String mn= "^[0-9\\-\\+]{9,15}$";
                String pn= "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$";

                if (!u.isEmpty()){
                    user.setError(null);
                    if (!e.isEmpty() && e.matches(ex)){
                        email.setError(null);
                        if (!m.isEmpty() && m.matches(mn)){
                            mobile.setError(null);
                            if (!n.isEmpty()){
                                name.setError(null);
                                if (!p.isEmpty() && p.matches(pn)){
                                    pass.setError(null);

                                    firebaseDatabase = FirebaseDatabase.getInstance();
                                    databaseReference = firebaseDatabase.getReference("user");

                                    String isuser = "manager";
                                    auth = FirebaseAuth.getInstance();

                                    auth.createUserWithEmailAndPassword(e,p);

                                    storedata storedata = new storedata(u,e,m,n,p,isuser);
                                    databaseReference.child(u).setValue(storedata);

                                    SmsManager smsManager = SmsManager.getDefault();
                                    smsManager.sendTextMessage(m,null,"Email : "+e+"\nUsername  : "+u+"\nPassword : "+p+"\nWork proper Bhosdike",null,null);

                                    Toast.makeText(addmn.this, "Manager added", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(addmn.this,adminside.class);
                                    startActivity(intent);
                                    finish();

                                }else {
                                    pass.setError("Enter password");
                                }
                            }else {
                                name.setError("Enter name");
                            }
                        }else {
                            mobile.setError("Enter phone number");
                        }

                    }else {
                        email.setError("Eneter email");
                    }
                }

            }
        });



    }
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


        if (requestCode==20){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            }
            else {
                Toast.makeText(this,"Please give permission",Toast.LENGTH_SHORT).show();
            }
        }
    }
}