package xixinxin.bawie.com.todaydemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import xixinxin.bawie.com.todaydemo.R;

/**
 * 1: @author 席鑫鑫
 * 2: @time 2017/3/12 15:55
 */
public class OneFragment extends Fragment implements View.OnClickListener {

    private ImageView imageview2;
    private SlidingMenu slidingMenu;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.onefragment, null);
        imageview2 = (ImageView) view.findViewById(R.id.imageView2);
        imageview2.setOnClickListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //创建SlidingMenu
        slidingMenu = new SlidingMenu(getActivity());
        //设置侧滑方向
        slidingMenu.setMode(SlidingMenu.LEFT);
        //设置侧滑宽度
        slidingMenu.setBehindOffset(80);
        //设置让侧滑依附于activity之上
        slidingMenu.attachToActivity(getActivity(), SlidingMenu.SLIDING_CONTENT);
        //设置侧滑布局
        slidingMenu.setMenu(R.layout.menu);
        initFragment();
    }

    @Override
    public void onClick(View v) {
    slidingMenu.toggle();
    }

    private void initFragment() {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.menu, new MenuFragment());
        transaction.commit();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fram,new NewFragment()).commit();
    }
}
