package com.example.myapplication;

import android.os.Bundle;
import android.widget.EditText;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import com.example.myapplication.ui.levels.Levels;
import com.example.myapplication.ui.levels.LevelsDirections;
import com.example.myapplication.ui.user.UserFragment;
import com.example.myapplication.ui.user.UserFragmentDirections;

public class SignIn extends Fragment implements View.OnClickListener {

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.sign_in, container, false);


        view.findViewById(R.id.authUserBt).setOnClickListener(this);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        User.signIn(view);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.authUserBt:
                String phone = ((EditText)this.view.findViewById(R.id.authPhone)).getText().toString();
                User.signIn(view, phone);
                break;
        }
    }

    public static void load(View view){
        NavDirections action = UserFragmentDirections.actionUserToSignIn();
        Navigation.findNavController(view).navigate(action);
    }
}