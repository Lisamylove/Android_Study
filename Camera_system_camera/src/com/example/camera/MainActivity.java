package com.example.camera;

import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView.FindListener;
import android.widget.ImageView;

public class MainActivity extends Activity {

    private ImageView imageview;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageview = (ImageView) findViewById(R.id.imgeview);
    }
	
	public void startCamera(View v){
		//通过意图打开系统照相机
		Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(intent, 1000);
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==1000){
			Bitmap bitmap = data.getParcelableExtra("data");
			imageview.setImageBitmap(bitmap);
		}
	}
    
}
