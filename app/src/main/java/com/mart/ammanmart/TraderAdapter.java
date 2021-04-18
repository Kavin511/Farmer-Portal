package com.mart.ammanmart;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TraderAdapter extends FragmentPagerAdapter {
        Context context;
        int totalTabs;
    requests requests=new requests();
    billed billed=new billed();
    seller seller=new seller();
    public TraderAdapter(FragmentManager fragmentManager, Context context, int totalTabs)
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
                return requests;
                    case 1:
                return billed;
                    case 3:
                        return seller;
        }
        return requests;
    }
    @Override
    public int getCount() {
        return totalTabs;
    }
}
