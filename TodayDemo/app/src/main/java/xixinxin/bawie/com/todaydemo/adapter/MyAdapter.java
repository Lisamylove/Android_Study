package xixinxin.bawie.com.todaydemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * 1: @author 席鑫鑫
 * 2: @time 2017/3/14 10:56
 */
public class MyAdapter extends FragmentPagerAdapter{
    private ArrayList<String> titl;
    private ArrayList<Fragment> fragments;

    public MyAdapter(FragmentManager fm, ArrayList<String> titles, ArrayList<Fragment> fragments) {
        super(fm);
        this.titl = titles;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titl.get(position);
    }
}
