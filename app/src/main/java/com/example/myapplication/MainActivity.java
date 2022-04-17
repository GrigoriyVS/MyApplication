package com.example.myapplication;

import android.os.Bundle;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.myapplication.databinding.ActivityMainBinding;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static MainActivity mainActivity ;
    private ActivityMainBinding binding;

    {
        mainActivity = this;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setFullScreen();
        setLanguage();

        //TODO

        setConfig();
    }

    private void setFullScreen(){
        getSupportActionBar().hide();

        WindowManager.LayoutParams attrs = getWindow().getAttributes();
        attrs.flags = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setAttributes(attrs);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }

    private void setLanguage(){
        LocaleHelper.setLanguageBt(findViewById(R.id.languageBt));
        LocaleHelper.setLocale(LocaleHelper.currentLang);
    }

    private void setConfig(){
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_courses, R.id.navigation_learning, R.id.navigation_user)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }
}