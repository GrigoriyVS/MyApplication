package com.example.myapplication;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
//import org.json.simple.JSONObject;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.preference.PreferenceManager;
import org.json.JSONException;
import org.json.JSONObject;

import static android.content.Context.MODE_PRIVATE;


public class User {
    private String filePath = "User";

    private String fileName = File.separator+"user.json";
    private String name;
    private String language;

    public User()  {


        File file = new File(filePath+fileName);
        if(file.exists()){
            LoadData();
        }
        else{
            Autorization();
            SaveData();
        }
    }

    private void SaveData()  {

        File filePath1 = new File(filePath);
        filePath1.mkdirs();

        File file = new File(filePath1 + fileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
/*
        new File(filePath)
        File file = new File("Example.txt");

        // Создание файла
        try {
            if(!file.exists())
                file.createNewFile();
        } catch (IOException e) { }
*/
        JSONObject resultJson = new JSONObject();
        try(FileWriter writer = new FileWriter(file))
        {
            resultJson.put("User",this);
            resultJson.put("User",this);
            writer.write(resultJson.toString());
        }
        catch(IOException ex){}
        catch (JSONException e) {}
    }
    private void LoadData() {
        String userJson = "";
        Scanner scanner = new Scanner(filePath+fileName);
        while (scanner.hasNextLine())
            userJson+=scanner.nextLine();

        User user = null;
        try {
            user = (User) new JSONObject(userJson).get("user");
        } catch (JSONException e) {}
        this.name = user.name;
        this.language = user.language;
    }

    private void Autorization(){
        name= "Гриша";
        language="ii";
    }
}
