package com.marvell.zoezhu.birthmanager;

import android.app.ActionBar;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private BirthDatabaseHelper dbHelper;

    private List<Item> birthList = new ArrayList<Item>();


    @Override
    protected void onRestart() {
        super.onRestart();
        InitData();
        InitView();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle("");
        setActionBar(myToolbar);
//        myToolbar.setNavigationIcon(R.drawable.ab_android);

        InitData();
        InitView();

//        dbHelper = new BirthDatabaseHelper(this, "Birth.db", null, 1);
//        Button createDatabase = (Button) findViewById(R.id.create_database);
//        createDatabase.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dbHelper.getWritableDatabase();
//            }
//        });
//
//        Button addData = (Button) findViewById(R.id.add_data);
//        addData.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                SQLiteDatabase db = dbHelper.getWritableDatabase();
//                ContentValues values = new ContentValues();
//                values.put("name", "Tom");
//                Date date = new Date();
//                values.put("birthday", String.valueOf(date));
//                db.insert("birthlist", null, values);
//                Toast.makeText(MainActivity.this, "Create succeeded", Toast.LENGTH_SHORT).show();
//
//
//            }
//        });


    }

    private void InitData() {
        dbHelper = new BirthDatabaseHelper(this, "Birth.db", null, 3);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("birthlist", null, null, null, null, null, null);
        birthList.clear();
        if (cursor.moveToFirst())
        {
            do {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                int year = cursor.getInt(cursor.getColumnIndex("birthyear"));
                int month = cursor.getInt(cursor.getColumnIndex("birthmonth"));
                int day = cursor.getInt(cursor.getColumnIndex("birthday"));
                birthList.add(new Item(id, name, year, month, day));
            } while (cursor.moveToNext());
        }
        cursor.close();
    }

    private void InitView() {
        ListView listview = (ListView) findViewById(R.id.birthlist);
        MyAdapter adapter = new MyAdapter(MainActivity.this, R.layout.birth_item_layout, birthList);
        listview.setAdapter(adapter);

        adapter.setOnEditButtonClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                EditActivity.actionStart(MainActivity.this, (Item) v.getTag());

            }
        });
        adapter.setOnRemoveButtonClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Item item = (Item)v.getTag();
                db.delete("birthlist", "id=?", new String[]{String.valueOf(item.id)});
                Toast.makeText(MainActivity.this, "record deleted", Toast.LENGTH_SHORT).show();
                InitData();
                InitView();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String msg = "";
        int id = item.getItemId();
        switch (id){
            case R.id.action_add:
                EditActivity.actionStart(MainActivity.this, new Item(0,null,2000,1,1));
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        if(!msg.equals("")){
            Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
        }
        return true;



    }

}
