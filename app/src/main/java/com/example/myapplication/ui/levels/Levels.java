package com.example.myapplication.ui.levels;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import com.example.myapplication.*;
import com.example.myapplication.databinding.FragmentLearningLevelsBinding;
import com.example.myapplication.ui.user.UserFragmentDirections;

import java.util.ArrayList;

import static com.example.myapplication.Learning.getCompleteParts;

public class Levels extends Fragment {

    public static Levels levelsFr;

    public enum LevelId{
        level1,
        level2,
        level3,
        level4
    }
    public static int[] levelId = {
            R.string.LevelName_1,
            R.string.LevelName_2,
            R.string.LevelName_3,
            R.string.LevelName_4,
    };

    private FragmentLearningLevelsBinding binding;
    public Level[] levels =
        new Level[]{
            new Level(//1 буквы
                new Part[]{
                    new Part(
                        new Word[]{
                            new Word(LevelId.level1, Parts.PartId.l1_part1, "不", "Б", R.drawable.b),
                            new Word(LevelId.level1, Parts.PartId.l1_part1, "无", "В", R.drawable.v),
                            new Word(LevelId.level1, Parts.PartId.l1_part1, "有", "Й", R.drawable.j),
                            new Word(LevelId.level1, Parts.PartId.l1_part1, "可", "К", R.drawable.k),
                            new Word(LevelId.level1, Parts.PartId.l1_part1, "来", "Л", R.drawable.l),
                            new Word(LevelId.level1, Parts.PartId.l1_part1, "没", "М", R.drawable.m),
                            new Word(LevelId.level1, Parts.PartId.l1_part1, "你", "Н", R.drawable.n),
                        }
                    ),
                    new Part(
                        new Word[]{
                            new Word(LevelId.level1, Parts.PartId.l1_part2, "啊", "А", R.drawable.a),
                            new Word(LevelId.level1, Parts.PartId.l1_part2, "伊", "И", R.drawable.i),
                            new Word(LevelId.level1, Parts.PartId.l1_part2, "诶", "Е", R.drawable.e),
                            new Word(LevelId.level1, Parts.PartId.l1_part2, "有", "Ё", R.drawable.jo),
                            new Word(LevelId.level1, Parts.PartId.l1_part2, "哦", "О", R.drawable.o),
                            new Word(LevelId.level1, Parts.PartId.l1_part2, "于", "Ы", R.drawable.bl),
                        }
                    ),
                    new Part(
                        new Word[]{
                            new Word(LevelId.level1, Parts.PartId.l1_part3, "零", "0", R.drawable.nul),
                            new Word(LevelId.level1, Parts.PartId.l1_part3, "一", "1", R.drawable.one),
                            new Word(LevelId.level1, Parts.PartId.l1_part3, "二", "2", R.drawable.two),
                            new Word(LevelId.level1, Parts.PartId.l1_part3, "三", "3", R.drawable.three),
                            new Word(LevelId.level1, Parts.PartId.l1_part3, "四", "4", R.drawable.four),
                            new Word(LevelId.level1, Parts.PartId.l1_part3, "五", "5", R.drawable.five),
                            new Word(LevelId.level1, Parts.PartId.l1_part3, "六", "6", R.drawable.six),
                            new Word(LevelId.level1, Parts.PartId.l1_part3, "七", "7", R.drawable.seven),
                            new Word(LevelId.level1, Parts.PartId.l1_part3, "八", "8", R.drawable.eight),
                            new Word(LevelId.level1, Parts.PartId.l1_part3, "九", "9", R.drawable.nine),
                        }
                    )
                }
            ),
            new Level(//2 слоги
                new Part[]{
                    new Part(
                        new Word[]{
                            new Word(LevelId.level2, Parts.PartId.l1_part1, "啊", "Ма", R.drawable.ma),
                            new Word(LevelId.level2, Parts.PartId.l1_part1, "头", "Па", R.drawable.pa),
                            new Word(LevelId.level2, Parts.PartId.l1_part1, "爷", "Да", R.drawable.da),
                            new Word(LevelId.level2, Parts.PartId.l1_part1, "爸", "Ба", R.drawable.ba),
                            new Word(LevelId.level2, Parts.PartId.l1_part1, "诶", "Ва", R.drawable.va),
                        }
                    ),
                    new Part(
                        new Word[]{
                            new Word(LevelId.level2, Parts.PartId.l1_part2, "妈妈", "Мама", R.drawable.mom),
                            new Word(LevelId.level2, Parts.PartId.l1_part2, "爸爸", "Папа", R.drawable.father),
                        }
                    ),
                    new Part(
                        new Word[]{
                            new Word(LevelId.level2, Parts.PartId.l1_part3, "爷爷", "Дедушка", R.drawable.gdad),
                            new Word(LevelId.level2, Parts.PartId.l1_part3, "祖母", "Бабушка", R.drawable.gmom),
                        }
                    )
                }
            ),
            new Level(//3 слова
                new Part[]{
                    new Part(
                        new Word[]{
                            new Word(LevelId.level3, Parts.PartId.l1_part1, "木头", "Дерево", R.drawable.tree),
                            new Word(LevelId.level3, Parts.PartId.l1_part1, "森林", "Лес", R.drawable.forest),
                            new Word(LevelId.level3, Parts.PartId.l1_part1, "海", "Море", R.drawable.sea),
                            new Word(LevelId.level3, Parts.PartId.l1_part1, "云", "Облако", R.drawable.cloud),
                            new Word(LevelId.level3, Parts.PartId.l1_part1, "场地", "Поле", R.drawable.fild),
                        }
                    ),
                    new Part(
                        new Word[]{
                            new Word(LevelId.level3, Parts.PartId.l1_part2, "一", "Один", R.drawable.one_d),
                            new Word(LevelId.level3, Parts.PartId.l1_part2, "二", "Два", R.drawable.two_d),
                        }
                    ),
                    new Part(
                        new Word[]{
                            new Word(LevelId.level3, Parts.PartId.l1_part3, "兄弟", "Брат", R.drawable.brother),
                            new Word(LevelId.level3, Parts.PartId.l1_part3, "姐姐", "Сестра", R.drawable.sister),
                        }
                    )
                }
            ),
            new Level(//4 предложения
                new Part[]{
                    new Part(
                        new Word[]{
                            new Word(LevelId.level4, Parts.PartId.l1_part1, "大森林", "Большой лес", R.drawable.big_forest),
                            new Word(LevelId.level4, Parts.PartId.l1_part1, "草原和田野", "Степи и поля", R.drawable.fild),
                            new Word(LevelId.level4, Parts.PartId.l1_part1, "森林附近的河流", "Река у леса", R.drawable.forest_river),
                            new Word(LevelId.level4, Parts.PartId.l1_part1, "森林附近的湖", "Озеро у леса", R.drawable.sea_forest),
                            new Word(LevelId.level4, Parts.PartId.l1_part1, "田野附近的森林", "Моря и океаны", R.drawable.sea),
                        }
                    ),
                    new Part(
                        new Word[]{
                            new Word(LevelId.level4, Parts.PartId.l1_part2, "兄弟姐妹", "брат и сестра", R.drawable.brother_sister),
                            new Word(LevelId.level4, Parts.PartId.l1_part2, "亲爱的妈妈", "Любимая мама", R.drawable.mom),
                        }
                    ),
                    new Part(
                        new Word[]{
                            new Word(LevelId.level4, Parts.PartId.l1_part3, "这是我的小妹妹", "Это моя младшая сестра", R.drawable.sister),
                            new Word(LevelId.level4, Parts.PartId.l1_part3, "你有兄弟吗？", "У тебя есть брат?", R.drawable.brother),
                        }
                    )
                }
            )
        };


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLearningLevelsBinding.inflate(inflater, container, false);
        updateLanguage();

        levelsFr = this;


        completeVisibilityOff();
        completeVisibilityOn();

        View root = binding.getRoot();
        TextView b1 = binding.levelBt1;
        TextView b2 = binding.levelBt2;
        TextView b3 = binding.levelBt3;
        TextView b4 = binding.levelBt4;
        b1.setOnClickListener(SidebarMainActivity.mainActivity);
        b2.setOnClickListener(SidebarMainActivity.mainActivity);
        b3.setOnClickListener(SidebarMainActivity.mainActivity);
        b4.setOnClickListener(SidebarMainActivity.mainActivity);
        return root;
    }
    private void completeVisibilityOff(){
        binding.levelBt1.setTextColor(Color.rgb(0,0,0));
        binding.levelBt2.setTextColor(Color.rgb(0,0,0));
        binding.levelBt3.setTextColor(Color.rgb(0,0,0));
        binding.levelBt4.setTextColor(Color.rgb(0,0,0));
    }
    private void completeVisibilityOn(){
        int count1, count2, count3, count4;
        count1= count2= count3= count4= 0;
        ArrayList<Learning.Vector2> completeParts = getCompleteParts();
        for (int i = 0; i< completeParts.size(); i++){
            switch (completeParts.get(i).level){
                case 0: count1++; break;
                case 1: count2++; break;
                case 2: count3++; break;
                case 3: count4++; break;
            }
        }
        if(count1==3)binding.levelBt1.setTextColor(Color.rgb(150,255,150));
        if(count2==3)binding.levelBt2.setTextColor(Color.rgb(150,255,150));
        if(count3==3)binding.levelBt3.setTextColor(Color.rgb(150,255,150));
        if(count4==3)binding.levelBt4.setTextColor(Color.rgb(150,255,150));
    }

    private void updateLanguage(){
        if(binding.level2Name.getText().toString().equals("2")){//default value isn't possible
            LocaleHelper.updateLocale();
        }
    }

    public static void loadFromSignIn(View view){
        NavDirections action = SignInDirections.actionSignInToLevels();
        Navigation.findNavController(view).navigate(action);
    }
    public static void loadFromComplete(View view){
        NavDirections action = CompletePartDirections.actionCompleteToLevels();
        Navigation.findNavController(view).navigate(action);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

