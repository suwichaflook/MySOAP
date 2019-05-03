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
import android.widget.TextView;
import android.widget.Toast;

import com.example.mysoap.Model.Users;
import com.example.mysoap.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rey.material.widget.CheckBox;

import io.paperdb.Paper;

public class LoginActivity extends AppCompatActivity {

    private Button loginbutton, registerbutton;
    private EditText Inputid, Inputpassword;
    private ProgressDialog loadingBar;
    private TextView AdminLink, NotAdminLink;

    private String parentDbName = "Users";
    private CheckBox chkBoxRememberMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        registerbutton = (Button)findViewById(R.id.loginregister);
        registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        loginbutton = (Button)findViewById(R.id.loginhome);
        Inputid = (EditText)findViewById(R.id.login_id);
        Inputpassword = (EditText)findViewById(R.id.login_password);
        AdminLink = (TextView)findViewById(R.id.admin_panel_link);
        NotAdminLink = (TextView)findViewById(R.id.not_admin_panel_link);

        chkBoxRememberMe = (CheckBox)findViewById(R.id.remember_me_chkb);

        Paper.init(this);

        loadingBar = new ProgressDialog(this);

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
        AdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                loginbutton.setText("เข้าสู่หน้าแอดมิน");
                AdminLink.setVisibility(View.INVISIBLE);
                NotAdminLink.setVisibility(View.VISIBLE);
                parentDbName="Admins";
            }
        });

        NotAdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                loginbutton.setText("เข้าสู่ระบบ");
                AdminLink.setVisibility(View.VISIBLE);
                NotAdminLink.setVisibility(View.INVISIBLE);
                parentDbName = "Users";
            }
        });


        String UserIdkey = Paper.book().read(Prevalent.UserIdkey);
        String UserPasswordKey = Paper.book().read(Prevalent.UserPasswordKey);
                if(UserIdkey != "" && UserPasswordKey != "")
                {
                    if (!TextUtils.isEmpty(UserIdkey)  && !TextUtils.isEmpty(UserPasswordKey))
                    {
                        AllowAccess(UserIdkey, UserPasswordKey);

                        loadingBar.setTitle("กำลังเข้าสู่บัญชี");
                        loadingBar.setMessage("กรุณารอสักครู่");
                        loadingBar.setCanceledOnTouchOutside(false);
                        loadingBar.show();
                    }
                }
    }

    private void AllowAccess(final String id, final String password)
    {


        final DatabaseReference ROOTRef;
        ROOTRef = FirebaseDatabase.getInstance().getReference();

        ROOTRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if (dataSnapshot.child("Users").child(id).exists())
                {
                    Users usersData = dataSnapshot.child("Users").child(id).getValue(Users.class);

                    if (usersData.getId().equals(id))
                    {
                        if (usersData.getPassword().equals(password))
                        {
                             if (parentDbName.equals("Users"))
                            {
                                Toast.makeText(LoginActivity.this, "เข้าสู่ระบบสำเร็จ", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();

                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                Prevalent.currentOnlineUser = usersData;
                                startActivity(intent);
                            }

                        }
                        else
                        {
                            loadingBar.dismiss();
                            Toast.makeText(LoginActivity.this, "รหัสผ่านไม่ถูกต้อง", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "บัญชีที่ใช้" + id + "ไม่มีในระบบ", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });












    }

    private void loginUser()
    {
        String id = Inputid.getText().toString();
        String password = Inputpassword.getText().toString();

        if (TextUtils.isEmpty(id))
        {
            Toast.makeText(this, "กรุณาใส่ชื่อผู้ใช้", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "กรุณาใส่พาส", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingBar.setTitle("เข้าสู่บัญชี");
            loadingBar.setMessage("กรุณารอสักครู่");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            AllowAccessToAccount(id, password);
        }
    }

    private void AllowAccessToAccount(final String id, final String password)
    {
        if (chkBoxRememberMe.isChecked())
        {
            Paper.book().write(Prevalent.UserIdkey, id);
            Paper.book().write(Prevalent.UserPasswordKey, password);
        }


        final DatabaseReference ROOTRef;
        ROOTRef = FirebaseDatabase.getInstance().getReference();

        ROOTRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
               if (dataSnapshot.child(parentDbName).child(id).exists())
               {
                   Users usersData = dataSnapshot.child(parentDbName).child(id).getValue(Users.class);

                   if (usersData.getId().equals(id))
                   {
                       if (usersData.getPassword().equals(password))
                       {
                          if (parentDbName.equals("Admins"))
                          {
                              Toast.makeText(LoginActivity.this, "เข้าสู่ระบบสำเร็จ", Toast.LENGTH_SHORT).show();
                              loadingBar.dismiss();

                              Intent intent = new Intent(LoginActivity.this, AdminCategoryActivity.class);
                              startActivity(intent);
                          }
                          else if (parentDbName.equals("Users"))
                          {
                              Toast.makeText(LoginActivity.this, "เข้าสู่ระบบสำเร็จ", Toast.LENGTH_SHORT).show();
                              loadingBar.dismiss();

                              Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                              Prevalent.currentOnlineUser = usersData;
                              startActivity(intent);
                          }

                       }
                       else
                       {
                           loadingBar.dismiss();
                           Toast.makeText(LoginActivity.this, "รหัสผ่านไม่ถูกต้อง", Toast.LENGTH_SHORT).show();
                       }
                   }
               }
               else
               {
                   Toast.makeText(LoginActivity.this, "บัญชีที่ใช้" + id + "ไม่มีในระบบ", Toast.LENGTH_SHORT).show();
                   loadingBar.dismiss();
               }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
