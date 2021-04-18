package com.mart.ammanmart;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link seller#newInstance} factory method to
 * create an instance of this fragment.
 */
public class seller extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public seller() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment seller.
     */
    // TODO: Rename and change types and number of parameters
    public static seller newInstance(String param1, String param2) {
        seller fragment = new seller();
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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.seller_layout, container, false);
        final EditText pt_name ,pt_category ,pt_boxes,pt_location;
        final CheckBox vehicle;
        MaterialButton materialButton;
        final SharedPreferences sharedPreferences= Objects.requireNonNull(getContext()).getSharedPreferences("com.mart.ammanmart",MODE_PRIVATE);
        pt_name=v.findViewById(R.id.pt_name);
        pt_category=v.findViewById(R.id.pt_category);
        pt_boxes=v.findViewById(R.id.pt_boxes);
        pt_location=v.findViewById(R.id.pt_location);
        vehicle=v.findViewById(R.id.vehicle);
        materialButton=v.findViewById(R.id.btn_add);
        MaterialToolbar mTopToolbar =v.findViewById(R.id.my_toolbar);
        Log.d("fauth", "onClick: "+ FirebaseAuth.getInstance().getUid());
                materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count=0;
                if(pt_name.getText().toString().length()!=0)
                {
                    count++;
                }
                if(pt_category.getText().toString().length()!=0)
                {
                    count++;
                }
                if(pt_boxes.getText().toString().length()!=0)
                {
                    count++;
                }
                if(pt_location.getText().toString().length()!=0)
                {
                    count++;
                }
                if (count==4)
                {
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    Map<String, Object> user = new HashMap<>();
                    user.put("type","sell");
                    user.put("pt_name", pt_name.getText().toString().trim());
                    user.put("pt_box", pt_boxes.getText().toString().trim());
                    user.put("pt_category", pt_category.getText().toString().trim());
                    user.put("pt_location",pt_location.getText().toString().trim());
                    user.put("date", Calendar.getInstance().getTimeInMillis());
                    user.put("userid",FirebaseAuth.getInstance().getUid());
                    user.put("mobile",sharedPreferences.getString("mobile","N/A"));
                    user.put("bill","no");
                    user.put("vehicle",vehicle.isChecked()?"Vehicle needed":"No vehicle needed");
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
                        public void onFailure(@NonNull Exception e)
                        {

                            Toast.makeText(getContext(), "Error Occurred ,try again later", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
        return v;
    }
}