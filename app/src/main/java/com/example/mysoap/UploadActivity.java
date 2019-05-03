package com.example.mysoap;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mysoap.Prevalent.Prevalent;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class UploadActivity extends AppCompatActivity {
    public static final int REQUEST_GALLERY = 1;

    //imageView.setImageBitmap(AtivityUpload.Bitmap);
    public static Bitmap bitmap;
    ImageView shows,imageView, InputProductImage;
    Dialog myDialog;
    Button Close, AddNewOrder;
    private LinearLayout GotoBank, GotoBankFrom;
    private TextView Bankmoney, usernameTextView, userphoneTextView, useraddressTextView;
    private String CategoryName, saveCurrentDate, saveCurrentTime, Bank;
    private StorageReference ProductImagesRef;
    private DatabaseReference ProductsRef;
    private static final int GalleryPick = 1;
    private Uri ImageUri;
    private ProgressDialog loadingBar;
    private String productRandomKey, downloadImageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);


        ImageView Img1 = (ImageView) findViewById(R.id.image1);
        Img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UploadActivity.this, PaymentActivity.class);
                startActivity(intent);
            }
        });




        GotoBank = (LinearLayout) findViewById(R.id.gotobank);
        GotoBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UploadActivity.this, BankActivity.class);
                startActivity(intent);
            }
        });


        Intent recevie_value = getIntent();
        String show_number =recevie_value.getStringExtra(
                "name_id");
        TextView bankactivity = (TextView) findViewById(R.id.bank);
        bankactivity.setText(show_number);

        Intent recevie1_value = getIntent();

        myDialog = new Dialog(this);

        ProductImagesRef = FirebaseStorage.getInstance().getReference().child("Product Images");
        ProductsRef = FirebaseDatabase.getInstance().getReference().child("Order");
        usernameTextView = findViewById(R.id.user_profile_name);
        userphoneTextView = findViewById(R.id.user_profile_phone);
        useraddressTextView = findViewById(R.id.user_profile_address);
        AddNewOrder = (Button)findViewById(R.id.add_order);
        InputProductImage = (ImageView)findViewById(R.id.select_bill_image);
        Bankmoney = findViewById(R.id.bank);

        loadingBar = new ProgressDialog(this);

        InputProductImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                OpenGallery();
            }
        });

        AddNewOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                ValidateProductData();
            }
        });

        usernameTextView.setText(Prevalent.currentOnlineUser.getName());
        userphoneTextView.setText(Prevalent.currentOnlineUser.getPhone());
        useraddressTextView.setText(Prevalent.currentOnlineUser.getAddress());

    }
    private void OpenGallery()
    {
        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, GalleryPick);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==GalleryPick  &&  resultCode==RESULT_OK  &&  data!=null)
        {
            ImageUri = data.getData();
            InputProductImage.setImageURI(ImageUri);
        }
    }
    private void ValidateProductData()
    {
        Bank = Bankmoney.getText().toString();
        if (ImageUri == null)
        {
            Toast.makeText(this, "โปรดใส่ภาพสินค้า", Toast.LENGTH_SHORT).show();
        }
        else
        {
            StoreProductInformation();
        }
    }
    private void StoreProductInformation()
    {
        loadingBar.setTitle("เพิ่มสินค้าใหม่");
        loadingBar.setMessage("กรุณารอสักครู่");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MM:dd:yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss");
        saveCurrentTime = currentTime.format(calendar.getTime());

        productRandomKey = saveCurrentDate + saveCurrentTime;

        final StorageReference filePath = ProductImagesRef.child(ImageUri.getLastPathSegment() + productRandomKey + ".jpg");

        final UploadTask uploadTask = filePath.putFile(ImageUri);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e)
            {
                String message = e.toString();
                Toast.makeText(UploadActivity.this, "เกิดข้อผิดพลาด"  + message, Toast.LENGTH_SHORT).show();
                loadingBar.dismiss();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
            {
                Toast.makeText(UploadActivity.this, "อัพโหลดภาพสินค้าสำเร็จ", Toast.LENGTH_SHORT).show();

                Task<Uri> uriTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if(!task.isSuccessful())
                        {
                            throw task.getException();
                        }

                        downloadImageUrl = filePath.getDownloadUrl().toString();
                        return filePath.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task)
                    {
                        if(task.isSuccessful())
                        {
                            downloadImageUrl = task.getResult().toString();

                            Toast.makeText(UploadActivity.this, "บันทึกภาพสินค้าสำเร็จ", Toast.LENGTH_SHORT).show();

                            saveProductInfoToDatabase();
                        }
                    }
                });
            }
        });

    }
    private void saveProductInfoToDatabase()
    {
        HashMap<String, Object> productMap = new HashMap<>();
        productMap.put("date", saveCurrentDate);
        productMap.put("time", saveCurrentTime);
        productMap.put("image", downloadImageUrl);
        productMap.put("bank", Bank);


        ProductsRef.child(productRandomKey).updateChildren(productMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if (task.isSuccessful())
                        {

                            Intent intent = new Intent(UploadActivity.this, MainActivity.class);
                            startActivity(intent);

                            loadingBar.dismiss();
                            Toast.makeText(UploadActivity.this, "เพิ่มสินค้าเรียบร้อย", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            loadingBar.dismiss();
                            String message = task.getException().toString();
                            Toast.makeText(UploadActivity.this, "เกิดข้อผิดพลาด" + message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

}


