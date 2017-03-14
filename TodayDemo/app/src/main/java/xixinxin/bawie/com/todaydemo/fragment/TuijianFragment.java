package xixinxin.bawie.com.todaydemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import xixinxin.bawie.com.todaydemo.R;

/**
 * 1: @author 席鑫鑫
 * 2: @time 2017/3/14 10:35
 */
public class TuijianFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.titlesfragment, null);
        return view;
    }
}
