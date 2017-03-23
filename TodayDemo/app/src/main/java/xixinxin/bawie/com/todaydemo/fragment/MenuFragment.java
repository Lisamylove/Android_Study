package xixinxin.bawie.com.todaydemo.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.shareboard.SnsPlatform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;
import xixinxin.bawie.com.todaydemo.R;

/**
 * 1: @author 席鑫鑫
 * 2: @time 2017/3/12 19:15
 */
public class MenuFragment extends Fragment implements View.OnClickListener {
    private TextView tv_yejian;
    private ImageView moshi;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor edit;
    private ImageView iv_qq;
    public ArrayList<SnsPlatform> platforms = new ArrayList<SnsPlatform>();
    private SHARE_MEDIA[] list = {SHARE_MEDIA.QQ};
    private boolean isauth;
    private ImageView iv_phone;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        sharedPreferences = getActivity().getSharedPreferences("congin", Context.MODE_PRIVATE);
        edit = sharedPreferences.edit();
        View view = inflater.inflate(R.layout.menufragment, null);
        //启动SDK
        cn.smssdk.SMSSDK.initSDK(getActivity(), "1c321d4cb6db6", "56ef9d5dceb78aad615c8dd25bad2b3b");
        initView(view);
        return view;
    }

    private void initView(View view) {
        //找控件
        moshi = (ImageView) view.findViewById(R.id.moshi);
        tv_yejian = (TextView) view.findViewById(R.id.tv_yejian);
        iv_qq = (ImageView) view.findViewById(R.id.iv_qq);
        iv_phone = (ImageView) view.findViewById(R.id.iv_phone);
        iv_qq.setOnClickListener(this);
        moshi.setOnClickListener(this);
        iv_phone.setOnClickListener(this);
        initPlatforms();
        isauth = UMShareAPI.get(getActivity()).isAuthorize(getActivity(), platforms.get(0).mPlatform);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_qq:
                if (isauth) {
                    Toast.makeText(getActivity(), "授权成功", Toast.LENGTH_SHORT).show();
                    UMShareAPI.get(getActivity()).deleteOauth(getActivity(), platforms.get(0).mPlatform, authListener);
                } else {
                    Toast.makeText(getActivity(), "登录成功", Toast.LENGTH_SHORT).show();
                    UMShareAPI.get(getActivity()).doOauthVerify(getActivity(), platforms.get(0).mPlatform, authListener);
                }
                break;
            case R.id.moshi:
                final boolean day = sharedPreferences.getBoolean("day", false);
                if (!day) {
                    edit.putBoolean("day", true);
                    edit.commit();
                } else {
                    edit.putBoolean("day", false);
                    edit.commit();
                }
                //重新创建
                getActivity().recreate();
                break;
            case R.id.iv_phone:
                    //打开注册界面
                RegisterPage page=new RegisterPage();
                //事件调用监听类
                page.setRegisterCallback(new EventHandler(){
                    @Override//短信SDK回调
                    public void afterEvent(int i, int i1, Object data) {
                        super.afterEvent(i, i1, data);
                        //解析注册结果
                        if (i1== SMSSDK.RESULT_COMPLETE){//如果状态为完成状态，表示注册成功
                            //获得data中数据
                            HashMap<String,Object> map= (HashMap<String, Object>) data;
                            //获取手机号所在的国家信息
                            String country = (String) map.get("country");
                            //获取收取验证码的手机号
                            String phone = (String) map.get("phone");
//                            //提交信息到mob注册
//                            subMobInfo(country,phone);
                        }
                    }
                });
                page.show(getActivity());
                break;
        }
    }

    private void subMobInfo(String country, String phone) {
        Random radom=new Random();
        String name = Math.abs(radom.nextInt()) + "";
        String NickName="设置个昵称";
        SMSSDK.submitUserInfo(name,NickName,null,country,phone);
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(getActivity()).onActivityResult(requestCode, resultCode, data);
    }

    UMAuthListener authListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
        }

        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            Toast.makeText(getActivity(), "请求成功了", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText(getActivity(), "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(getActivity(), "取消了", Toast.LENGTH_LONG).show();
        }
    };

}
