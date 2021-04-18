package com.mart.ammanmart;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
//import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link login_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class login_fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    FirebaseAuth mAuth;
    EditText mail,pass;
    TextView forgot_password;
    MaterialButton materialButton;
    public login_fragment() {
        // Required empty public constructor
    }
    public static login_fragment newInstance(String param1, String param2) {
        login_fragment fragment = new login_fragment();
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
            mAuth=FirebaseAuth.getInstance();
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_login_fragment, container, false);
        mail=v.findViewById(R.id.mail);
        pass=v.findViewById(R.id.pass);
        forgot_password=v.findViewById(R.id.forgot_password);
        mAuth=FirebaseAuth.getInstance();
        materialButton=v.findViewById(R.id.login_btn);
        materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail_str=mail.getText().toString().trim();
                String pass_str=pass.getText().toString().trim();
                String forgot_password_str=forgot_password.getText().toString().trim();
                String Expn = "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        +"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        +"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        +"([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";
                boolean flag=false;
                int count=2;
                if(mail_str.matches(Expn)&&!TextUtils.isEmpty(mail_str)&&mail_str.length()!=0)
                {
                    flag=true;
                }
                else
                {
                    mail.setError("Enter valid mail");
                    count--;
                }
                if (pass_str.length()==0)
                {
                    pass.setError("Enter valid password");
                    count--;
                }
                final Snackbar snackbar = Snackbar
                        .make(Objects.requireNonNull(getView()), "Processing Login request", Snackbar.LENGTH_LONG);
                if (count==2)
                {
                    snackbar.show();
                    mAuth.signInWithEmailAndPassword(mail_str, pass_str).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                snackbar.dismiss();
                                Intent startactivity=new Intent(getActivity(),DataActivity.class);
                                startActivity(startactivity);
                            } else {
                                snackbar.setText("Process failed");
                            }
                        }
                    });
                }

            }
        });
        return v;
    }

}