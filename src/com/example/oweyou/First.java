package com.example.oweyou;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class First extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		DB f = new DB(this);
		f.open();
		boolean tablexists = f.checkiftablexists();
		if(tablexists){
			startActivity(new Intent(First.this, Final.class));
		}else{
			startActivity(new Intent(First.this, MainActivity.class));
		}
		f.close();
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

}
