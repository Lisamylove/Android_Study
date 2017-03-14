package xixinxin.bawie.com.demodisanfang.app;

import android.app.Application;

import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

/**
 * 1: @author 席鑫鑫
 * 2: @time 2017/3/13 20:00
 */
public class MyApplition extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        UMShareAPI.get(this);
    }
        {
            PlatformConfig.setQQZone("1106036236", "mjFCi0oxXZKZEWJs");
    }

}
