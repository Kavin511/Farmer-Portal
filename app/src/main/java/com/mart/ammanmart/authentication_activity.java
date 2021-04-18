package com.mart.ammanmart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class authentication_activity extends AppCompatActivity {
TabLayout tabLayout;
ViewPager viewPager;
//private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication_activity);
//        mAuth = FirebaseAuth.getInstance();
        tabLayout=findViewById(R.id.tabLayout);
        viewPager=findViewById(R.id.viewPager);
        SharedPreferences sharedPreferences=getSharedPreferences("com.mart.ammanmart",MODE_PRIVATE);
        tabLayout.addTab(tabLayout.newTab().setText("Login"));
        if(!Objects.equals(sharedPreferences.getString("type", "null"), "trader"))
        tabLayout.addTab(tabLayout.newTab().setText("Sign up"));
        final LoginAdapter loginAdapter=new LoginAdapter(getSupportFragmentManager(),getApplicationContext(),tabLayout.getTabCount());
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
    }
}
