package com.example.myapplication;

import android.database.sqlite.SQLiteDatabase;

import static android.content.Context.MODE_PRIVATE;

public class SQLhelper {
    MainActivity m;
    SQLiteDatabase db;

    public SQLhelper(MainActivity m) {
        this.m = m;
        db = m.getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);
        db.execSQL("INSERT OR IGNORE INTO users VALUES ('Tom Smith', 23), ('John Dow', 31);");
    }
}
