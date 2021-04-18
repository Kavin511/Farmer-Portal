package com.mart.ammanmart;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link signin#newInstance} factory method to
 * create an instance of this fragment.
 */
public class signin extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private FirebaseAuth mAuth;
    EditText name,mail,pass,mobile;

    MaterialButton signin_btn;

    public signin() {
    }

    public static signin newInstance(String param1, String param2) {
        signin fragment = new signin();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth=FirebaseAuth.getInstance();





    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        View v= inflater.inflate(R.layout.fragment_signin, container, false);
        name=v.findViewById(R.id.name);
        mail=v.findViewById(R.id.mail);
        pass=v.findViewById(R.id.pass);
        mobile=v.findViewById(R.id.mobile);
        signin_btn=v.findViewById(R.id.signin_btn);
        signin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name_str=name.getText().toString().trim();
                String mail_str=mail.getText().toString().trim();
                final String pass_str=pass.getText().toString().trim();
                int count=4;
                final String mobile_str=mobile.getText().toString().trim();
                if (TextUtils.isEmpty(name_str)) {
                    name.setError("Username should not be empty");
                    count--;
                }
                if (pass_str.length()<5||TextUtils.isEmpty(pass_str))
                {
                    pass.setError("Password should be greater than 5 characters");
                    count--;
                }
                if(mobile_str.length()!=10)
                {
                    mobile.setError("Enter Valid mobile number");
                    count--;
                }
                String Expn = "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        +"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        +"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        +"([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";
                boolean flag=false;
                if(mail_str.matches(Expn)&&!TextUtils.isEmpty(mail_str)&&mail_str.length()!=0)
                {
                    flag=true;
                }
                else
                {
                    mail.setError("Enter valid mail");
                    count--;
                }

                final Snackbar snackbar = Snackbar
                        .make(Objects.requireNonNull(getView()), "Creating Account ",Snackbar.LENGTH_INDEFINITE);
                if(flag &&count==4) {
                    snackbar.show();
                    mAuth.createUserWithEmailAndPassword(mail_str, pass_str).addOnCompleteListener(Objects.requireNonNull(getActivity()), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            SharedPreferences sharedPreferences= Objects.requireNonNull(getContext()).getSharedPreferences("com.mart.ammanmart", Context.MODE_PRIVATE);
                            if (task.isSuccessful()) {
                                String userid = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
                                FirebaseFirestore db = FirebaseFirestore.getInstance();
                                Map<String, Object> user = new HashMap<>();
                                user.put("name", name_str);
                                user.put("pass", pass_str);
                                user.put("mobile", mobile_str);
                                user.put("type",sharedPreferences.getString("type","fake"));
                                db.collection("users").document(userid).collection("user_details").add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                    @Override
                                    public void onSuccess(DocumentReference documentReference) {
                                        Intent startactivity = new Intent(getContext(), DataActivity.class);
                                        startActivity(startactivity);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        snackbar.dismiss();
                                        Toast.makeText(getContext(), "Error Occured ,try again later", Toast.LENGTH_LONG).show();
                                    }
                                });
                            }
                            else
                            {
                                snackbar.dismiss();
                                Toast.makeText(getContext(),"Mail id already registered !",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(getActivity(),"Enter valid credentials ! ",Toast.LENGTH_LONG).show();
                    snackbar.dismiss();
                }
            }
        });
        return v;
    }
}