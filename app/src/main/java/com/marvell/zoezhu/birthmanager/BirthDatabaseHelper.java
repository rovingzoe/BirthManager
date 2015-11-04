package com.marvell.zoezhu.birthmanager;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by zoezhu on 2015/11/3.
 */
public class BirthDatabaseHelper extends SQLiteOpenHelper{

    public static final String CREATE_BIRTHLIST = "create table birthlist ("
            + "id integer primary key autoincrement, "
            + "name text, "
            + "birthday date)";
    Context mContext;
    public BirthDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BIRTHLIST);
        Toast.makeText(mContext, "Create succeeded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists birthlist");
        onCreate(db);
    }
}
