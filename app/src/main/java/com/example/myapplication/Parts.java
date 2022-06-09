package com.example.myapplication;

import android.os.Bundle;
import android.widget.ImageView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import com.example.myapplication.databinding.FragmentPartsBinding;

import java.util.ArrayList;

import static com.example.myapplication.Learning.getCompleteParts;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Parts} factory method to
 * create an instance of this fragment.
 */
public class Parts extends Fragment implements View.OnClickListener{

    public ArrayList<Part> parts;
    FragmentPartsBinding binding;
    View view;

    public enum PartId{
        l1_part1,
        l1_part2,
        l1_part3,

        l2_part1,
        l2_part2,
        l2_part3,

        l3_part1,
        l3_part2,
        l3_part3,

        l4_part1,
        l4_part2,
        l4_part3
    }
    public static int[] partId = {
            R.string.part_consonants,
            R.string.part_vowels,
            R.string.part_num,

            R.string.l2_part_wordPart,
            R.string.l2_part_wordPart2,
            R.string.l2_part_wordPart3,

            R.string.l3_words1,
            R.string.l3_words2,
            R.string.l3_words3,

            R.string.l4_combinWords1,
            R.string.l4_combinWords2,
            R.string.l4_combinWords3,
    };
    public static int[] partImageId = {
            R.drawable.sogl,
            R.drawable.glas,
            R.drawable.p123,

            R.drawable.slog1,
            R.drawable.slog2,
            R.drawable.slog3,

            R.drawable.priroda,
            R.drawable.p123,
            R.drawable.family,

            R.drawable.priroda,
            R.drawable.family,
            R.drawable.family,
    };



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentPartsBinding.inflate(inflater, container, false);
        view = binding.getRoot();

        ImageView part1 = view.findViewById(R.id.part1Bt);
        ImageView part2 = view.findViewById(R.id.part2Bt);
        ImageView part3 = view.findViewById(R.id.part3Bt);

        loadDataPart();

        part1.setOnClickListener(this);
        part2.setOnClickListener(this);
        part3.setOnClickListener(this);
        completeVisibility(View.GONE);
        completeVisibilityOn();
        return view;
    }
    private void loadDataPart(){
        int level = Learning.getLevel();

        binding.part1Bt.setImageResource(partImageId[level*3]);
        binding.part2Bt.setImageResource(partImageId[level*3+1]);
        binding.part3Bt.setImageResource(partImageId[level*3+2]);

        binding.part1Text.setText(partId[level*3]);
        binding.part2Text.setText(partId[level*3+1]);
        binding.part3Text.setText(partId[level*3+2]);
    }
    private void completeVisibility(int visibility){
        binding.gifComplete1.setVisibility(visibility);
        binding.gifComplete2.setVisibility(visibility);
        binding.gifComplete3.setVisibility(visibility);
    }
    private void completeVisibilityOn(){
        int level = Learning.getLevel();
        ArrayList<Learning.Vector2> completeParts = getCompleteParts();
        for (int i = 0; i< completeParts.size(); i++){
            if(completeParts.get(i).level == level){
                switch (completeParts.get(i).part){
                    case 0: binding.gifComplete1.setVisibility(View.VISIBLE); break;
                    case 1: binding.gifComplete2.setVisibility(View.VISIBLE); break;
                    case 2: binding.gifComplete3.setVisibility(View.VISIBLE); break;
                }
            }
        }
    }

    @Override
    public void onClick(View view) {
        int level = Learning.getLevel();
        switch (view.getId()){
            case R.id.part1Bt: setLevelPartToLearn(view, partId[level*3]); break;
            case R.id.part2Bt: setLevelPartToLearn(view, partId[level*3+1]); break;
            case R.id.part3Bt: setLevelPartToLearn(view, partId[level*3+2]); break;
        }
    }
    private void setLevelPartToLearn(View view,int part){
        Learning.currentPart = part;
        NavDirections action = PartsDirections.actionPartsToLearning();
        Navigation.findNavController(view).navigate(action);
    }
}

