package com.example.mysoap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BankFrom extends AppCompatActivity {
    private LinearLayout BankFrom1, BankFrom2, BankFrom3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_from);

        BankFrom1 = findViewById(R.id.bankfrom1);
        BankFrom2 = findViewById(R.id.bankfrom2);
        BankFrom3 = findViewById(R.id.bankfrom3);

        BankFrom1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BankFrom.this,UploadActivity.class);
                TextView tvbank1 = (TextView)findViewById(R.id.testbankfrom1);
                intent.putExtra("name_bank",tvbank1.getText().toString());
                startActivity(intent);
            }
        });
        BankFrom2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BankFrom.this,UploadActivity.class);
                TextView tvbank1 = (TextView)findViewById(R.id.testbankfrom2);
                intent.putExtra("name_bank",tvbank1.getText().toString());
                startActivity(intent);
            }
        });
        BankFrom3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BankFrom.this,UploadActivity.class);
                TextView tvbank1 = (TextView)findViewById(R.id.testbankfrom3);
                intent.putExtra("name_bank",tvbank1.getText().toString());
                startActivity(intent);
            }
        });
    }
}
