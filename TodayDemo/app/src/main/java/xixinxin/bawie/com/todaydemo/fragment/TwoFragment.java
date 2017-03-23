package xixinxin.bawie.com.todaydemo.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.bawei.xlistviewlibrary.XListView;
import com.google.gson.Gson;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import xixinxin.bawie.com.todaydemo.R;
import xixinxin.bawie.com.todaydemo.adapter.DataAdapter;
import xixinxin.bawie.com.todaydemo.bean.VideoUser;

/**
 * 1: @author 席鑫鑫
 * 2: @time 2017/3/12 15:55
 */
public class TwoFragment extends Fragment {

    private XListView xlistview;
    private SpringView springview;
    private String videourl = "http://c.m.163.com/recommend/getChanListNews?channel=T1457068979049&size=20&offset=0&fn=2&passport=&devId=TBZKWzruKEUzMq%2FrkQGD1Q%3D%3D&lat=LzuLo%2Fp8uPCPH%2FM5fji6EQ%3D%3D&lon=sYBJWoBmwCl%2FrUcIj4B0%2FQ%3D%3D&version=21.0&net=wifi&ts=1489735397&sign=nwb%2BCymOI4mwm151aDlhpw8RrfncqzRdq6RApa%2BORCF48ErR02zJ6%2FKXOnxX046I&encryption=1&canal=360_news&mac=VRdaVCjS2%2FXnr0PFFJxgnzC0ruPUyXM4Jwce4E9oM30%3D&open=&openpath=";
    private ListView lv;
    Handler handler=new Handler();
    private List<VideoUser.视频Bean> video;
    private DataAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.twofragment, null);
        lv = (ListView) view.findViewById(R.id.lv_listview);
        springview = (SpringView) view.findViewById(R.id.springview);
        LoadSpring();
        getData();
        return view;
    }

    private void LoadSpring() {
        springview.setType(SpringView.Type.FOLLOW);
        springview.setHeader(new DefaultHeader(getActivity()));
        springview.setFooter(new DefaultFooter(getActivity()));
        springview.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        springview.onFinishFreshAndLoad();
                        getData();
                    }
                },2000);
            }

            @Override
            public void onLoadmore() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        springview.onFinishFreshAndLoad();
                        getData();
                    }
                },2000);
            }
        });
    }

    private void getData() {
        MyAsyncTask task = new MyAsyncTask();
        task.execute();
    }

    public class MyAsyncTask extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            HttpClient client = new DefaultHttpClient();
            HttpGet get = new HttpGet(videourl);
            try {
                HttpResponse response = client.execute(get);
                if (response.getStatusLine().getStatusCode() == 200) {
                    InputStream inputStream = response.getEntity().getContent();
                    ByteArrayOutputStream boa = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int len = 0;
                    while ((len = inputStream.read(buffer)) != -1) {
                        boa.write(buffer, 0, len);
                    }
                    boa.close();
                    inputStream.close();
                    String json = boa.toString();
                    return json;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Gson gson = new Gson();
            VideoUser result = gson.fromJson(s, VideoUser.class);
            if (video==null){
                video = result.get视频();
                adapter = new DataAdapter(getActivity(), video);
                lv.setAdapter(adapter);
            }else{
                video.addAll(result.get视频());
                adapter.notifyDataSetChanged();
            }
        }
    }
}
