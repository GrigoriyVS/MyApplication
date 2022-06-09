package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.navigation.NavDirections;
import com.example.myapplication.ui.levels.LevelsDirections;
import com.example.myapplication.ui.user.UserFragment;
import com.google.android.material.navigation.NavigationView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.databinding.ActivitySidebarMainBinding;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;



public class SidebarMainActivity extends AppCompatActivity implements View.OnClickListener {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivitySidebarMainBinding binding;
    public static LocaleHelper localeHelper;
    public static int indexWord = 0;
    public static Learning.Mode mode;

    public static SQLhelper db;
    public static SidebarMainActivity mainActivity ;
    {
        mainActivity = this;
    }

    public static void restartLearning(){
        mode = Learning.Mode.Learning;
        indexWord = 0;
    }


    public static ImageView avatar_sideBar;
    public static TextView name_sideBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySidebarMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db = new SQLhelper(this);

        setFullScreen(this);
        setLanguage();

        setMenuBar();
    }
    public void setMenuBar(){
        binding.appBarSidebarMain.appBarLayout.setOnClickListener(this);
        setSupportActionBar(binding.appBarSidebarMain.toolbar);
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        navigationView.setItemIconTintList(null);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.levels, R.id.parts,R.id.learning, R.id.complete,
                R.id.user,
                //R.id.settings,
                R.id.translator,
                R.id.listen)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_sidebar_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }


    @Override
    public boolean onSupportNavigateUp() {
        avatar_sideBar = findViewById(R.id.avatar_sideBar);
        name_sideBar = findViewById(R.id.name_sideBar);
        UserFragment.setSideBarData();
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
        localeHelper= new LocaleHelper(findViewById(R.id.languageBt_main),this);
        localeHelper.setLocale(LocaleHelper.currentLang);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.languageBt_main:
                localeHelper.changeLocale(); break;

            case R.id.levelBt1:
                setLevelToLearn(view, R.string.LevelName_1);
                break;
            case R.id.levelBt2:
                setLevelToLearn(view, R.string.LevelName_2);
                break;
            case R.id.levelBt3:
                setLevelToLearn(view, R.string.LevelName_3);
                break;
            case R.id.levelBt4:
                setLevelToLearn(view, R.string.LevelName_4);
                break;
        }
    }
    private void setLevelToLearn(View view, int level){
        Learning.currentLevel = level;
        NavDirections action = LevelsDirections.actionLevelsToParts();
        Navigation.findNavController(view).navigate(action);
    }

    public void pickImage(){
        //Вызываем стандартную галерею для выбора изображения с помощью Intent.ACTION_PICK:

        Intent intent;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
        }else{
            intent = new Intent(Intent.ACTION_GET_CONTENT);
        }
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, ""), 1);

    }


    @Override //Обрабатываем результат выбора в галерее:
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int takeFlags = Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION;
            ContentResolver resolver = this.getContentResolver();
            Uri uri = imageReturnedIntent.getData();
            resolver.takePersistableUriPermission(uri, takeFlags);
            User.getAuthorizedUser().setImageURI(uri.toString()).updateInDB();
        }
        this.recreate();
    }

}