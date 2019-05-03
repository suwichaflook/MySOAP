package com.example.mysoap;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private Button CreateAccountButton;
    private EditText InputName, InputId, InputPassword, InputNumber, InputEmail, InputAddress;
    private ProgressDialog loadingBar;
    private ImageView BackLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        BackLogin = (ImageView)findViewById(R.id.image1);
        BackLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        CreateAccountButton = (Button)findViewById(R.id.registerbutton);
        InputName = (EditText)findViewById(R.id.register_name);
        InputId = (EditText)findViewById(R.id.register_id);
        InputPassword = (EditText)findViewById(R.id.register_pass);
        InputNumber = (EditText)findViewById(R.id.register_number);
        InputEmail = (EditText)findViewById(R.id.register_email);
        InputAddress = (EditText)findViewById(R.id.register_address);
        loadingBar = new ProgressDialog(this);

        CreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                CreateAccount();
            }
        });
    }

    private void CreateAccount()
    {

        String id = InputId.getText().toString();
        String password = InputPassword.getText().toString();
        String email = InputEmail.getText().toString();
        String name = InputName.getText().toString();
        String phone = InputNumber.getText().toString();
        String address = InputAddress.getText().toString();


        if (TextUtils.isEmpty(id))
        {
            Toast.makeText(this, "กรุณาใส่ชื่อผู้ใช้", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "กรุณาใส่พาส", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(email))
        {
            Toast.makeText(this, "กรุณาใส่อีเมล", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(name))
        {
            Toast.makeText(this, "กรุณาใส่ชื่อ", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(address))
        {
            Toast.makeText(this, "กรุณาใส่ที่อยู่", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(phone))
        {
            Toast.makeText(this, "กรุณาใส่เบอร์", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingBar.setTitle("สร้างบัญชี");
            loadingBar.setMessage("กรุณารอสักครู่");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            ValidatorphoneNumber(id, password, email, name, phone, address);
        }
    }

    private void ValidatorphoneNumber(final String id, final String password, final String email, final String name, final String phone, final String address)
    {
        final DatabaseReference ROOTRef;
        ROOTRef = FirebaseDatabase.getInstance().getReference();

        ROOTRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if (!(dataSnapshot.child("Users").child(id).exists()))
                {
                    HashMap<String, Object> userdataMap = new HashMap<>();
                    userdataMap.put("id", id);
                    userdataMap.put("password", password);
                    userdataMap.put("email", email);
                    userdataMap.put("name", name);
                    userdataMap.put("address", address);
                    userdataMap.put("phone", phone);

                    ROOTRef.child("Users").child(id).updateChildren(userdataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>()
                            {
                                @Override
                                public void onComplete(@NonNull Task<Void> task)
                                {
                                    if (task.isSuccessful())
                                    {
                                        Toast.makeText(RegisterActivity.this, "สมัครบัญชีเรียบร้อย", Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();

                                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                        startActivity(intent);
                                    }
                                    else
                                    {
                                        loadingBar.dismiss();
                                        Toast.makeText(RegisterActivity.this, "ขาดการเชื่อมต่อ : โปรดลองอีกครั้ง", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                else
                {
                    Toast.makeText(RegisterActivity.this, "ชื่อผู้ใช้นี้"+ id + "มีอยู่ในระบบเเล้ว", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Toast.makeText(RegisterActivity.this, "โปรดใส่ชื่อผู้ใช้ใหม่อีกครั้ง", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
