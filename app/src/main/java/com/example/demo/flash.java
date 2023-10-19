package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.example.demo.databinding.ActivityMainBinding;

public class flash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);
        Thread thread = new Thread(){
            public void run(){
                try {
                    sleep(2000);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                finally {
                    Intent intent = new Intent(flash.this , MainActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        };thread.start();
    }
}