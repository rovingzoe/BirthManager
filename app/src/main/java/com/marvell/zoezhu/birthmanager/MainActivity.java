package com.marvell.zoezhu.birthmanager;

import android.app.ActionBar;
import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.Date;

public class MainActivity extends Activity {

    private BirthDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setActionBar(myToolbar);
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
