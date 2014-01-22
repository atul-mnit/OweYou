package com.example.oweyou;



import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SQLView extends Activity implements View.OnClickListener{
	
	EditText toDelete;
	Button DelPart;
	String in;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sqlview);
		toDelete = (EditText) findViewById(R.id.etDelete);
		DelPart = (Button) findViewById(R.id.bDel);
		DelPart.setOnClickListener(this);
		TextView tv = (TextView) findViewById(R.id.tvSQLinfo);
		DB info = new DB(this);
		info.open();
		in = "";
		int x = info.getCount();
		for(int i=1;i<=x;i++){
			in = in + i + " " + info.getname(i) + "                   " + info.getBalance(i) + "\n";
		}
		info.close();
		tv.setText(in);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.bDel:
			//boolean worked = true;
			//try{
			String name1 = toDelete.getText().toString();
			long l1 = Long.parseLong(name1);
			DB del = new DB(this);
			del.open();
			int cntr = del.getCount();
			if(l1>cntr || l1<0){
				Toast.makeText(SQLView.this, "Invalid Serial Number", Toast.LENGTH_SHORT).show();
			}else{
			del.deleteEntry(l1);
			del.deleteTempEntry(l1);
			del.close();
			Toast.makeText(SQLView.this, "Partner Deleted", Toast.LENGTH_SHORT).show();
			startActivity(new Intent(SQLView.this, Final.class));
			}
			/*}catch (Exception e){
				worked = false;
				String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("Sorry!");
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
					startActivity(new Intent(SQLView.this, Final.class));
				}
				
			}*/
			
			
			break;
		}
	}

}
