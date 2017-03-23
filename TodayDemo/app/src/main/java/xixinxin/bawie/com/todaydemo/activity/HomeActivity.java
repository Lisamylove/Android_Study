package xixinxin.bawie.com.todaydemo.activity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import xixinxin.bawie.com.todaydemo.R;
import xixinxin.bawie.com.todaydemo.fragment.OneFragment;
import xixinxin.bawie.com.todaydemo.fragment.ThreeFragment;
import xixinxin.bawie.com.todaydemo.fragment.TwoFragment;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView iv_one, iv_two, iv_three;
    private FrameLayout fl;
    private SharedPreferences sha;
    private SharedPreferences.Editor edit;
    private TextView tv_one,tv_two,tv_three;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home);
        sha = getSharedPreferences("congin", MODE_PRIVATE);
        edit = sha.edit();
        boolean day=sha.getBoolean("day",false);
        if (day){
            setTheme(R.style.NightAppTheme);
        }else{
            setTheme(R.style.AppTheme);
        }
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.add(R.id.fl, new OneFragment(), "onefragment");
        beginTransaction.commit();
        //找控件
        initView();
    }

    private void initView() {
        fl = (FrameLayout) findViewById(R.id.fl);
        tv_one = (TextView) findViewById(R.id.tv_one);
        tv_two = (TextView) findViewById(R.id.tv_two);
        tv_three = (TextView) findViewById(R.id.tv_three);
        iv_one = (ImageView) findViewById(R.id.iv_one);
        iv_two = (ImageView) findViewById(R.id.iv_two);
        iv_three = (ImageView) findViewById(R.id.iv_three);
        iv_one.setOnClickListener(this);
        iv_two.setOnClickListener(this);
        iv_three.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_one:
                ChangeFragment(new OneFragment(), "onefragment");
                tv_one.setTextColor(Color.RED);
                tv_two.setTextColor(Color.GRAY);
                tv_three.setTextColor(Color.GRAY);
                iv_one.setImageResource(R.mipmap.bb);
                iv_two.setImageResource(R.mipmap.c);
                iv_three.setImageResource(R.mipmap.a);
                break;
            case R.id.iv_two:
                ChangeFragment(new TwoFragment(), "twofragment");
                tv_one.setTextColor(Color.GRAY);
                tv_two.setTextColor(Color.RED);
                tv_three.setTextColor(Color.GRAY);
                iv_one.setImageResource(R.mipmap.b);
                iv_two.setImageResource(R.mipmap.cc);
                iv_three.setImageResource(R.mipmap.a);
                break;
            case R.id.iv_three:
                ChangeFragment(new ThreeFragment(), "threefragment");
                tv_one.setTextColor(Color.GRAY);
                tv_two.setTextColor(Color.GRAY);
                tv_three.setTextColor(Color.RED);
                iv_one.setImageResource(R.mipmap.b);
                iv_two.setImageResource(R.mipmap.c);
                iv_three.setImageResource(R.mipmap.aa);
                break;
        }
    }

    private void ChangeFragment(Fragment fragment, String tag) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl, fragment, tag);
        transaction.commit();
    }
}
