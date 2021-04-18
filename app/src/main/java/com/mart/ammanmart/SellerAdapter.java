package com.mart.ammanmart;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class SellerAdapter extends FragmentPagerAdapter {
    Context context;
    int totalTabs;
    seller requests_page=new seller();
    history billed=new history();
    Acoount acoount=new Acoount();
    public SellerAdapter(FragmentManager supportFragmentManager, Context applicationContext, int tabCount) {
        super(supportFragmentManager);
        this.context=applicationContext;
        this.totalTabs=tabCount;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new seller();
            case 1:
                return new history();
            case 2:
                return acoount;
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return totalTabs;
    }
}
