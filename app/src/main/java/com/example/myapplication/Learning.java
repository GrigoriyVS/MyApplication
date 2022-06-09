package com.example.myapplication;

import android.content.ContentValues;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.myapplication.databinding.FragmentLearningBinding;
import com.example.myapplication.ui.levels.Levels;
import com.example.myapplication.ui.listen.ListenFragment;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import pl.droidsonroids.gif.GifImageView;

import java.util.ArrayList;
import java.util.Random;

import static com.example.myapplication.SidebarMainActivity.*;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Learning} factory method to
 * create an instance of this fragment.
 */
public class Learning extends Fragment implements View.OnClickListener {

    public static int currentLevel;
    public static int currentPart;
    FragmentLearningBinding binding;

    public enum Mode {Learning, Tests, Complete}




    TextInputLayout inputWord;
    ImageView listenBt;
    ImageView imageTip;
    GifImageView gifTipImage;
    Button checkAnswer;
    Button completeBt;
    TextView levelText;
    TextView partText;
    TextView wordTextRu;
    TextView wordTextii;
    TextView textView6;
    TextView textView8;

    public void setButtons(View view){
        inputWord = view.findViewById(R.id.inputWord);
        listenBt = view.findViewById(R.id.listenBt);
        imageTip = view.findViewById(R.id.imageTip);
        gifTipImage = view.findViewById(R.id.gifTipImage);
        checkAnswer = view.findViewById(R.id.checkAnswer);
        completeBt = view.findViewById(R.id.completeBt);
        levelText = view.findViewById(R.id.levelText);
        partText = view.findViewById(R.id.partText);
        wordTextRu = view.findViewById(R.id.WordTextRu);
        wordTextii = view.findViewById(R.id.WordTextii);
        textView6 = view.findViewById(R.id.textView6);
        textView8 = view.findViewById(R.id.textView8);

        listenBt.setOnClickListener(this);
        imageTip.setOnClickListener(this);
        gifTipImage.setOnClickListener(this);
        checkAnswer.setOnClickListener(this);
        completeBt.setOnClickListener(this);
        levelText.setText(this.getString(currentLevel)+":");
        partText.setText(this.getString(currentPart));
    }

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLearningBinding.inflate(inflater, container, false);
        view = binding.getRoot();
        setButtons(view);
        updateLanguage();

        setWords();
        loadPage();

        return view;
    }

    ArrayList<Word> words = new ArrayList<>();
    Word word;
    public void setWords(){
        int level = getLevel();
        int part = getPart();
        for(int i = 0; i < Levels.levelsFr.levels[level].parts[part].words.length; i++){
            words.add(Levels.levelsFr.levels[level].parts[part].words[i]);
        }
    }

    private void setLearningMode(){
        mode = Mode.Learning;

        textView6.setVisibility(View.GONE);
        textView8.setVisibility(View.GONE);
        inputWord.setVisibility(View.GONE);
        checkAnswer.setVisibility(View.GONE);
        gifTipImage.setVisibility(View.GONE);

        wordRuEnable(View.VISIBLE);
        completeBt.setVisibility(View.VISIBLE);
        imageTip.setVisibility(View.VISIBLE);
    }

    private void setTestsMode(){
        mode = Mode.Tests;
        wordRuEnable(View.GONE);
        completeBt.setVisibility(View.GONE);

        imageTip.setVisibility(View.INVISIBLE);

        gifTipImage.setVisibility(View.VISIBLE);
        textView6.setVisibility(View.VISIBLE);
        textView8.setVisibility(View.VISIBLE);
        inputWord.setVisibility(View.VISIBLE);
        checkAnswer.setVisibility(View.VISIBLE);
    }

    private void setCompleteMode(){
        mode = Mode.Complete;
        gifTipImage.setVisibility(View.GONE);
        completeBt.setVisibility(View.VISIBLE);
        imageTip.setVisibility(View.VISIBLE);
        inputWord.setEnabled(false);
        checkAnswer.setEnabled(false);
        wordRuEnable(View.VISIBLE);
    }

    private void wordRuEnable(int visible){
        wordTextRu.setVisibility(visible);
        listenBt.setVisibility(visible);
    }


    private void nextWord(){
        indexWord++;
    }
    private void loadPage(){
        if(mode == null){
            setLearningMode();
        }
        else{
            switch (mode){
                case Learning: setLearningMode(); break;
                case Tests: setTestsMode(); break;
                case Complete: setCompleteMode(); break;
            }
        }

        if(indexWord == words.size()){
            switch (mode){
                case Learning:
                    indexWord=0;
                    setTestsMode();
                    break;
                case Tests:
                case Complete:
                    restartLearning();

                    CompletePart.points = (words.size() - errors)*5 - errors*3;
                    if(CompletePart.points<0) CompletePart.points=0;

                    saveProgress();
                    CompletePart.load(view);
                    return;
            }
        }
        word = words.get(indexWord);
        loadWord(word);
    }

    private void loadWord(Word word){
        wordTextRu.setText(word.ru);
        wordTextii.setText(word.ii);
        imageTip.setImageResource(word.image);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.checkAnswer: checkAnswer(); break;
            case R.id.completeBt:
                nextWord();
                loadPage();
                if(mode == Mode.Complete){
                    setTestsMode();
                    ((TextInputEditText)this.view.findViewById(R.id.inputEditeRu)).setText("");
                    inputWord.setEnabled(true);
                    checkAnswer.setEnabled(true);
                    binding.checkAnswer.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(83,176,232)));
                }
                break;
            case R.id.gifTipImage:
            case R.id.imageTip:
                if(User.getPoints()<10) return;
                imageTip.setVisibility(View.VISIBLE);
                gifTipImage.setVisibility(View.GONE);

                updateProgressToDB(-10);
                break;
            case R.id.listenBt:
                ListenFragment.load(view,wordTextRu.getText().toString());
                break;
        }
    }
    private void updateLanguage(){
        if(textView6.getText().toString().equals("Hint")){//default value isn't possible
            LocaleHelper.updateLocale();
        }
    }
    static int errors = 0;
    private void checkAnswer(){
        if(completeBt.getVisibility() == View.VISIBLE) return;
        if(inputWord.getEditText().getText().toString().equals(word.ru)){
            setCompleteMode();
        }else{
            errors++;
            binding.checkAnswer.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(255,150,150)));
        }
    }

    public static int getLevel(){
        for (int level = 0; level<Levels.levelId.length; level++){
            if(Levels.levelId[level]==currentLevel) return level;
        }
        return -1;
    }
    public static int getPart(){
        for (int part = 0; part<Parts.partId.length; part++){
            if(Parts.partId[part]==currentPart) return part%3;
        }
        return -1;
    }
    private void saveProgress(){
        int level = getLevel();
        int part = getPart();

        updateLevelsToDB(level, part);
        updateProgressToDB(CompletePart.points);
    }
    public long updateLevelsToDB(int level, int part){
        return db.getWritableDatabase()
                .update("Levels",
                        getContestValuesLevels(level, part),
                        "phone=?",
                        new String[]{User.getPhone()});
    }
    public long updateProgressToDB(int points){
        return db.getWritableDatabase()
                .update("Progress",
                        getContestValuesProgress(points),
                        "phone=?",
                        new String[]{User.getPhone()});
    }
    private static ContentValues getContestValuesLevels(int level, int part){
        ContentValues contentValues = new ContentValues();
        level++; part++;
        contentValues.put("l"+level+"_part"+part, String.valueOf(true));
        return contentValues;
    }
    private static ContentValues getContestValuesProgress(int points){
        ContentValues contentValues = new ContentValues();
        contentValues.put("points", String.valueOf(points+User.getPoints()));
        return contentValues;
    }
    public static ArrayList<Vector2> getCompleteParts(){
        SQLiteDatabase _db = db.getWritableDatabase();
        Cursor cursor = _db.rawQuery("Select * from Levels where phone = ?",new String[]{User.getAuthorizedUser().phone});
        if(cursor.getCount()>0) {
            ArrayList<Vector2> level_Part = new ArrayList<>();
            while (cursor.moveToNext()){
                for(int i = 0; i< cursor.getColumnCount(); i++){
                    if(cursor.getString(i).equals(String.valueOf(true)))
                        level_Part.add(parse(i));
                }
            }
            return level_Part;
        }
        return null;
    }
    private static Vector2 parse(int value){
        value--;//0-phone
        return new Vector2(value/3, value%3);
    }
    public static class Vector2{
        public int level;
        public int part;
        public Vector2(int level, int part) {
            this.level = level;
            this.part = part;
        }
    }


}
