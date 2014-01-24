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

public class Expenditure extends Activity implements View.OnClickListener{
	public static int cntr,i;
	public static long y,z1,z2,x;
	DB exp = new DB(this);
	String na,nb;
	Button next;
	EditText amntpaid, amntcnsmed;
	TextView tv1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.expenditure);
		exp.open();
		next = (Button) findViewById(R.id.bnext);
		tv1 = (TextView) findViewById(R.id.tvusername);
		amntpaid = (EditText) findViewById(R.id.etAmntPaid);
		amntcnsmed = (EditText) findViewById(R.id.etAmntCnsumed);
		next.setOnClickListener(this);
		cntr = exp.getCount();
		//String f = "" + cntr;
		//tv1.setText(f);
		/*boolean w = true;
		try{
			exp.dropTempTable();
		}catch (Exception e){
			w = false;
			String error = e.toString();
			Dialog d = new Dialog(this);
			d.setTitle("Error Dropiing!");
			TextView tv = new TextView(this);
			tv.setText(error);
			d.setContentView(tv);
			d.show();
		}finally{
			if(w){
				Dialog d = new Dialog(this);
				d.setTitle("done ;) !");
				TextView tv = new TextView(this);
				tv.setText("Success");
				d.setContentView(tv);
				d.show();
				
			}
		}*/
		for(i=1;i<=cntr;i++){
			String namefromMain = exp.getname(i);
			exp.updateTempEntry(i, namefromMain, "0");
		}
		i=1;
		na = exp.getname(i);
		
		y = exp.getBalance(i);
		tv1.setText(na);
		
					
			
		}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bnext:
						
				String p = amntpaid.getText().toString();
				if(p.contentEquals("")){
					p = "0";
				}
				long paid = Long.parseLong(p);
				String c = amntcnsmed.getText().toString();
				if(c.contentEquals("")){
					c = "0";
				}
				long consumed = Long.parseLong(c);
				//int b = (int) (y + (int) (paid - consumed)); This was here
				int b = (int) (paid - consumed);
				String bal = "" + b;
				//DB up = new DB(this);
				//up.open();
				//exp.updatedb(i,na,bal);    THis was here
				boolean diditwork = true;
				//try{
					exp.updateTempEntry(i,na,bal);
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
						d.setTitle("Heck Yao Temp Entry!");
						TextView tv = new TextView(this);
						tv.setText("Success");
						d.setContentView(tv);
						d.show();
						
					}
					
				}*/
				//up.close();
				i = i+1;
				if(i>cntr){
					long x=0;
					int k,sum;
					for(k=1;k<=cntr;k++){
						
						sum = (int) exp.getTempBalance(k);
						x = x + sum;
					}
				    if(x !=0 ){
				    	Dialog d = new Dialog(this);
						d.setTitle("Error!");
						TextView tv = new TextView(this);
						tv.setText("Expenditure Entered InCorrect(Total Not 0)!");
						d.setCanceledOnTouchOutside(false);
						d.setContentView(tv);
						d.show();
						Toast.makeText(Expenditure.this, "Incorrect Inputs(Total Not 0)", Toast.LENGTH_SHORT).show();
						
				    }else{
				    	for(k=1;k<=cntr;k++){
				    		nb=exp.getname(k);
				    		z1=exp.getBalance(k);
				    		z2=exp.getTempBalance(k);
				    		x=z1+z2;
				    		String x1 = "" + x;
				    		exp.updatedb(k,nb,x1);
				    	}
				    }
					exp.close();
					finish();
					startActivity(new Intent(Expenditure.this, Final.class));
					break;
				}
				na = exp.getname(i);
				y = exp.getBalance(i);
				//name.close();
				tv1.setText(na);	
				amntpaid.setText("0");
				amntcnsmed.setText("0");
				break;
			}
			
			
			
			
		}
		
		
}

