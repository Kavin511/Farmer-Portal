package com.mart.ammanmart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
SharedPreferences sharedPreferences;
SharedPreferences.Editor editor;
private FirebaseAuth mAuth;
ProgressBar progressBar;
Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        sharedPreferences=getSharedPreferences("com.mart.ammanmart",MODE_PRIVATE);
        editor=getSharedPreferences("com.mart.ammanmart",MODE_PRIVATE).edit();
        editor.apply();
        AlphaAnimation inAnimation;
        inAnimation = new AlphaAnimation(0f, 1f);
        inAnimation.setDuration(200);
        progressBar=findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.INVISIBLE);
        final LinearLayout button_page=findViewById(R.id.button_page);
        intent=new Intent(getApplicationContext(),authentication_activity.class);
    }
    @Override
    public void onStart() {
        super.onStart();
    }
    public void traderClick(View view) {
        progressBar.setVisibility(View.VISIBLE);
        editor.putString("type","trader");
        editor.apply();
        if (mAuth.getCurrentUser()!=null)
        {
            final SharedPreferences.Editor editor=getSharedPreferences("com.mart.ammanmart",MODE_PRIVATE).edit();
            final FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
            String userid= Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid();
            final FirebaseFirestore db=FirebaseFirestore.getInstance();
            final String[] user_type = {""};
            db.collection("users").document(userid).collection("user_details").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (QueryDocumentSnapshot queryDocumentSnapshot: Objects.requireNonNull(task.getResult()))
                    {
                        user_type[0] = Objects.requireNonNull(queryDocumentSnapshot.getData().get("type")).toString();
                        editor.putString("mobile", Objects.requireNonNull(queryDocumentSnapshot.getData().get("mobile")).toString());
                        editor.apply();
                        if (user_type[0].equals("trader"))
                        {
                            progressBar.setVisibility(View.INVISIBLE);
                            startActivity(new Intent(getApplicationContext(),Trader.class));
                        }
                        else
                        {
                            progressBar.setVisibility(View.INVISIBLE);
                            startActivity(intent);
                        }
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressBar.setVisibility(View.INVISIBLE);
                    startActivity(intent);
                }
            });
        }
        else
            startActivity(intent);
    }
    public void sellerClick(View view) {
        progressBar.setVisibility(View.VISIBLE);
        editor.putString("type","seller");
        editor.apply();
        if (mAuth.getCurrentUser()!=null)
        {
            final SharedPreferences.Editor editor=getSharedPreferences("com.mart.ammanmart",MODE_PRIVATE).edit();
            final FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
            String userid= Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid();
            final FirebaseFirestore db=FirebaseFirestore.getInstance();
            final String[] user_type = {""};
            db.collection("users").document(userid).collection("user_details").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (QueryDocumentSnapshot queryDocumentSnapshot: Objects.requireNonNull(task.getResult()))
                    {
                        user_type[0] = Objects.requireNonNull(queryDocumentSnapshot.getData().get("type")).toString();
                        editor.putString("mobile", Objects.requireNonNull(queryDocumentSnapshot.getData().get("mobile")).toString());
                        editor.apply();
                        if (user_type[0].equals("seller"))
                        {
                            progressBar.setVisibility(View.INVISIBLE);
                            startActivity(new Intent(getApplicationContext(),Seller_.class));
                        }
                        else
                        {
                            progressBar.setVisibility(View.INVISIBLE);
                            startActivity(intent);
                        }
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressBar.setVisibility(View.INVISIBLE);
                    startActivity(intent);
                }
            });
        }
        else
        {
            progressBar.setVisibility(View.INVISIBLE);
            startActivity(intent);
        }
    }
    public void buyerClick(View view) {
        progressBar.setVisibility(View.VISIBLE);
        editor.putString("type","buyer");
        editor.apply();
        if (mAuth.getCurrentUser()!=null)
        {
            final SharedPreferences.Editor editor=getSharedPreferences("com.mart.ammanmart",MODE_PRIVATE).edit();
            final FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
            String userid= Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid();
            final FirebaseFirestore db=FirebaseFirestore.getInstance();
            final String[] user_type = {""};
            db.collection("users").document(userid).collection("user_details").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (QueryDocumentSnapshot queryDocumentSnapshot: Objects.requireNonNull(task.getResult()))
                    {
                        user_type[0] = Objects.requireNonNull(queryDocumentSnapshot.getData().get("type")).toString();
                        editor.putString("mobile", Objects.requireNonNull(queryDocumentSnapshot.getData().get("mobile")).toString());
                        editor.apply();
                        if (user_type[0].equals("buyer"))
                        {
                            progressBar.setVisibility(View.INVISIBLE);
                            startActivity(new Intent(getApplicationContext(),Buyer.class));
                        }
                        else
                        {
                            progressBar.setVisibility(View.INVISIBLE);
                            startActivity(intent);
                        }
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressBar.setVisibility(View.INVISIBLE);
                    startActivity(intent);
                }
            });
        }
        else
        {
            progressBar.setVisibility(View.INVISIBLE);
            startActivity(intent);
        }
    }
}