package com.example.shoptm.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoptm.R;
import com.example.shoptm.activity.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignInFragment extends Fragment {
    FrameLayout parentFrameLayout;
    TextView tvSignUp, tvForgotPass;
    private EditText edtEmail, edtPassword;
    private Button btnSignIn;
    private ImageView imvClose;
    private ProgressBar progressBar;
    FirebaseAuth mAuth;


    public SignInFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        parentFrameLayout = getActivity().findViewById(R.id.register_frame_layout);
        mAuth = FirebaseAuth.getInstance();
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        tvSignUp = view.findViewById(R.id.tv_signUp);
        edtEmail = view.findViewById(R.id.edt_Email);
        edtPassword = view.findViewById(R.id.edt_Password);
        btnSignIn = view.findViewById(R.id.btn_signIn);
        tvForgotPass = view.findViewById(R.id.tv_forgotPass);
        imvClose = view.findViewById(R.id.imageView2);
        progressBar = view.findViewById(R.id.progressbar_signIn);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new SignUpFragment());
            }
        });
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputSignIn();
            }
        });
        imvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    private void inputSignIn() {
        String email = edtEmail.getText().toString();
        String pass = edtPassword.getText().toString();

        if (email.isEmpty() || pass.isEmpty()) {
            Toast.makeText(getActivity(), "Không được để trống !", Toast.LENGTH_SHORT).show();
        } else if (pass.length() < 6) {
            Toast.makeText(getActivity(), "Mật khẩu phải nhiều hơn 6 kí tự !", Toast.LENGTH_SHORT).show();
        } else {
            progressBar.setVisibility(View.VISIBLE);
            mAuth.signInWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                progressBar.setVisibility(View.INVISIBLE);
                                Intent intent = new Intent(getActivity(), MainActivity.class);
                                startActivity(intent);
                                Toast.makeText(getActivity(), "Đăng nhập thành công !", Toast.LENGTH_SHORT).show();
                            } else {
                                progressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(getActivity(), "Đăng nhập thất bại !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(parentFrameLayout.getId(), fragment);
        fragmentTransaction.commit();

    }

    @Override
    public void onResume() {
        super.onResume();
        progressBar.setVisibility(View.INVISIBLE);
    }
}
