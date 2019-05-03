package com.example.mysoap;


import android.support.v7.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewAddressActivity extends AppCompatActivity {

    DatabaseStudent mHelper;
    SQLiteDatabase mDb;
    Cursor mCursor;
    ListView listStudent;

    ImageView backtoadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_address);
        backtoadd = findViewById(R.id.imge1);
        backtoadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewAddressActivity.this,AddActivity.class);
                startActivity(intent);
            }
        });



        mHelper = new DatabaseStudent(this);
        mDb = mHelper.getReadableDatabase();

        mCursor = mDb.rawQuery("SELECT * FROM " + DatabaseStudent.TABLE_NAME, null);

        ArrayList<String> arr_list = new ArrayList<String>();
        mCursor.moveToFirst();
        while(!mCursor.isAfterLast() ){
            arr_list.add("ชื่อ : " + mCursor.getString(mCursor.getColumnIndex(DatabaseStudent.COL_NAME))
                    + "\nเบอร์โทร" + mCursor.getString(mCursor.getColumnIndex(DatabaseStudent.COL_LASTNAME))
                    + "\nที่อยู่ : " + mCursor.getString(mCursor.getColumnIndex(DatabaseStudent.COL_SCHOOL)));
            mCursor.moveToNext();
        }

        ArrayAdapter<String> adapterDir = new ArrayAdapter<String>(getApplicationContext(), R.layout.my_listview, arr_list);

        listStudent = (ListView)findViewById(R.id.listStudent);
        listStudent.setAdapter(adapterDir);
        listStudent.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                mCursor.moveToPosition(arg2);

                String name = mCursor.getString(mCursor.getColumnIndex(DatabaseStudent.COL_NAME));
                String lastname = mCursor.getString(mCursor.getColumnIndex(DatabaseStudent.COL_LASTNAME));
                String school = mCursor.getString(mCursor.getColumnIndex(DatabaseStudent.COL_SCHOOL));

                AlertDialog.Builder builder = new AlertDialog.Builder(ViewAddressActivity.this);
                builder.setTitle("ข้อมูลนักเรียน");
                builder.setMessage("ชื่อ : " + name + "\n\nเบอร์โทร : " + lastname + "\n\nโรงเรียน : " + school);
                builder.setNeutralButton("OK", null);
                builder.show();
            }
        });
    }

    public void onStop() {
        super.onStop();
        mHelper.close();
        mDb.close();
    }
}
