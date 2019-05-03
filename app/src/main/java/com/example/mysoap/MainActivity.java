package com.example.mysoap;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SliderLayout sliderLayout;
    private Button Productone, Producttwo, Productthree, Productfour, Productfive, Productsix, Productseven, Producteight;
    public static String[] product_id,product_name,product_price;
    public static Integer[] product_img;

    public static ArrayList<ModelCart> CartList = new ArrayList<ModelCart>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        product_id = new String[] {"01", "02", "03" , "04" ,"05", "06", "07", "08"};
        product_name = new String[] {"test1", "test2", "test3", "test4", "test5", "test6", "test7", "test8"};
        product_price = new String[] {"70","70","70","150","150","150","120","120"};


        Producteight = (Button)findViewById(R.id.btn7);
        Producteight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ProducteightActivity.class);
                intent.putExtra("p_id",product_id[7]);
                intent.putExtra("p_name",product_name[7]);
                intent.putExtra("p_price",product_price[7]);
                Bitmap bitmap = BitmapFactory.decodeResource
                        (getResources(), R.drawable.g6); // your bitmap
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bs);
                intent.putExtra("img", bs.toByteArray());
                startActivity(intent);
            }
        });

        Productone = (Button)findViewById(R.id.btn);
        Productone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ProductActivity.class);
                intent.putExtra("p_id",product_id[0]);
                intent.putExtra("p_name",product_name[0]);
                intent.putExtra("p_price",product_price[0]);
                Bitmap bitmap = BitmapFactory.decodeResource
                        (getResources(), R.drawable.g6); // your bitmap
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bs);
                intent.putExtra("img", bs.toByteArray());
                startActivity(intent);
            }
        });

        Producttwo = (Button)findViewById(R.id.btn1);
        Producttwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ProducttwoActivity.class);
                intent.putExtra("p_id",product_id[1]);
                intent.putExtra("p_name",product_name[1]);
                intent.putExtra("p_price",product_price[1]);
                Bitmap bitmap = BitmapFactory.decodeResource
                        (getResources(), R.drawable.g6); // your bitmap
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bs);
                intent.putExtra("img", bs.toByteArray());
                startActivity(intent);
            }
        });

        Productthree = (Button)findViewById(R.id.btn2);
        Productthree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ProductthreeActivity.class);
                intent.putExtra("p_id",product_id[2]);
                intent.putExtra("p_name",product_name[2]);
                intent.putExtra("p_price",product_price[2]);
                Bitmap bitmap = BitmapFactory.decodeResource
                        (getResources(), R.drawable.g3); // your bitmap
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bs);
                intent.putExtra("img", bs.toByteArray());
                startActivity(intent);
            }
        });

        Productfour = (Button)findViewById(R.id.btn3);
        Productfour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ProductfourActivity.class);
                intent.putExtra("p_id",product_id[3]);
                intent.putExtra("p_name",product_name[3]);
                intent.putExtra("p_price",product_price[3]);
                Bitmap bitmap = BitmapFactory.decodeResource
                        (getResources(), R.drawable.g3); // your bitmap
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bs);
                intent.putExtra("img", bs.toByteArray());
                startActivity(intent);
            }
        });

        Productfive = (Button)findViewById(R.id.btn4);
        Productfive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ProductfiveActivity.class);
                intent.putExtra("p_id",product_id[4]);
                intent.putExtra("p_name",product_name[4]);
                intent.putExtra("p_price",product_price[4]);
                Bitmap bitmap = BitmapFactory.decodeResource
                        (getResources(), R.drawable.g3); // your bitmap
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bs);
                intent.putExtra("img", bs.toByteArray());
                startActivity(intent);
            }
        });

        Productsix = (Button)findViewById(R.id.btn5);
        Productsix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ProductsixActivity.class);
                intent.putExtra("p_id",product_id[5]);
                intent.putExtra("p_name",product_name[5]);
                intent.putExtra("p_price",product_price[5]);
                Bitmap bitmap = BitmapFactory.decodeResource
                        (getResources(), R.drawable.g3); // your bitmap
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bs);
                intent.putExtra("img", bs.toByteArray());
                startActivity(intent);
            }
        });

        Productseven = (Button)findViewById(R.id.btn6);
        Productseven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ProductsevenActivity.class);
                intent.putExtra("p_id",product_id[6]);
                intent.putExtra("p_name",product_name[6]);
                intent.putExtra("p_price",product_price[6]);
                Bitmap bitmap = BitmapFactory.decodeResource
                        (getResources(), R.drawable.g3); // your bitmap
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bs);
                intent.putExtra("img", bs.toByteArray());
                startActivity(intent);
            }
        });



        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.navigation_basket:
                        Intent intent1 = new Intent(MainActivity.this, CartActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.navigation_account:
                        Intent intent3 = new Intent(MainActivity.this,AccountActivity.class);
                        startActivity(intent3);
                        break;

                }


                return false;
            }
        });

        sliderLayout = findViewById(R.id.imageSlider);
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.FILL); //set indicator animation by using SliderLayout.Animations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderLayout.setScrollTimeInSec(1); //set scroll delay in seconds :

        setSliderViews();
    }
        private void setSliderViews() {

            for (int i = 0; i <= 2; i++) {

                DefaultSliderView sliderView = new DefaultSliderView(this);

                switch (i) {
                    case 0:
                        sliderView.setImageDrawable(R.drawable.s4);
                        //sliderView.setImageUrl("https://images.pexels.com/photos/547114/pexels-photo-547114.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
                        break;
                    case 1:
                        sliderView.setImageDrawable(R.drawable.s5);
                        //sliderView.setImageUrl("https://images.pexels.com/photos/218983/pexels-photo-218983.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
                        break;
                    case 2:
                        sliderView.setImageDrawable(R.drawable.s6);
                        //sliderView.setImageUrl("https://images.pexels.com/photos/747964/pexels-photo-747964.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260");

                }

                sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
                //sliderView.setDescription("setDescription " + (i + 1));
                final int finalI = i;
                sliderView.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
                    @Override
                    public void onSliderClick(SliderView sliderView) {
                        Toast.makeText(MainActivity.this, "This is slider " + (finalI + 1), Toast.LENGTH_SHORT).show();
                    }
                });

                //at last add this view in your layout :
                sliderLayout.addSliderView(sliderView);
            }
        }
    }
