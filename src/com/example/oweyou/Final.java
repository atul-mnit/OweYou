package com.example.oweyou;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Final extends Activity implements View.OnClickListener{
	Button AddP, Expnd, DelP, SetU, ex,inf;
	TextView tv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.finalscreen);
		tv = (TextView) findViewById(R.id.textView1);
		AddP = (Button) findViewById(R.id.bAddPartners);
		Expnd = (Button) findViewById(R.id.bNewExpend);
		SetU = (Button) findViewById(R.id.bSettleUp);
		inf = (Button) findViewById(R.id.binfo);
		DelP = (Button) findViewById(R.id.bDeleteP);
		ex = (Button) findViewById(R.id.bexit);
		ex.setOnClickListener(this);
		inf.setOnClickListener(this);
		DelP.setOnClickListener(this);
		AddP.setOnClickListener(this);
		Expnd.setOnClickListener(this);
		SetU.setOnClickListener(this);
		
		DB tex = new DB(this);
		tex.open();
		long l = tex.getAdminBalance();
		tex.close();
		if(l==0){
			tv.setText("You Are Even");
		}else if(l>0){
			tv.setText("Your Friends Owe You Rs." + l);
		}else{
			tv.setText("You Owe Your Friends Rs." + l*(-1));
		}
		
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.bAddPartners:
			startActivity(new Intent(Final.this, Second.class));
			break;
		case R.id.bNewExpend:
			startActivity(new Intent(Final.this, Expenditure.class));
			break;
		case R.id.bDeleteP:
			startActivity(new Intent(Final.this, Del.class));
			break;
		case R.id.bSettleUp:
			startActivity(new Intent(Final.this, Settle.class));
			break;
		case R.id.binfo:
			startActivity(new Intent(Final.this, Info.class));
			break;
		case R.id.bexit:
			Intent intent = new Intent(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_HOME);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);
			//finish();
			//System.exit(0);
			break;
			 
		}
	}

}
