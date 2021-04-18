package com.mart.ammanmart;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Objects;

public class Trader_fragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.trader_layout, container, false);
        final FirebaseFirestore db=FirebaseFirestore.getInstance();
        LinearLayout linearLayout=v.findViewById(R.id.linearlayout);
        db.collection("request").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful())
                {
                    for(QueryDocumentSnapshot queryDocumentSnapshot: Objects.requireNonNull(task.getResult()))
                    {
                        String type= Objects.requireNonNull(queryDocumentSnapshot.getData().get("type")).toString();
                        String pt_name= Objects.requireNonNull(queryDocumentSnapshot.getData().get("pt_name")).toString();
                        String pt_box= Objects.requireNonNull(queryDocumentSnapshot.getData().get("pt_box")).toString();
                        String pt_category=queryDocumentSnapshot.getData().get("pt_category").toString();
                        String pt_location= Objects.requireNonNull(queryDocumentSnapshot.getData().get("pt_location")).toString();
                        String date= Objects.requireNonNull(queryDocumentSnapshot.getData().get("date")).toString();
                        String mobile= Objects.requireNonNull(queryDocumentSnapshot.getData().get("mobile")).toString();

                        View view=getLayoutInflater().inflate(R.layout.item_view,null);
                        TextView type_,name_,boxes_,category_,mobile_,location_,date_,vehicle_;
//                        type_=view.findViewById(R.id.type);
                        name_=view.findViewById(R.id.name);
                        boxes_=view.findViewById(R.id.boxes);
                        category_=view.findViewById(R.id.category);
                        mobile_=view.findViewById(R.id.mobile);
                        location_=view.findViewById(R.id.location);
//                        date_=view.findViewById(R.id.date);
                        vehicle_=view.findViewById(R.id.vehicle);
//                        type_.setText(type);
                                name_.setText(pt_name);
                        boxes_.setText(pt_box);
                                category_.setText(pt_category);
                        mobile_.setText(mobile);
                                location_.setText(pt_location);
//                        date_.setText(date);
                        if (!type.equals("sell")){
                            vehicle_.setVisibility(View.GONE);
                            view.setBackgroundColor(Color.parseColor("#FFF"));
                        }
                        else
                        {
                            String vehicle= Objects.requireNonNull(queryDocumentSnapshot.getData().get("vehicle")).toString();
                            vehicle_.setText(vehicle);
                            vehicle_.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Error occurred ,Try again later", Toast.LENGTH_LONG).show();
            }
        });
        return v;
    }
}
