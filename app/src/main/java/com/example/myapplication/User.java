package com.example.myapplication;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
//import org.json.simple.JSONObject;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import com.example.myapplication.ui.levels.Levels;
import org.json.JSONException;
import org.json.JSONObject;

import static com.example.myapplication.SidebarMainActivity.db;


public class User {

    //о себе
    public String name;
    public String language;
    public String imageURI;

    //обязательные
    public String phone;

    //дополнительно
    public boolean isAuthorized = false;


    public User(String phone)  {
        this.phone = phone;
        this.isAuthorized = true;
    }
    public User(String phone, String language, String name, String imageURI, String isAuthorized)  {
        this(phone);
        this.language = language;
        if(name!=null) this.name = name;
        if(imageURI!=null) this.imageURI = imageURI;
        if(isAuthorized!=null) this.isAuthorized = Boolean.parseBoolean(isAuthorized);
    }


    private static ArrayList<User> getUsers(String sql, String[] arg){
        SQLiteDatabase _db = db.getWritableDatabase();
        Cursor cursor = _db.rawQuery(sql, arg);
        if(cursor.getCount()>0) {
            ArrayList<User> user = new ArrayList<>();
            while (cursor.moveToNext()){
                user.add(new User(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4))
                );
            }
            return user;
        }
        return null;
    }
    public static ArrayList<User> getUsers(){
        return getUsers("Select * from Users",null);
    }
    public static User getUser(String phone){
        ArrayList<User> users = getUsers("Select * from Users where phone = ?",new String[]{phone});
        if(users!=null) return users.get(0);
        else return null;
    }
    public static User getAuthorizedUser(){
        ArrayList<User> users = getUsers("Select * from Users where isAuthorized = ?",new String[]{String.valueOf(true)});
        if(users!=null) return users.get(0);
        else return null;
    }

    //обновляет все данные юзера по номеру телефона
    public long updateInDB(){
        User user = getUser(this.phone);
        if(user != null) {
            return db.getWritableDatabase()
                    .update("Users",
                            getContestValues(this, false),
                            "phone=?",
                            new String[]{this.phone});
        }
        else return -1;
    }
    public long insertToDB(){
        User user = getUser(this.phone);
        long flag = -1;
        if(user == null){
            flag = db.getWritableDatabase()
                    .insert("Users", null,
                            getContestValues(this, true));
            flag += db.getWritableDatabase()
                    .insert("Progress", null,
                            getContestPhone(this.phone));
            flag += db.getWritableDatabase()
                    .insert("Levels", null,
                            getContestPhone(this.phone));
        }
        return flag;
    }
    private static ContentValues getContestValues(User user, boolean isInsert){
        ContentValues contentValues = new ContentValues();
        if(isInsert)contentValues.put("phone", user.phone);
        contentValues.put("language", user.language);
        contentValues.put("name", user.name);
        contentValues.put("imageURI", user.imageURI);
        contentValues.put("isAuthorized", String.valueOf(user.isAuthorized));
        return contentValues;
    }
    private static ContentValues getContestPhone(String phone){
        ContentValues contentValues = new ContentValues();
        contentValues.put("phone", phone);
        return contentValues;
    }

    public static void signIn(View view, String phone){
        User user = User.getUser(phone);
        if(user == null){//new
            if(new User(phone).insertToDB()==-1) return;
        }else{
            if(user.setAuthorized(true).updateInDB()==-1) return;
        }
        signIn(view);
    }
    public static void signIn(View view){
        if(User.getAuthorizedUser() != null){
            Levels.loadFromSignIn(view);
        }
    }
    public static void signOut(View view){
        User user = User.getAuthorizedUser();
        user.setAuthorized(false).updateInDB();
        SignIn.load(view);
    }

/*
    public void Auth(){
        if(!SidebarMainActivity.db.exist(this)) {
            SidebarMainActivity.db.insertUser(this);
        }
        this.isAuthorized = true;
    }
*/

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public User setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public User setLanguage(String language) {
        this.language = language;
        return this;
    }
    public User setLanguage(boolean isRU) {
        this.language = isRU?"ru":"ii";
        return this;
    }

    public User setImageURI(String imageURI) {
        this.imageURI = imageURI;
        return this;
    }

    public User setAuthorized(boolean authorized) {
        isAuthorized = authorized;
        return this;
    }

    public static Bitmap getImage(){
        String imageURI = User.getAuthorizedUser().imageURI;
        if(imageURI == null) return null;
        Uri uri = Uri.parse(imageURI);
        final InputStream imageStream;
        try {
            imageStream = SidebarMainActivity.mainActivity.getContentResolver().openInputStream(uri);
            return BitmapFactory.decodeStream(imageStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static String getName(){
        String name = User.getAuthorizedUser().name;
        if(name == null) name = "no_name";
        return name;
    }

    public static String getLanguage() {
        User user = User.getAuthorizedUser();
        if(user == null) return "ii";
        String language = user.language;
        if(language == null) {
            user.setLanguage(false).updateInDB();
            language = "ii";
        }
        return language;
    }

    public static String getPhone() {
        return User.getAuthorizedUser().phone;
    }

    public static int getPoints() {
        SQLiteDatabase _db = db.getWritableDatabase();
        Cursor cursor = _db.rawQuery("Select * from Progress where phone = ?",new String[]{User.getPhone()});
        if(cursor.getCount()>0) {
            while (cursor.moveToNext()){
                return Integer.parseInt(cursor.getString(1));
            }
        }
        return -1;
    }
}
