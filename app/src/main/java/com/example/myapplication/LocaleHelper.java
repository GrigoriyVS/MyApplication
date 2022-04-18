package com.example.myapplication;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;


public class LocaleHelper {
    enum Languages{ii,ru}

    public static Languages currentLang;
    private static Languages defaultLang = Languages.ii;
    public AppCompatActivity context;
    private ImageButton langBt;

    public void changeLocale() {
        if(currentLang==Languages.ru)
            setLocale(Languages.ii);
        else setLocale(Languages.ru);
    }

    public LocaleHelper(ImageButton languageBt,AppCompatActivity activity){
        context = activity;
        langBt = languageBt;
        langBt.setOnClickListener((View.OnClickListener) context);
    }

    public void setLocale(Languages newLang) {
        SetImageBt();
        if(currentLang == newLang && newLang != null) return;

        Locale locale;
        if(currentLang==null){
            if(newLang==null){
                locale = new Locale(defaultLang.name());
            }
            else locale = new Locale(currentLang.name());
        }else locale = new Locale(newLang.name());
        currentLang = Languages.valueOf(locale.getLanguage());
        Locale.setDefault(locale);

        Configuration config = context.getResources().getConfiguration();
        config.setLocale(locale);
        context.createConfigurationContext(config);
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
        context.recreate();
    }

    public void SetImageBt(){
        if(currentLang == null) return;
        switch (currentLang) {
            case ii:
                langBt.setImageResource(R.drawable.ii);
                break;
            case ru:
                langBt.setImageResource(R.drawable.ru);
                break;
        }
    }
}