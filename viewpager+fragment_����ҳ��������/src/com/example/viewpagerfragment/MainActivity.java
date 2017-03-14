package com.example.viewpagerfragment;

import java.util.ArrayList;

import com.example.viewpagerfragment.adapter.Adapter;

import android.R.integer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private ViewPager viewpager;
	private ArrayList<Integer> pclist = new ArrayList<Integer>();
	private Button bt_button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		viewpager = (ViewPager) findViewById(R.id.viewpager);
		bt_button = (Button) findViewById(R.id.bt_button);
		bt_button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(MainActivity.this,TwoActivity.class);
				startActivity(intent);
			}
		});
		viewpager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
				if(position==pclist.size()-1){
					bt_button.setVisibility(View.VISIBLE);
				}else{
					bt_button.setVisibility(View.GONE);
				}
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		// 添加数据
		indate();
		//设置数据适配器
		viewpager.setAdapter(new Adapter(this, pclist));
	}

	private void indate() {
		// TODO Auto-generated method stub
		pclist.add(R.drawable.image3);
		pclist.add(R.drawable.image4);
		pclist.add(R.drawable.image5);
		pclist.add(R.drawable.image6);
		pclist.add(R.drawable.image7);
	}

}
