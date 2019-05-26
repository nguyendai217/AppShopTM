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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {
    private FrameLayout parentFrameLayout;
    private TextView tvSignIn;
    private EditText edtEmail, edtFullname, edtPassword, edtConfirmpassword;
    private Button btnSignUp;
    private ImageView imvClose;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    FirebaseFirestore firebaseFirestore;

    public SignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        parentFrameLayout = getActivity().findViewById(R.id.register_frame_layout);
        tvSignIn = view.findViewById(R.id.tv_signIn);
        edtEmail = view.findViewById(R.id.Email);
        edtFullname = view.findViewById(R.id.Name);
        edtPassword = view.findViewById(R.id.Password);
        edtConfirmpassword = view.findViewById(R.id.ConfirmPassword);
        btnSignUp = view.findViewById(R.id.btn_signUp);
        imvClose = view.findViewById(R.id.imv_close);
        progressBar = view.findViewById(R.id.progressbar_signUp);
        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new SignInFragment());
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkInputs();
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

    private void checkInputs() {
        String email = edtEmail.getText().toString();
        final String name = edtFullname.getText().toString();
        String pass = edtPassword.getText().toString();
        String confirmpass = edtConfirmpassword.getText().toString();

        if (email.isEmpty() || pass.isEmpty() || name.isEmpty() || confirmpass.isEmpty()) {
            Toast.makeText(getActivity(), "Không được để trống !", Toast.LENGTH_SHORT).show();
        } else if (pass.length() < 6) {
            Toast.makeText(getActivity(), "Mật khẩu phải nhiều hơn 6 kí tự !", Toast.LENGTH_SHORT).show();
        } else if (!confirmpass.equals(pass)) {
            Toast.makeText(getActivity(), "Mật khẩu không trùng khớp !", Toast.LENGTH_SHORT).show();
        } else {
            progressBar.setVisibility(View.VISIBLE);
            mAuth.createUserWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                progressBar.setVisibility(View.INVISIBLE);
                                Map<Object, String> userdata = new HashMap<>();
                                userdata.put("fullname", name);
                                firebaseFirestore.collection("USERS")
                                        .add(userdata).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentReference> task) {
                                        if (task.isSuccessful()) {
                                            Intent intent = new Intent(getActivity(), MainActivity.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(intent);
                                            Toast.makeText(getActivity(), "Đăng kí thành công ..", Toast.LENGTH_SHORT).show();
                                            getActivity().finish();
                                        } else {
                                            progressBar.setVisibility(View.INVISIBLE);
                                            Toast.makeText(getActivity(), "Đăng kí thất bại.." + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            } else {
                                progressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(getActivity(), "Đăng kí thất bại.." + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
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
