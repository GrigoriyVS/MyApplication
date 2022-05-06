package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.myapplication.databinding.ActivityMainBinding;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static MainActivity mainActivity ;
    private ActivityMainBinding binding;
    private LocaleHelper localeHelper;

    {
        mainActivity = this;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        System.out.println("Loading...");
        //binding.getRoot().findViewById(R.id.levelBt_1);
        setFullScreen(this);
        setLanguage();

        //ImageButton bt = findViewById(R.id.levelBt_1);
        //bt.setOnClickListener(this);
        //TODO some

        //startActivity(this, new Intent(this, LearningTests.class));
        setConfig();
    }

    public static void setFullScreen(AppCompatActivity act){
        act.getSupportActionBar().hide();

        WindowManager.LayoutParams attrs = act.getWindow().getAttributes();
        attrs.flags = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        act.getWindow().setAttributes(attrs);

        View decorView = act.getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }

    private void setLanguage(){
        localeHelper= new LocaleHelper(findViewById(R.id.languageBt),this);
        localeHelper.setLocale(LocaleHelper.currentLang);
    }

    private void setConfig(){
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_courses, R.id.navigation_learning, R.id.navigation_user)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.languageBt:
                localeHelper.changeLocale(); break;

            case R.id.levelBt_1:
                setLevelToLearn(R.string.LevelName_1);
                break;
            case R.id.levelBt_2:
                setLevelToLearn(R.string.LevelName_2);
                break;
            case R.id.levelBt_3:
                setLevelToLearn(R.string.LevelName_3);
                break;

            case R.id.AuthBt:
                setAuthActivity();
                break;
        }
    }
    private void setLevelToLearn(int level){
        LearningTests.currentLevel = level;
        Intent intent = new Intent(this, LearningTests.class);
        startActivity(intent);
    }
    private void setAuthActivity(){
        Intent intent = new Intent(this, AuthUser.class);
        startActivity(intent);
    }
}