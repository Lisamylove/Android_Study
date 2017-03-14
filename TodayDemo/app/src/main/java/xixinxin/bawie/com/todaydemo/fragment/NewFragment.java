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
        titles.add("推荐");
        titles.add("热点");
        titles.add("北京");
        titles.add("社会");
        titles.add("娱乐");
        titles.add("问答");
        titles.add("图片");
        titles.add("科技");
        titles.add("汽车");
        titles.add("体育");
        TuijianFragment tuijian = new TuijianFragment();
        fragments.add(tuijian);
        RedianFragment redian = new RedianFragment();
        fragments.add(redian);
        BeijingFragment beijing = new BeijingFragment();
        fragments.add(beijing);
        ShehuiFragment shehui = new ShehuiFragment();
        fragments.add(shehui);
        YuleFragment yule = new YuleFragment();
        fragments.add(yule);
        WendaFragment wenda = new WendaFragment();
        fragments.add(wenda);
        TupianFragment tupian = new TupianFragment();
        fragments.add(tupian);
        KejiFragment keji = new KejiFragment();
        fragments.add(keji);
        QicheFragment qiche = new QicheFragment();
        fragments.add(qiche);
        TiyuFragment tiiyu = new TiyuFragment();
        fragments.add(tiiyu);
        //设置tablayout模式
        tab.setTabMode(TabLayout.MODE_SCROLLABLE);
        //添加tab标签
        tab.addTab(tab.newTab().setText(titles.get(0)));
        tab.addTab(tab.newTab().setText(titles.get(1)));
        tab.addTab(tab.newTab().setText(titles.get(2)));
        tab.addTab(tab.newTab().setText(titles.get(3)));
        tab.addTab(tab.newTab().setText(titles.get(4)));
        tab.addTab(tab.newTab().setText(titles.get(5)));
        tab.addTab(tab.newTab().setText(titles.get(6)));
        tab.addTab(tab.newTab().setText(titles.get(7)));
        tab.addTab(tab.newTab().setText(titles.get(8)));
        tab.addTab(tab.newTab().setText(titles.get(9)));
        //创建适配器
        MyAdapter adapter=new MyAdapter(getActivity().getSupportFragmentManager(),titles,fragments);
        vp.setAdapter(adapter);
        //将tablayout与viewpager相关联
        tab.setupWithViewPager(vp);
        tab.setTabsFromPagerAdapter(adapter);
    }
}
