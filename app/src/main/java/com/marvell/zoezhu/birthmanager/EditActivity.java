package com.marvell.zoezhu.birthmanager;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class EditActivity extends Activity {

    private BirthDatabaseHelper dbHelper;

    final  int EDIT_MODE = 1;
    final  int ADD_MODE = 0;

    private Item item;

    private int id;
    private String name;
    private int birth_day;
    private int birth_month;
    private int birth_year;

    private int mode;

    CalendarView cv;

    public static void actionStart(Context context, Item i) {
        Intent intent = new Intent(context, EditActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("item", i);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle("");
        setActionBar(myToolbar);

        dbHelper = new BirthDatabaseHelper(this, "Birth.db", null, 3);

        item = (Item) getIntent().getSerializableExtra("item");

        id = item.id;
        name = item.name;
        birth_day = item.birthday;
        birth_month = item.birthmonth;
        birth_year = item.birthyear;
        mode = id == 0 ? ADD_MODE:  EDIT_MODE;

        EditText nameView = (EditText) findViewById(R.id.field1_val);
        EditText dateShow = (EditText) findViewById(R.id.field2_val);
        dateShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dlg = new DatePickerDialog(EditActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        birth_day = dayOfMonth;
                        birth_month = monthOfYear + 1;
                        birth_year = year;
                        showDate(birth_year, birth_month, birth_day);
                    }
                }, birth_year, birth_month - 1, birth_day);
                dlg.show();
            }
        });

        nameView.setText(name);
        dateShow.setText(birth_year + "-" + birth_month + "-" + birth_day);
    }


    private void showDate(int year, int month, int day)
    {
        EditText show = (EditText) findViewById(R.id.field2_val);
        show.setText(year + "-" +  month + "-" + day);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String msg = "";
        int menu_id = item.getItemId();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        switch (menu_id){
            case R.id.action_save:
                ContentValues values = new ContentValues();
                EditText nameView = (EditText) findViewById(R.id.field1_val);
                values.put("name", nameView.getText().toString());
                values.put("birthday", birth_day);
                values.put("birthmonth", birth_month);
                values.put("birthyear", birth_year);
                if(mode == ADD_MODE)
                {
                    db.insert("birthlist", null, values);
                }
                else
                {

                    String[] whereArgs = {String.valueOf(id)};
                    db.update("birthlist", values,"id=?", whereArgs);
                }

                msg += "record changed";
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        if(!msg.equals("")){
            Toast.makeText(EditActivity.this, msg, Toast.LENGTH_SHORT).show();
        }
        EditActivity.this.finish();
        return true;
    }
}
