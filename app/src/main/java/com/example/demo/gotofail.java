package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class gotofail extends AppCompatActivity {
    TextView cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gotofail);
        cancel = findViewById(R.id.canceltext);

        String can = getIntent().getStringExtra("name");

        cancel.setText("Sorry "+can+" Your booking is fail try again");
    }
}