package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class profile extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    TextView name,email,user,mobile;
    DatabaseReference childRef = database.getReference("user");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        name=findViewById(R.id.pname);
        email = findViewById(R.id.pemail);
        user=findViewById(R.id.puname);
        mobile = findViewById(R.id.pnumber);

        String name1 ="kalpesh";


        childRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get the data from the child node.
                Map map = (Map) dataSnapshot.getValue();
                String namee = map.get("name").toString();
                String mobile1 = map.get("mobile").toString();
                String username1 = map.get("username").toString();
                String email1 = map.get("email").toString();
                if (namee.equals(name1))
                {
                    name.setText("Name : "+namee);
                    email.setText("Emauil : "+email1);
                    user.setText("UserName : "+username1);
                    mobile.setText("Mobile : "+mobile1);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle the error.
            }
        });
    }
}