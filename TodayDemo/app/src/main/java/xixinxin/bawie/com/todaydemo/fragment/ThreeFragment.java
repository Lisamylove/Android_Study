package xixinxin.bawie.com.todaydemo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import xixinxin.bawie.com.todaydemo.R;
import xixinxin.bawie.com.todaydemo.activity.HotActivity;

/**
 * 1: @author 席鑫鑫
 * 2: @time 2017/3/12 15:55
 */
public class ThreeFragment extends Fragment implements View.OnClickListener {

    private LinearLayout liear;
    private ImageView c;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.threefragment, null);
        liear = (LinearLayout) view.findViewById(R.id.liear);
        c = (ImageView) view.findViewById(R.id.c);
        c.setOnClickListener(this);
        liear.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        Intent it=new Intent(getActivity(), HotActivity.class);
        startActivity(it);
    }
}
