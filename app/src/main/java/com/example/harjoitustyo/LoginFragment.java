package com.example.harjoitustyo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    EditText pwd;
    EditText uName;
    Button loginButton;
    UserManager manager;
    TextView infoText;
    TextView signUpText;

    public LoginFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            manager = (UserManager) getArguments().getSerializable("manager");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        pwd = v.findViewById(R.id.edit_login_password);
        uName = v.findViewById(R.id.edit_login_uName);
        infoText = v.findViewById(R.id.infoTextLogin);
        signUpText = v.findViewById(R.id.text_signUp);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == v.findViewById(R.id.button_login)) {
                    System.out.println("Button pressed");
                    infoText.setText(manager.loginCheck(uName.getText().toString(), pwd.getText().toString()));
                    Intent intent = new Intent(getActivity(), ProfileActivity.class);
                } else if (view == v.findViewById(R.id.text_signUp)) {
                    Fragment fragment = new SignUpFragment();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("manager", manager);
                    fragment.setArguments(bundle);
                    FragmentManager fManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction transaction = fManager.beginTransaction();
                    transaction.replace(R.id.fragmentContainer, fragment);
                    transaction.commit();
                }
            }
        };
        signUpText.setOnClickListener(listener);
        loginButton = v.findViewById(R.id.button_login);
        loginButton.setOnClickListener(listener);
        return v;
    }



    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        };
    }
}