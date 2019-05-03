package com.example.mysoap;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.DataSetObserver;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mysoap.Prevalent.Prevalent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    public static ListView listView;
    public static int PriceAll = 0;
    public static TextView priceAllTv;
    private CartListViewAdapter adapter;
    private ImageView BackHome;
    private Button Gotopayment;
    TextView mealTotalText;
    ArrayList<ModelCart> modellist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        priceAllTv = findViewById(R.id.priceAll);
        Gotopayment = findViewById(R.id.Go_to_payment);
        Gotopayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, PaymentActivity.class);


                startActivity(intent);
            }
        });

        BackHome = findViewById(R.id.imge1);
        BackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.navigation_home:
                        Intent intent1 = new Intent(CartActivity.this, MainActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.navigation_account:
                        Intent intent3 = new Intent(CartActivity.this, AccountActivity.class);
                        startActivity(intent3);
                        break;

                }


                return false;
            }
        });

        listView = findViewById(R.id.cartList);
        adapter = new CartListViewAdapter(CartActivity.this, MainActivity.CartList);
        listView.setAdapter(adapter);


        ;

        int price = 0;
        for (int i = 0; i < CartActivity.listView.getAdapter().getCount(); i++)
        {
            String priceString = MainActivity.CartList.get(i).getPrice();
            String[] split = priceString.split("\\s+");
            price += Integer.parseInt(split[0]);

        }
        priceAllTv.setText("ราคารวมทั้งหมด : "+price + " บาท");
    }

}




