package com.example.myapplication.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.myapplication.MainActivity;
import com.example.myapplication.databinding.FragmentLearningBinding;

public class LearningFragment extends Fragment {

    private FragmentLearningBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentLearningBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        ImageButton b1 = binding.levelBt1;
        ImageButton b2 = binding.levelBt2;
        ImageButton b3 = binding.levelBt3;
        b1.setOnClickListener(MainActivity.mainActivity);
        b2.setOnClickListener(MainActivity.mainActivity);
        b3.setOnClickListener(MainActivity.mainActivity);
         return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}