package com.example.oweyou;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Settle extends Activity implements View.OnClickListener{
	Button Set;
	TextView tv;
	@Override
	protected void onCreate(Bundle savedInstanceState){
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settle);
		tv = (TextView) findViewById(R.id.tvSQLinfoSettleUp);
		Set = (Button) findViewById(R.id.bSettle);
		Set.setOnClickListener(this);
		DB info = new DB(this);
		info.open();
		String data = "";
		int x = info.getCount();
		for(int i=1;i<=x;i++){
			data = data + i + " " + info.getname(i) + "                   " + info.getBalance(i) + "\n";
		}
		info.close();
		tv.setText(data);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		  case R.id.bSettle:
			  AlertDialog.Builder builder = new AlertDialog.Builder(Settle.this);
		        builder.setMessage("Do You Want to SettleUp??");
		        builder.setCancelable(false);  //cant cancell the dilog by pressing back button
		        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						// TODO Auto-generated method stub
						startActivity(new Intent(Settle.this, Settled.class));
					}
				});
		        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dialog.cancel();
					}
				});
		      AlertDialog alert = builder.create();
		      alert.show();	
		      
		      
		      break;
			}
			  
			  
		}
	}


