package com.example.mysoap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ProductsixActivity extends AppCompatActivity {

    private ImageView add, remove;
    private TextView price;
    private EditText amount;
    private int item = 0, totalPrice;
    private String showItem;
    private Button addToCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productsix);


        Button btn2 = (Button) findViewById(R.id.btn1);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductsixActivity.this, CartActivity.class);
                EditText edt1 = (EditText)findViewById(R.id.editText);
                TextView tv1 = (TextView)findViewById(R.id.textview);

                intent.putExtra("name_id",edt1.getText().toString());
                intent.putExtra("text",tv1.getText().toString());

                startActivity(intent);
            }
        });

        ImageView Img1 = (ImageView) findViewById(R.id.image1);
        Img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductsixActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        add = (ImageView) findViewById(R.id.img2);
        remove = (ImageView) findViewById(R.id.img1);
        price = (TextView) findViewById(R.id.textview);
        amount = (EditText) findViewById(R.id.editText);

        totalPrice = 20;
        item = 0;
        price.setText("ราคาสินค้า");
        amount.setText("");

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = item + 1;
                showItem = Integer.toString(item);
                amount.setText(showItem);
                totalPrice = item * 70;

                price.setText(Integer.toString(totalPrice) + "");
            }
        });
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (amount.getText().toString().equals("")) {
                    item = 0;
                    totalPrice = 0;
                    price.setText("ราคาสินค้า");
                    amount.setText("");
                } else if (Integer.parseInt(amount.getText().toString()) > 1) {
                    item = Integer.parseInt(amount.getText().toString());
                    item = item - 1;
                    showItem = Integer.toString(item);
                    amount.setText(showItem);
                    totalPrice -= 70;
                    price.setText(Integer.toString(totalPrice) + "");
                } else if (Integer.parseInt(amount.getText().toString()) == 1) {
                    item = 0;
                    totalPrice = 0;
                    price.setText("ราคาสินค้า");
                    amount.setText("");
                }
            }
        });

//        addToCart = findViewById(R.id.addToCart);
//        addToCart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ModelCart modelCart = new ModelCart("01",R.drawable.g8,"ครีมนวด อัญชัน",Integer.parseInt(amount.getText().toString()),price.getText().toString());
//                MainActivity.CartList.add(modelCart);
//                Toast.makeText(ProductsixActivity.this,"ใส่ตะกร้าเรียบร้อยแล้ว",Toast.LENGTH_LONG).show();
//            }
//        });
    }
}
