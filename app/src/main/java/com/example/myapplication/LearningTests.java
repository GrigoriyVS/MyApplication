package com.example.myapplication;


import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class LearningTests extends AppCompatActivity implements View.OnClickListener {

    public static int currentLevel;
    private LocaleHelper localeHelper;

    public void setButtons(){
        Button checkAnswer = findViewById(R.id.checkAnswer);
        localeHelper= new LocaleHelper(findViewById(R.id.languageBt_LearningAct),this);
        localeHelper.setLocale(LocaleHelper.currentLang);

        checkAnswer.setOnClickListener(this);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_tests);
        MainActivity.setFullScreen(this);

        TextView t = findViewById(R.id.level_name);
        t.setText(currentLevel);

        ImageView i = findViewById(R.id.imageTip);
        i.setImageResource(R.drawable.tip);
        i.setOnClickListener(this);
        setButtons();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.checkAnswer: System.out.println("click"); break;
            case R.id.languageBt_LearningAct: localeHelper.changeLocale(); break;
            case R.id.imageTip: ((ImageView)view).setImageResource(R.drawable.ii); break;
        }
    }
}