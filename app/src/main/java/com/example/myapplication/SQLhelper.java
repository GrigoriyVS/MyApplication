package com.example.myapplication;

import android.database.sqlite.SQLiteDatabase;

import static android.content.Context.MODE_PRIVATE;

public class SQLhelper {
    private static MainActivity m;
    private static SQLiteDatabase dbUser;
    private static SQLiteDatabase dbWords;

    public static void SetActivity(MainActivity mainActivity){
        m = mainActivity;
    }

    public static void openOrCreateDatabases(){
        if(m == null) return;
        dbUser = m.getBaseContext().openOrCreateDatabase("user.db", MODE_PRIVATE, null);
        dbWords = m.getBaseContext().openOrCreateDatabase("words.db", MODE_PRIVATE, null);
    }

    public static void addUser(User user){
        openOrCreateDatabases();
        dbUser.execSQL("INSERT OR IGNORE INTO users VALUES ('Tom Smith', 23), ('John Dow', 31);");
        close();
    }

    public static void addWord(Word word){
        openOrCreateDatabases();
        dbWords.execSQL("INSERT OR IGNORE INTO users VALUES ('Tom Smith', 23), ('John Dow', 31);");
        close();
    }

    public static void close(){
        if(m == null) return;
        dbUser.close();
        dbWords.close();
    }

}