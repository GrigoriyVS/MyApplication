package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import com.example.myapplication.databinding.ComletePartBinding;
import com.example.myapplication.ui.levels.Levels;
import com.example.myapplication.ui.user.UserFragment;
import com.google.android.material.textfield.TextInputEditText;

public class CompletePart extends Fragment implements View.OnClickListener {

    static ComletePartBinding binding;
    View root;
    public static int points;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = ComletePartBinding.inflate(inflater, container, false);
        root = binding.getRoot();

        binding.poinstAdd.setText(String.valueOf(points));
        binding.backToLearn.setOnClickListener(this);

        binding.errors.setText(String.valueOf(Learning.errors));

        Learning.errors = 0;

        return root;
    }



    public static void load(View view){
        NavDirections action = LearningDirections.actionLearningToComplete();
        Navigation.findNavController(view).navigate(action);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.backToLearn:
                SidebarMainActivity.mainActivity.recreate();
                Levels.loadFromComplete(this.root);
                break;
        }
    }
}