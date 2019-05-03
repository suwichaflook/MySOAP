package com.example.mysoap;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import io.paperdb.Paper;

public class AccountActivity extends AppCompatActivity {

    private LinearLayout Outtologin, GotoAdd, GoToMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        GoToMap = (LinearLayout)findViewById(R.id.gotomap);
        GoToMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountActivity.this,ContactActivity.class);
                startActivity(intent);
            }
        });

        GotoAdd = (LinearLayout)findViewById(R.id.gotoadd);
        GotoAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });


        Outtologin = (LinearLayout)findViewById(R.id.out1);
        Outtologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Paper.book().destroy();
                Intent intent = new Intent(AccountActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });



        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.navigation_home:
                        Intent intent1 = new Intent(AccountActivity.this, MainActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.navigation_basket:
                        Intent intent3 = new Intent(AccountActivity.this,CartActivity.class);
                        startActivity(intent3);
                        break;

                }


                return false;
            }
        });
    }
}
