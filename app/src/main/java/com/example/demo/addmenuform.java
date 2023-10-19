package com.example.demo;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.demo.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class addmenuform extends AppCompatActivity {

    Button add,upload,select;
    EditText name,des,price;

    String imageurl;

    ImageView image;

    Uri uuri;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference,databse;



    Spinner spinner;

    String value;

    String[] permisssion = {"android.permission.READ_EXTERNAL_STORAGE","android.permission.MANAGE_EXTERNAL_STORAGE","android.permission.WRITE_EXTERNAL_STORAGE"};

    String[] type = {"drink","fastfood","regular"};

    ProgressBar pro;

    ActivityResultLauncher<Intent> take;
    ActivityMainBinding binding;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmenuform);

        name=findViewById(R.id.itemname);
        des=findViewById(R.id.decription);
        price=findViewById(R.id.price);
        add=findViewById(R.id.addtablee);
        spinner=findViewById(R.id.spinne4);
        image=findViewById(R.id.imageView2);
        pro=findViewById(R.id.progressBar2);
        upload = findViewById(R.id.addd);
        take = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK){
                            Intent data = result.getData();
                            uuri = data.getData();
                            image.setImageURI(uuri);
                        }
                    }
                }


        );



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(addmenuform.this, android.R.layout.simple_spinner_item,type);
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

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestPermissions(permisssion,20);
                Intent photo = new Intent(Intent.ACTION_PICK);
                photo.setType("image/*");
                take.launch(photo);

            }
        });



        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String name1=name.getText().toString();
                String des1=des.getText().toString();
                String price1=price.getText().toString();

                pro.setVisibility(View.VISIBLE);

                if (!name1.isEmpty()){
                    name.setError(null);
                    if (!des1.isEmpty()){
                        des.setError(null);
                        if (!price1.isEmpty()){
                            price.setError(null);
                            if (uuri != null){

                                uploadeimage();




                                Toast.makeText(addmenuform.this,"Added success",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(addmenuform.this , adminside.class);
                                startActivity(intent);
                                finish();
                            }else {
                                Toast.makeText(addmenuform.this,"Plase select image",Toast.LENGTH_SHORT).show();
                            }







                        }else {
                            price.setError("Enter Price ......");
                        }

                    }else {
                        des.setError("Enter Description...");
                    }
                }else {
                    name.setError("Enter a Name ..");
                }
            }
        });
    }



    private void uploadeimage(){
        StorageReference reference = FirebaseStorage.getInstance().getReference().child(uuri.toString());
        reference.putFile(uuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete());
                uuri = uriTask.getResult();
                imageurl=uuri.toString();
                savadata();





            }
        });
    }

    public void savadata(){

        String namev = name.getText().toString();
        String descv = des.getText().toString();
        String pricv = price.getText().toString();

        imageupload md = new imageupload(namev,descv,pricv,imageurl);

        FirebaseDatabase.getInstance().getReference("menu").child(value).child(namev).setValue(md).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(addmenuform.this,"saved",Toast.LENGTH_SHORT ).show();
                }
            }
        });

    }

    private String getextension(Uri muri) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mini = MimeTypeMap.getSingleton();
        return mini.getExtensionFromMimeType(cr.getType(muri));

    }

    @Override
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