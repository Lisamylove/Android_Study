package xixinxin.bawie.com.demodisanfang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.shareboard.SnsPlatform;

import java.util.ArrayList;
import java.util.Map;

import xixinxin.bawie.com.demodisanfang.R;

public class MainActivity extends AppCompatActivity {

    public ArrayList<SnsPlatform> platforms = new ArrayList<SnsPlatform>();
    private SHARE_MEDIA[] list = {SHARE_MEDIA.QQ};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bt = (Button) findViewById(R.id.bt_QQ);
        initPlatforms();
        final boolean isauth = UMShareAPI.get(this).isAuthorize(this, platforms.get(0).mPlatform);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isauth) {
                    Toast.makeText(MainActivity.this, "授权成功", Toast.LENGTH_SHORT).show();
                    UMShareAPI.get(MainActivity.this).deleteOauth(MainActivity.this, platforms.get(0).mPlatform, authListener);

                } else {
                    Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    UMShareAPI.get(MainActivity.this).doOauthVerify(MainActivity.this, platforms.get(0).mPlatform, authListener);
                }
            }
        });


    }

    private void initPlatforms() {
        platforms.clear();
        for (SHARE_MEDIA e : list) {
            if (!e.toString().equals(SHARE_MEDIA.GENERIC.toString())) {
                platforms.add(e.toSnsPlatform());
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
        //  Log.i("xxx", data.toString());

    }

    UMAuthListener authListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            // SocializeUtils.safeShowDialog(dialog);
        }

        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            //  SocializeUtils.safeCloseDialog(dialog);
            Toast.makeText(MainActivity.this, "成功了", Toast.LENGTH_LONG).show();
            //  notifyDataSetChanged();
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            //  SocializeUtils.safeCloseDialog(dialog);
            Toast.makeText(MainActivity.this, "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            //   SocializeUtils.safeCloseDialog(dialog);
            Toast.makeText(MainActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };

}
