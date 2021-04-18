package com.mart.ammanmart;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;

public class buyer_fragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.buyer_layout, container, false);
                final EditText pt_name = v.findViewById(R.id.pt_name);

        final EditText pt_category = v.findViewById(R.id.pt_category);
        final EditText pt_boxes = v.findViewById(R.id.pt_boxes);
        final EditText pt_location = v.findViewById(R.id.pt_location);
        MaterialButton post_req = v.findViewById(R.id.post_req);
        Log.d("fauth", "onClick: "+FirebaseAuth.getInstance().getUid());
        final SharedPreferences sharedPreferences= Objects.requireNonNull(getContext()).getSharedPreferences("com.mart.ammanmart",MODE_PRIVATE);
        post_req.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = 0;
                if (pt_name.getText().toString().length() == 0) {
                    pt_name.setError("Product name required");
                    count--;
                }
                if (pt_boxes.getText().toString().length() == 0) {
                    pt_boxes.setError("Number of boxes required");
                    count--;
                }
                if (pt_location.getText().toString().length()==0)
                {
                    pt_location.setError("Enter valid location");
                    count--;
                }
                if (pt_category.getText().toString().length()==0)
                {
                    pt_category.setError("Enter category");
                    count--;
                }
                if (count == 0) {
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    Map<String, Object> user = new HashMap<>();
                    user.put("type","buy");
                    user.put("pt_name", pt_name.getText().toString().trim());
                    user.put("pt_box", pt_boxes.getText().toString().trim());
                    user.put("pt_category", pt_category.getText().toString().trim());
                    user.put("pt_location",pt_location.getText().toString().trim());
                    user.put("date", Calendar.getInstance().getTimeInMillis());
                    user.put("mobile",sharedPreferences.getString("mobile","N/A"));
                    user.put("bill","no");
                    user.put("userid",FirebaseAuth.getInstance().getUid());
                    db.collection("request").add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            pt_name.setText("");
                            pt_boxes.setText("");
                            pt_category.setText("");
                            pt_location.setText("");
                            Toast.makeText(getContext(), "Request posted", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getContext(), "Error Occurred ,try again later", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
        return v;
    }
}
