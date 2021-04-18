package com.mart.ammanmart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DataActivity extends AppCompatActivity {

FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_data);
        mAuth = FirebaseAuth.getInstance();
        final SharedPreferences.Editor editor=getSharedPreferences("com.mart.ammanmart",MODE_PRIVATE).edit();
        final FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        String userid= Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid();
        final FirebaseFirestore db=FirebaseFirestore.getInstance();
        final String[] user_type = {""};
        Log.d("admin", "onComplete: "+user_type[0]);
        db.collection("users").document(userid).collection("user_details").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful())
                {
                    for(QueryDocumentSnapshot documentSnapshot: Objects.requireNonNull(task.getResult())) {
                        user_type[0] = Objects.requireNonNull(documentSnapshot.getData().get("type")).toString();
                        Log.d("admin", "onComplete: " + user_type[0]);
                        switch (user_type[0]) {
                            case "buyer":
                                editor.putString("mobile", Objects.requireNonNull(documentSnapshot.getData().get("mobile")).toString());
                                editor.apply();
                                startActivity(new Intent(getApplicationContext(),Buyer.class));
                                finish();
                                break;
                            case "seller":
                                editor.putString("mobile", Objects.requireNonNull(documentSnapshot.getData().get("mobile")).toString());
                                editor.apply();
                                startActivity(new Intent(getApplicationContext(),Seller_.class));
                                finish();
                                break;
                            case "trader":
                                editor.putString("mobile", Objects.requireNonNull(documentSnapshot.getData().get("mobile")).toString());
                                editor.apply();
                                startActivity(new Intent(getApplicationContext(),Trader.class));
                                finish();
                                break;
                        }

                    }
                }
            }
        });
    }
}