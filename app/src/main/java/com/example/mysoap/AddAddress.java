package com.example.mysoap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class AddAddress extends AppCompatActivity {

    DatabaseStudent mHelper;
    SQLiteDatabase mDb;
    ImageView backtoadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);

        backtoadd = findViewById(R.id.imge1);
        backtoadd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddAddress.this,AddActivity.class);
                startActivity(intent);
            }
        });




        mHelper = new DatabaseStudent(this);
        mDb = mHelper.getWritableDatabase();

        final EditText editName = (EditText)findViewById(R.id.editName);
        final EditText editLastName = (EditText)findViewById(R.id.editLastName);
        final EditText editSchool = (EditText)findViewById(R.id.editSchool);

        Button buttonAdd = (Button)findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                String name = editName.getText().toString();
                String lastname = editLastName.getText().toString();
                String school = editSchool.getText().toString();

                if(name.length() != 0 && lastname.length() != 0 && school.length() != 0 ) {

                    Cursor mCursor = mDb.rawQuery("SELECT * FROM " + DatabaseStudent.TABLE_NAME
                            + " WHERE " + DatabaseStudent.COL_NAME + "='" + name + "'"
                            + " AND " + DatabaseStudent.COL_LASTNAME + "='" + lastname + "'"
                            + " AND " + DatabaseStudent.COL_SCHOOL + "='" + school + "'", null);

                    if(mCursor.getCount() == 0) {
                        mDb.execSQL("INSERT INTO " + DatabaseStudent.TABLE_NAME + " ("
                                + DatabaseStudent.COL_NAME + ", " + DatabaseStudent.COL_LASTNAME
                                + ", " + DatabaseStudent.COL_SCHOOL + ") VALUES ('" + name
                                + "', '" + lastname + "', '" + school + "');");

                        editName.setText("");
                        editLastName.setText("");
                        editSchool.setText("");

                        Toast.makeText(getApplicationContext(), "เพิ่มข้อมูลเรียบร้อยแล้ว"
                                , Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "คุณมีข้อมูลนี้อยู่แล้ว"
                                , Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "กรุณาใส่ข้อมูลให้ครบทุกช่อง"
                            , Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onStop() {
        super.onStop();
        mHelper.close();
        mDb.close();
    }
}
