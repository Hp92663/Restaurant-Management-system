package com.example.demo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class booksuccess extends AppCompatActivity implements PaymentResultListener {

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference,da,booking,time;
    String[] permisssion = {"android.permission.SEND_SMS"};

    Button book,select,select1;
    CalendarView c1;
    String value,dates,name,email;
    Integer n4;
    Integer n1=null,n2=null;
    String valid="not";
    String[] data;
    Date dat1,dat2;
    ProgressBar pro;
    String formattedDate,rupes;
    String randomnumber = UUID.randomUUID().toString();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booksuccess);
        value = getIntent().getStringExtra("value");
        rupes = getIntent().getStringExtra("ruppes");
        book = findViewById(R.id.booknow);
        select = findViewById(R.id.selectdate);
        c1 = findViewById(R.id.calendarView);
        select1 = findViewById(R.id.selectdate2);
        pro = findViewById(R.id.progressBar3);

        name = getIntent().getStringExtra("name");
        email = getIntent().getStringExtra("email");
        booking = firebaseDatabase.getReference("booking");
        time = firebaseDatabase.getReference("booking");

        Calendar calendar = Calendar.getInstance();

        // Get the current date from the Calendar object
        Date date = calendar.getTime();

        // Create a SimpleDateFormat object with the desired format
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        // Format the current date using the SimpleDateFormat object
         formattedDate = dateFormat.format(date);

        // Print the formatted date
        System.out.println(formattedDate);


        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Toast.makeText(booksuccess.this,"select date is : "+dates,Toast.LENGTH_SHORT).show();

            }
        });

        c1.setVisibility(View.VISIBLE);
        c1.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                Calendar select = Calendar.getInstance();
                select.set(i,i1,i2);

                SimpleDateFormat datef = new SimpleDateFormat("dd/MM/yyyy");
                String datess = datef.format(select.getTime());
                dates = datess;

                try {
                     dat1 = datef.parse(datess);
                     dat2 = datef.parse(formattedDate);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                int result = dat1.compareTo(dat2);
                if (result < 0 )
                {
                    Toast.makeText(booksuccess.this, "Plase select valid date", Toast.LENGTH_SHORT).show();
                    select1.setVisibility(View.GONE);
                    book.setVisibility(View.GONE);
                }
                else
                {
                    select1.setVisibility(View.VISIBLE);
                }


                booking.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                        if (snapshot.exists()) {
                            Map map = (Map) snapshot.getValue();
                            String namee = map.get("date").toString();
                            // String namemm = namee;
                            //Toast.makeText(booksuccess.this, namee + " And " + name, Toast.LENGTH_SHORT).show();
                            if (namee.equals(dates)){
                                //Toast.makeText(booksuccess.this, "yes already there", Toast.LENGTH_SHORT).show();
                                valid="yes";
                                booking.removeEventListener(this);
                                pro.setVisibility(View.INVISIBLE);

                            }
                            else {
                                valid="no";
                            }


                        } else {
                            valid="not";
                            Toast.makeText(booksuccess.this,"something wrong",Toast.LENGTH_SHORT).show();

                        }
                        pro.setVisibility(View.INVISIBLE);

                    }


                    @Override
                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestPermissions(permisssion,20);
                int amount = Math.round(Float.parseFloat(String.valueOf(rupes)) * 100);

                Checkout checkout = new Checkout();
                checkout.setKeyID("rzp_test_HHIK7bhmtxxXhz");
                checkout.setImage(R.drawable.logo);

                JSONObject object = new JSONObject();
                try {
                    object.put("name","Restorent");
                    object.put("description ","Tebale booking payment");
                    object.put("amount",amount);
                    object.put("prefill.contect","8156035497");
                    checkout.open(booksuccess.this,object);

                }catch (Exception e){
                    e.printStackTrace();

                }

            }
        });



        databaseReference = firebaseDatabase.getReference("table").child(value);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                n4 = snapshot.getValue(Integer.class);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(booksuccess.this,"error"+error,Toast.LENGTH_SHORT).show();

            }
        });

        select1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pro.setVisibility(View.VISIBLE);
                if (valid.equals("yes")){
                    Toast.makeText(booksuccess.this, "alredy exist", Toast.LENGTH_SHORT).show();
                    pro.setVisibility(View.INVISIBLE);
                } else if (valid.equals("not")) {
                    Toast.makeText(booksuccess.this, "Book Now...", Toast.LENGTH_SHORT).show();
                    requestPermissions(permisssion,20);
                    pro.setVisibility(View.INVISIBLE);

                } else {
                    Toast.makeText(booksuccess.this, "Book Now...", Toast.LENGTH_SHORT).show();
                    requestPermissions(permisssion,20);
                    pro.setVisibility(View.INVISIBLE);

                }









            }
        });







    }

    public void storedatat(){


        bookmodel bm = new bookmodel(dates,name,email,value);
        booking.child(String.valueOf(randomnumber)).setValue(bm);
        //time = firebaseDatabase.getReference("date");
        //time.setValue(dates);

        if (n4!=0)
        {
            n2 = n4 - 1;
            HashMap user = new HashMap();
            user.put(value,n2);


            Toast.makeText(booksuccess.this,"saves"+n4+"  ava "+n2,Toast.LENGTH_SHORT).show();
            da = firebaseDatabase.getReference("table");
            da.updateChildren(user);

        }
        else
        {
            n1=0;
            HashMap user = new HashMap();
            user.put(value,n1);


            Toast.makeText(booksuccess.this,"Sorry table not available",Toast.LENGTH_SHORT).show();
            da = firebaseDatabase.getReference("table");
            da.updateChildren(user);

        }

       // Toast.makeText(booksuccess.this,"not exist",Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(booksuccess.this, "Payment success", Toast.LENGTH_SHORT).show();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("user");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("UnlocalizedSms")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                storedatat();
                String num = snapshot.child(name).child("mobile").getValue(String.class);
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(num,"Restorent Management","      Restorent Management \nName : "+name+"\nDate : "+dates+"\nNo of Person : "+value+"\n\nYour booking is success",null,null);
                Toast.makeText(booksuccess.this, "Booking conform sended sms", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        Intent intent = new Intent(booksuccess.this,gotosuccess.class);
        intent.putExtra("name",name);
        intent.putExtra("date",dates);
        startActivity(intent);
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(booksuccess.this, "Payment fail", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(booksuccess.this,gotofail.class);
        intent.putExtra("name",name);
        startActivity(intent);

    }
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


        if (requestCode==20){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                book.setVisibility(View.VISIBLE);
            }
            else {
                Toast.makeText(this,"Please give permission",Toast.LENGTH_SHORT).show();
            }
        }
    }
}