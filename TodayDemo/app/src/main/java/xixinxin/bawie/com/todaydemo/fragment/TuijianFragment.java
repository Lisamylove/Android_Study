package xixinxin.bawie.com.todaydemo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.bawei.xlistviewlibrary.XListView;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

import xixinxin.bawie.com.todaydemo.R;
import xixinxin.bawie.com.todaydemo.activity.WebActivity;
import xixinxin.bawie.com.todaydemo.adapter.ResultBaseAdapter;
import xixinxin.bawie.com.todaydemo.bean.Result;

/**
 * 1: @author 席鑫鑫
 * 2: @time 2017/3/14 10:35
 */
public class TuijianFragment extends Fragment {

    private XListView xlv;
    private String url;
    private ResultBaseAdapter adapter;
    private List<Result.DataBean> data;

    public TuijianFragment() {
    }

    public TuijianFragment(String url) {
        this.url = url;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.titlesfragment, null);
        initView(view);
        getData();
        return view;
    }

    private void initView(View view) {
        final Handler handler = new Handler();
        xlv = (XListView) view.findViewById(R.id.xlv);
        xlv.setPullRefreshEnable(true);
        xlv.setPullLoadEnable(true);
        xlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent it=new Intent(getActivity(), WebActivity.class);
                it.putExtra("url",data.get(position-1).getUrl());
                startActivity(it);
            }
        });
        xlv.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                getData();
                Toast.makeText(getActivity(), "下拉刷新", Toast.LENGTH_SHORT).show();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        xlv.stopRefresh();
                        getData();
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore() {
                Toast.makeText(getActivity(), "上拉加载", Toast.LENGTH_SHORT).show();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        xlv.stopLoadMore();
                        getData();
                    }
                }, 2000);
            }
        });
    }

    private void getData() {
        RequestParams params = new RequestParams(url);
        params.setCacheMaxAge(1000 * 60);
        x.http().get(params, new Callback.CacheCallback<String>() {
            private String result = null;

            @Override
            public boolean onCache(String result) {
                this.result = result;
                //默认flase不走缓存
                return true;
            }

            @Override
            public void onSuccess(String result) {
                if (result != null) {
                    this.result = result;
                    getServiceData(result);
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void getServiceData(String result) {
        Gson gson = new Gson();
        Result json = gson.fromJson(result, Result.class);
        if(data==null){
            data = json.getData();
            adapter = new ResultBaseAdapter(getActivity(), data);
            xlv.setAdapter(adapter);
        }else{
            data.addAll(json.getData());
            adapter.notifyDataSetChanged();
        }
    }
}
