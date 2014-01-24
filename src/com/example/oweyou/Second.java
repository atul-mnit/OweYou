package com.example.oweyou;



import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Second extends Activity implements View.OnClickListener{
	TextView tv;
	EditText etPartner;
	Button Add, Go;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second);
		tv = (TextView) findViewById(R.id.textView2);
		etPartner = (EditText) findViewById(R.id.etNameAdd);
		Add = (Button) findViewById(R.id.bAdd);
		Go = (Button) findViewById(R.id.bGo);
		Add.setOnClickListener(this);
		Go.setOnClickListener(this);
		DB user = new DB(this);
		user.open();
		String USER = user.getusername();
		user.close();
		
		//String AdminName = getIntent().getExtras().getString("123");
		String message = "Welcome "
				+ USER
				+ "! \nAdd Your Partners";
		tv.setText(message);
	
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		
		case R.id.bAdd:
			//boolean worked = true;
			//try{
			String partner = etPartner.getText().toString();
			if(partner.contentEquals("")){
				AlertDialog.Builder builder = new AlertDialog.Builder(Second.this);
				builder.setTitle("Alert!");
		        builder.setMessage("No Name Entered!\nEnter a Name to Add to Your Partner List");
		        builder.setCancelable(false);  //cant cancell the dilog by pressing back button
		        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						// TODO Auto-generated method stub
						arg0.cancel();
					}
				});
		        AlertDialog alert = builder.create();
			    alert.show();
			}else{
			DB enter = new DB(this);
			enter.open();
			enter.createEntry(partner, "0");
			enter.createTempEntry(partner, "0");
			enter.close();
			Toast.makeText(Second.this, "Partner Added", Toast.LENGTH_SHORT).show();
			etPartner.setText("");
			}
			/*}catch (Exception e){
				worked = false;
				String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("Shit!");
				TextView tv = new TextView(this);
				tv.setText(error);
				d.setContentView(tv);
				d.show();
			}finally{
				if(worked){
					Dialog d = new Dialog(this);
					d.setTitle("Heck Yea!");
					TextView tv = new TextView(this);
					tv.setText("Success");
					d.setContentView(tv);
					d.show();
				}
			}*/
			break;
		case R.id.bGo:
			startActivity(new Intent(Second.this, Final.class));
			
			break;
			
		}
	}

}

