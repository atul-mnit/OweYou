package com.example.oweyou;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Settled extends Activity implements View.OnClickListener{ 
	DB settle = new DB(this);
	int[] amount;
	public int cntr,i,x,p,q,k,l;
	public int pMin,pMax,min,max;
	
	String info,from,to,s;
	Button payment;
	TextView tvf;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settled);
		tvf = (TextView) findViewById(R.id.tvSettlementDetails);
		payment = (Button) findViewById(R.id.bPaymentSettled);
		payment.setOnClickListener(this);
		
		
		settle.open();
		cntr = settle.getCount();
		amount = new int[cntr];
		info = "";
		
		for(i=0;i<cntr;i++){  //Get All data in an Array amount[]
			x=(int) settle.getBalance(i+1);
			amount[i]=x;
		}
		
		k=cntr;
		int flag=1;
	    for(l=0;l<cntr;l++){
	    	if(amount[l]!=0)
	    		flag=0;
	    }
	    if(flag == 1){
	    	info = "Payment Already Settled";
	    	k=-1;
	    	
	    }
		
		while(k >= 0){
			
		    	
			//finding minimum
			min=amount[0];
			pMin = 0;
			for(p=1;p<cntr;p++){
				if(amount[p]<min){
					min = amount[p];
					pMin = p;
				}
			}
			//finding maximum
			max=amount[0];
			pMax = 0;
			for(q=1;q<cntr;q++){
				if(amount[q]>max){
					max = amount[q];
					pMax = q;
				}
			}
		    if((min*(-1))<=max){
		    	amount[pMin]=0;
		    	amount[pMax]=max+min;
		    	String temp = "" + min*(-1);
		    	from = settle.getname(pMin+1);
		    	to = settle.getname(pMax+1);
		    	info = info + from + " Owes " + "Rs. " + temp + " to " + to + "\n"; 
		    }else if((min*(-1)>max)){
		    	amount[pMax]=0;
		    	amount[pMin]=max+min;
		    	String temp = "" + min*(-1);
		    	from = settle.getname(pMin+1);
		    	to = settle.getname(pMax+1);
		    	info = info + from + " Owes " + "Rs. " + temp + " to " + to + "\n"; 
		    	
		    }
		    k--;
		    flag=1;
		    for(l=0;l<cntr;l++){
		    	if(amount[l]!=0)
		    		flag=0;
		    }
		    if(flag == 1)
		    	break;
		}
		tvf.setText(info);
		
		
		
	}
	/*private int isarrayempty(int[] amount2, int n) {
		// TODO Auto-generated method stub
		int i;
		
		for(i=0;i<n;i++){
			if(amount2[i]!=0){
				return 0;
			}
		}
		return 1;
	}*/
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.bPaymentSettled:
			for(l=1;l<=cntr;l++){
				s = settle.getname(l);
				settle.updatedb(l, s, "0");
			}
			settle.close();
			Toast.makeText(Settled.this, "Payment Settled", Toast.LENGTH_SHORT).show();
			startActivity(new Intent(Settled.this, Final.class));
			break;
		}
	}

}
