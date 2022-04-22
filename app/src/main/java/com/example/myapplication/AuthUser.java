package com.example.myapplication;

import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class AuthUser extends AppCompatActivity implements View.OnClickListener {
    private LocaleHelper localeHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_user);
        MainActivity.setFullScreen(this);

        localeHelper= new LocaleHelper(findViewById(R.id.languageBt_Auth),this);
        localeHelper.setLocale(LocaleHelper.currentLang);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.languageBt_Auth: localeHelper.changeLocale(); break;
        }
    }
}