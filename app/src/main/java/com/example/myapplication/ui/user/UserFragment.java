package com.example.myapplication.ui.user;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import com.example.myapplication.*;
import com.example.myapplication.databinding.FragmentUserBinding;
import com.google.android.material.textfield.TextInputEditText;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import static com.example.myapplication.Learning.getCompleteParts;

public class UserFragment extends Fragment implements View.OnClickListener {

    private FragmentUserBinding binding;
    public static UserFragment userFragment;

    View root;
    Button applayBt;
    TextInputEditText changeName;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentUserBinding.inflate(inflater, container, false);
        root = binding.getRoot();
        userFragment = this;

        setUserData();


        Button authBt = binding.SignOutBt;
        ImageView avatar = binding.avatarUser;
        applayBt = binding.applayBt;
        changeName = binding.inputChangeName;
        inputNameVisible(View.GONE);

        authBt.setOnClickListener(this);
        avatar.setOnClickListener(this);
        applayBt.setOnClickListener(this);
        binding.nameUser.setOnClickListener(this);
        binding.language.setOnClickListener(this);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.SignOutBt:
                User.signOut(root); break;
            case R.id.avatarUser:
                SidebarMainActivity.mainActivity.pickImage();
                break;
            case R.id.nameUser:
                inputNameVisible(View.VISIBLE);
                changeName.setText(User.getName());
                break;
            case R.id.applayBt:
                String name = changeName.getText().toString();
                if(name.length()>4){
                    User.getAuthorizedUser().setName(name).updateInDB();
                    SidebarMainActivity.mainActivity.recreate();
                    inputNameVisible(View.GONE);
                }
                break;
            case R.id.language:
                if(User.getLanguage().equals("ii")){
                    User.getAuthorizedUser().setLanguage(true).updateInDB();
                }else {
                    User.getAuthorizedUser().setLanguage(false).updateInDB();
                }
                setLanguage();
                break;
        }
    }
    private void inputNameVisible(int visible){
        changeName.setVisibility(visible);
        applayBt.setVisibility(visible);
    }

    public static void setSideBarData(){
        setNameSideBar();
        setAvatarSideBar();
    }
    private void setUserData(){
        setAvatarUser();
        setNameUser();
        setProgressLevels();
        setLanguage();
        setPoints();
    }
    private void setAvatarUser(){
        if(User.getImage()!=null) {
            binding.avatarUser.setImageBitmap(User.getImage());
        }else binding.avatarUser.setImageResource(R.drawable.avatar);
    }
    private static void setAvatarSideBar(){
        if(User.getImage()!=null) {
            SidebarMainActivity.avatar_sideBar.setImageBitmap(User.getImage());
        }else SidebarMainActivity.avatar_sideBar.setImageResource(R.drawable.avatar);
    }
    private void setNameUser(){
        binding.nameUser.setText(User.getName());
    }
    private static void setNameSideBar(){
        SidebarMainActivity.name_sideBar.setText(User.getName());
    }
    private void setProgressLevels(){
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
        binding.progressBar1.setProgress(100*count1/3);
        binding.progressBar2.setProgress(100*count2/3);
        binding.progressBar3.setProgress(100*count3/3);
        binding.progressBar4.setProgress(100*count4/3);
    }

    private void setPoints(){
        binding.points.setText(String.valueOf(User.getPoints()));
    }
    private void setLanguage(){
        if(User.getLanguage().equals("ii")){
            binding.language.setImageResource(R.drawable.ii);
        }else {
            binding.language.setImageResource(R.drawable.ru);
        }

    }
}