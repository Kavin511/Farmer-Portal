package com.mart.ammanmart;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class LoginAdapter extends FragmentPagerAdapter {
    Context context;
    int totalTabs;
    login_fragment login_fragment=new login_fragment();
    signin signin=new signin();
public LoginAdapter(FragmentManager fragmentManager,Context context,int totalTabs)
{
    super(fragmentManager);
    this.context=context;
    this.totalTabs=totalTabs;
}
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return login_fragment;
            case 1:
                return signin;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
