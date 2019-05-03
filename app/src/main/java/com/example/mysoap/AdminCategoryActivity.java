package com.example.mysoap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AdminCategoryActivity extends AppCompatActivity {

    private ImageView soapone, soaptwo, soapthree;
    private ImageView soapfour, soapfive, soapsix;
    private ImageView soapseven, soapeight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);

        soapone = (ImageView)findViewById(R.id.s_soapone);
        soaptwo = (ImageView)findViewById(R.id.s_soaptwo);
        soapthree = (ImageView)findViewById(R.id.s_soapthree);

        soapfour = (ImageView)findViewById(R.id.s_soapfour);
        soapfive = (ImageView)findViewById(R.id.s_soapfive);
        soapsix = (ImageView)findViewById(R.id.s_soapsix);

        soapseven = (ImageView)findViewById(R.id.s_soapseven);
        soapeight = (ImageView)findViewById(R.id.s_soapeight);


        soapone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("ประเภท","สบู่");
                startActivity(intent);
            }
        });
        soaptwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("ประเภท","สบู่");
                startActivity(intent);
            }
        });
        soapthree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("ประเภท","สบู่");
                startActivity(intent);
            }
        });
        soaptwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("ประเภท","สบู่");
                startActivity(intent);
            }
        });
        soapfour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("ประเภท","ครีมอาบน้ำ");
                startActivity(intent);
            }
        });
        soapfive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("ประเภท","แชมพู");
                startActivity(intent);
            }
        });
        soapsix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("ประเภท","ครีมนวด");
                startActivity(intent);
            }
        });
        soapseven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("ประเภท","โลชั่น");
                startActivity(intent);
            }
        });
        soapeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("ประเภท","โลชั่น");
                startActivity(intent);
            }
        });
    }
}
