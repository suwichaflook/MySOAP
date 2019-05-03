package com.example.mysoap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;


public class AddActivity extends AppCompatActivity {

    ImageView backtoadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        backtoadd = findViewById(R.id.imge1);
        backtoadd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddActivity.this,AccountActivity.class);
                startActivity(intent);
            }
        });



        DatabaseStudent mHelper = new DatabaseStudent(this);
        SQLiteDatabase mDb = mHelper.getWritableDatabase();
        mHelper.close();
        mDb.close();

        Button buttonView = (Button)findViewById(R.id.buttonView);
        buttonView.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ViewAddressActivity.class);
                startActivity(intent);
            }
        });

        Button buttonAdd = (Button)findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddAddress.class);
                startActivity(intent);
            }
        });

        Button buttonEdit = (Button)findViewById(R.id.buttonEdit);
        buttonEdit.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EditaddressActivity.class);
                startActivity(intent);
            }
        });
    }
}