package com.example.oweyou;



import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener{
	EditText et;
	Button b;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et = (EditText) findViewById(R.id.editText1);
	    b = (Button) findViewById(R.id.bGO);
		b.setOnClickListener(this);
		 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		
		case R.id.bGO:
			//Toast.makeText(MainActivity.this, "Button Clicked", Toast.LENGTH_SHORT).show();
			//boolean diditwork = true;
			//try{
			String name = et.getText().toString();
			if(name.contentEquals("")){
				/*Toast t = Toast.makeText(MainActivity.this, "No Name Entered!", 5000);  //Values is in MiliSeconds
				t.setGravity(Gravity.CENTER,0,0);
				t.show();*/
				AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
				builder.setTitle("Alert!");
		        builder.setMessage("No Name Entered!\nEnter a Name to Continue");
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
			DB entry = new DB(this);
		    entry.open();
		    entry.createEntry(name, "0");
		    entry.createTempEntry(name, "0");
		    entry.close();
		    startActivity(new Intent(MainActivity.this, Second.class));
			}
			/*}catch (Exception e){
				diditwork = false;
				String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("Shit!");
				TextView tv = new TextView(this);
				tv.setText(error);
				d.setContentView(tv);
				d.show();
				
			}finally{
				if(diditwork){
					Dialog d = new Dialog(this);
					d.setTitle("Heck Yea!");
					TextView tv = new TextView(this);
					tv.setText("Success");
					d.setContentView(tv);
					d.show();
				}
				}*/
		    break;
		}
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
}
