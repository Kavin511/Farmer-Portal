package com.mart.ammanmart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Seller_ extends AppCompatActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item_s, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_signout)
        {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(this,MainActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_);
        final EditText pt_name ,pt_category ,pt_boxes,pt_location;
        final CheckBox vehicle;
        MaterialButton materialButton;
        final SharedPreferences sharedPreferences= Objects.requireNonNull(getApplicationContext()).getSharedPreferences("com.mart.ammanmart",MODE_PRIVATE);
        pt_name=findViewById(R.id.pt_name);
        pt_category=findViewById(R.id.pt_category);
        pt_boxes=findViewById(R.id.pt_boxes);
        pt_location=findViewById(R.id.pt_location);
        vehicle=findViewById(R.id.vehicle);
        materialButton=findViewById(R.id.btn_add);
        MaterialToolbar mTopToolbar =findViewById(R.id.my_toolbar);
        setSupportActionBar(mTopToolbar);
        TabLayout tabLayout=findViewById(R.id.tab_layout);
        final ViewPager viewPager=findViewById(R.id.view_pager);
        tabLayout.addTab(tabLayout.newTab().setText("Request"));
            tabLayout.addTab(tabLayout.newTab().setText("History"));
        final SellerAdapter loginAdapter= new SellerAdapter(getSupportFragmentManager(), getApplicationContext(), tabLayout.getTabCount());
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.setAdapter(loginAdapter);
//        materialButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int count=0;
//                if(pt_name.getText().toString().length()!=0)
//                {
//                    count++;
//                }
//                if(pt_category.getText().toString().length()!=0)
//                {
//                    count++;
//                }
//                if(pt_boxes.getText().toString().length()!=0)
//                {
//                    count++;
//                }
//                if(pt_location.getText().toString().length()!=0)
//                {
//                    count++;
//                }
//                if (count==4)
//                {
//                    FirebaseFirestore db = FirebaseFirestore.getInstance();
//                    Map<String, Object> user = new HashMap<>();
//                    user.put("type","sell");
//                    user.put("pt_name", pt_name.getText().toString().trim());
//                    user.put("pt_box", pt_boxes.getText().toString().trim());
//                    user.put("pt_category", pt_category.getText().toString().trim());
//                    user.put("pt_location",pt_location.getText().toString().trim());
//                    user.put("date", Calendar.getInstance().getTimeInMillis());
//                    user.put("mobile",sharedPreferences.getString("mobile","N/A"));
//                    user.put("bill","no");
//                    user.put("vehicle",vehicle.isChecked()?"Vehicle needed":"No vehicle needed");
//                    db.collection("request").add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                        @Override
//                        public void onSuccess(DocumentReference documentReference) {
//                            pt_name.setText("");
//                            pt_boxes.setText("");
//                            pt_category.setText("");
//                            pt_location.setText("");
//                            Toast.makeText(getApplicationContext(), "Request posted", Toast.LENGTH_SHORT).show();
//                        }
//                    }).addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e)
//                        {
//
//                            Toast.makeText(getApplicationContext(), "Error Occurred ,try again later", Toast.LENGTH_LONG).show();
//                        }
//                    });
//                }
//            }
//        });
    }


}