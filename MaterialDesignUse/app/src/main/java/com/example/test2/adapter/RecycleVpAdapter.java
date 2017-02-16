package com.example.test2.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaolin on 2017/2/14.
 */

public class RecycleVpAdapter extends FragmentPagerAdapter {
    List<Fragment> list;
    FragmentManager fm;
   public  RecycleVpAdapter(FragmentManager fm, List<Fragment> list){
       super(fm);
       this.list=list;
       this.fm=fm;
   }
    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
