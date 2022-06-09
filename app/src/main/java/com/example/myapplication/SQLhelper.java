package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class SQLhelper extends SQLiteOpenHelper {

    public SQLhelper( Context context) {
        super(context, "user12.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table Users(phone TEXT primary key, language TEXT, name TEXT, imageURI TEXT, isAuthorized TEXT)");
        db.execSQL("create Table Progress(phone TEXT primary key, points TEXT  DEFAULT '0')");
        db.execSQL("create Table Levels(phone TEXT primary key, " +
                "l1_part1 TEXT DEFAULT ''," +
                "l1_part2 TEXT DEFAULT ''," +
                "l1_part3 TEXT DEFAULT ''," +

                "l2_part1 TEXT DEFAULT ''," +
                "l2_part2 TEXT DEFAULT ''," +
                "l2_part3 TEXT DEFAULT ''," +

                "l3_part1 TEXT DEFAULT ''," +
                "l3_part2 TEXT DEFAULT ''," +
                "l3_part3 TEXT DEFAULT ''," +

                "l4_part1 TEXT DEFAULT ''," +
                "l4_part2 TEXT DEFAULT ''," +
                "l4_part3 TEXT DEFAULT '')" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop Table if exists Users");
    }




}