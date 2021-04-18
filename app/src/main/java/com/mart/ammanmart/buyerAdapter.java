package com.mart.ammanmart;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class buyerAdapter extends FragmentPagerAdapter {
    Context context;
    int totalTabs;
    buyer_fragment buyer_fragment=new buyer_fragment();
    history billed=new history();
    public buyerAdapter(FragmentManager supportFragmentManager, Context applicationContext, int tabCount) {
        super(supportFragmentManager);
        this.context=applicationContext;
        this.totalTabs=tabCount;
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new buyer_fragment();
            case 1:
                return new history();
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return totalTabs;
    }
}
