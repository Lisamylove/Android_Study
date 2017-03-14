package xixinxin.bawie.com.todaydemo.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

import xixinxin.bawie.com.todaydemo.R;
import xixinxin.bawie.com.todaydemo.fragment.OneFragment;
import xixinxin.bawie.com.todaydemo.fragment.ThreeFragment;
import xixinxin.bawie.com.todaydemo.fragment.TwoFragment;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private RadioButton bt_one, bt_two, bt_three;
    private ImageView iv_one, iv_two, iv_three;
    private ArrayList<RadioButton> buttonlist = new ArrayList<>();
    private RadioGroup bt_group;
    private FrameLayout fl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //找控件
        initView();
    }

    private void initView() {
        fl = (FrameLayout) findViewById(R.id.fl);
        bt_group = (RadioGroup) findViewById(R.id.bt_group);
        bt_one = (RadioButton) findViewById(R.id.bt_one);
        bt_two = (RadioButton) findViewById(R.id.bt_two);
        bt_three = (RadioButton) findViewById(R.id.bt_three);
        iv_one = (ImageView) findViewById(R.id.iv_one);
        iv_two = (ImageView) findViewById(R.id.iv_two);
        iv_three = (ImageView) findViewById(R.id.iv_three);
        iv_one.setOnClickListener(this);
        iv_two.setOnClickListener(this);
        iv_three.setOnClickListener(this);
        buttonlist.add(bt_one);
        buttonlist.add(bt_two);
        buttonlist.add(bt_three);
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.add(R.id.fl,new OneFragment(),"onefragment");
        beginTransaction.commit();
        bt_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int id) {
                ChangeColor(id);
                switch (id) {
                    case R.id.bt_one:
                        ChangeFragment(new OneFragment(),"onefragment");
                        iv_one.setImageResource(R.mipmap.bb);
                        iv_two.setImageResource(R.mipmap.c);
                        iv_three.setImageResource(R.mipmap.a);
                        break;
                    case R.id.bt_two:
                        ChangeFragment(new TwoFragment(),"twofragment");
                        iv_one.setImageResource(R.mipmap.b);
                        iv_two.setImageResource(R.mipmap.cc);
                        iv_three.setImageResource(R.mipmap.a);
                        break;
                    case R.id.bt_three:
                        ChangeFragment(new ThreeFragment(),"threefragment");
                        iv_one.setImageResource(R.mipmap.b);
                        iv_two.setImageResource(R.mipmap.c);
                        iv_three.setImageResource(R.mipmap.aa);
                        break;
                }
            }
        });
    }

    public void ChangeColor(int id) {
        for (RadioButton bb : buttonlist) {
            if (bb.getId() == id) {
                bb.setTextColor(Color.RED);
            } else {
                bb.setTextColor(Color.GRAY);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_one:
                ChangeFragment(new OneFragment(),"onefragment");
                bt_one.setTextColor(Color.RED);
                bt_two.setTextColor(Color.GRAY);
                bt_three.setTextColor(Color.GRAY);
                iv_one.setImageResource(R.mipmap.bb);
                iv_two.setImageResource(R.mipmap.c);
                iv_three.setImageResource(R.mipmap.a);
                break;
            case R.id.iv_two:
                ChangeFragment(new TwoFragment(),"twofragment");
                bt_one.setTextColor(Color.GRAY);
                bt_two.setTextColor(Color.RED);
                bt_three.setTextColor(Color.GRAY);
                iv_one.setImageResource(R.mipmap.b);
                iv_two.setImageResource(R.mipmap.cc);
                iv_three.setImageResource(R.mipmap.a);
                break;
            case R.id.iv_three:
                ChangeFragment(new ThreeFragment(),"threefragment");
                bt_one.setTextColor(Color.GRAY);
                bt_two.setTextColor(Color.GRAY);
                bt_three.setTextColor(Color.RED);
                iv_one.setImageResource(R.mipmap.b);
                iv_two.setImageResource(R.mipmap.c);
                iv_three.setImageResource(R.mipmap.aa);
                break;
        }
    }
    private void ChangeFragment(Fragment fragment,String tag){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl,fragment,tag);
        transaction.commit();
    }
}
