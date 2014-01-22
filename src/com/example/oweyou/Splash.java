package com.example.oweyou;

import android.app.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class Splash extends Activity {

	@Override
	protected void onCreate(Bundle TravisLoveBacon) {
		// TODO Auto-generated method stub
		super.onCreate(TravisLoveBacon);
		setContentView(R.layout.splash);
		Thread timer = new Thread(){
			public void run(){
				try{
					sleep(4000);					
				}catch(InterruptedException e){
					e.printStackTrace();
				}finally{
					
					startActivity(new Intent(Splash.this,First.class));
					
				}
			}
		};
		timer.start();
	}
	
	
 @Override
protected void onPause() {
	// TODO Auto-generated method stub
	super.onPause();
	finish();
}
}
