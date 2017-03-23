package xixinxin.bawie.com.todaydemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import xixinxin.bawie.com.todaydemo.R;
import xixinxin.bawie.com.todaydemo.adapter.MyAdapter;
import xixinxin.bawie.com.todaydemo.bean.Home;
import xixinxin.bawie.com.todaydemo.utils.HomeList;

/**
 * 1: @author 席鑫鑫
 * 2: @time 2017/3/12 19:15
 */
public class NewFragment extends Fragment {

    private TabLayout tab;
    private ViewPager vp;
    private ArrayList<String> titles = new ArrayList<>();
    private ArrayList<Fragment> fragments = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.newfragment, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        tab = (TabLayout) view.findViewById(R.id.tab);
        vp = (ViewPager) view.findViewById(R.id.vp);
        ArrayList<Home> title = HomeList.getList();
        for (int i = 0; i < title.size(); i++) {
            titles.add(title.get(i).getName());
        }
        for (int i = 0; i < title.size(); i++) {
            TuijianFragment fragment = new TuijianFragment(title.get(i).getUrl());
            fragments.add(fragment);
        }
        //设置tablayout模式
        tab.setTabMode(TabLayout.MODE_SCROLLABLE);
        //添加tab标签
        for (String str : titles) {
            tab.addTab(tab.newTab().setText(str));
        }
        //创建适配器
        MyAdapter adapter = new MyAdapter(getActivity().getSupportFragmentManager(), titles, fragments);
        vp.setAdapter(adapter);
        //将tablayout与viewpager相关联
        tab.setupWithViewPager(vp);
        tab.setTabsFromPagerAdapter(adapter);
    }
}
