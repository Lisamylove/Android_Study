package xixinxin.bawie.com.todaydemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import xixinxin.bawie.com.todaydemo.R;

public class MainActivity extends AppCompatActivity {

    private ViewPager vp_viewpager;
    private ImageView iv_hh;
    private int time = 3;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
               if (time >0){
                   time--;
                   handler.sendEmptyMessageDelayed(0, 1000);
               }else{
                   Intent intent=new Intent(MainActivity.this,HomeActivity.class);
                   startActivity(intent);
                   finish();
               }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //找控件
        initView();
    }

    private void initView() {
        iv_hh = (ImageView) findViewById(R.id.iv_hh);
        handler.sendEmptyMessageDelayed(0, 1000);
    }
}
