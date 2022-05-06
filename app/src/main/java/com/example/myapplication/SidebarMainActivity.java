package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import com.google.android.material.navigation.NavigationView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.databinding.ActivitySidebarMainBinding;

public class SidebarMainActivity extends AppCompatActivity implements View.OnClickListener {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivitySidebarMainBinding binding;
    private LocaleHelper localeHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySidebarMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        setFullScreen(this);
        setLanguage();

        setSupportActionBar(binding.appBarSidebarMain.toolbar);
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        navigationView.setItemIconTintList(null);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_sidebar_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_sidebar_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public static void setFullScreen(AppCompatActivity act){
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