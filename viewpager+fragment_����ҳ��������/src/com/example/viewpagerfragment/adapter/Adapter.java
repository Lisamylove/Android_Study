package com.example.viewpagerfragment.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class Adapter extends PagerAdapter {
	private Context context;
	private ArrayList<Integer> list;

	public Adapter(Context context, ArrayList<Integer> list) {
		super();
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0==arg1;
	}
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		ImageView imageView=new ImageView(context);
		imageView.setImageResource(list.get(position));
		//添加到容器中
		container.addView(imageView);
		return imageView;
	}
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}
}
