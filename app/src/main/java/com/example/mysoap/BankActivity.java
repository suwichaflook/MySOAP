package com.example.mysoap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BankActivity extends AppCompatActivity {

    private LinearLayout Bank1, Bank2, Bank3, Bank4, Bank5, Bank6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank);

        Bank1 = findViewById(R.id.bank1);
        Bank2 = findViewById(R.id.bank2);
        Bank3 = findViewById(R.id.bank3);
        Bank4 = findViewById(R.id.bank4);
        Bank5 = findViewById(R.id.bank5);
        Bank6 = findViewById(R.id.bank6);

        Bank1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BankActivity.this,UploadActivity.class);
                TextView tvbank1 = (TextView)findViewById(R.id.textbank1);
                intent.putExtra("name_id",tvbank1.getText().toString());
                startActivity(intent);
            }
        });
        Bank2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BankActivity.this,UploadActivity.class);
                TextView tvbank1 = (TextView)findViewById(R.id.textbank2);
                intent.putExtra("name_id",tvbank1.getText().toString());
                startActivity(intent);
            }
        });
        Bank3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BankActivity.this,UploadActivity.class);
                TextView tvbank1 = (TextView)findViewById(R.id.textbank3);
                intent.putExtra("name_id",tvbank1.getText().toString());
                startActivity(intent);
            }
        });
        Bank4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BankActivity.this,UploadActivity.class);
                TextView tvbank1 = (TextView)findViewById(R.id.textbank4);
                intent.putExtra("name_id",tvbank1.getText().toString());
                startActivity(intent);
            }
        });
        Bank5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BankActivity.this,UploadActivity.class);
                TextView tvbank1 = (TextView)findViewById(R.id.textbank5);
                intent.putExtra("name_id",tvbank1.getText().toString());
                startActivity(intent);
            }
        });
        Bank6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BankActivity.this,UploadActivity.class);
                TextView tvbank1 = (TextView)findViewById(R.id.textbank6);
                intent.putExtra("name_id",tvbank1.getText().toString());
                startActivity(intent);
            }
        });
    }
}
