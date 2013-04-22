package net.dmpetersen.androiddevelopment.StockCalculatorAds;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;

public class SCResultActivity extends Activity {


	

	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.resultmain);
	    
	    
	

	    TextView results = (TextView) findViewById(R.id.data_display);
	    EditText shares = (EditText) findViewById(R.id.number_shares_to_buy);
	    EditText capital = (EditText) findViewById(R.id.capital_needed);
	    EditText profit = (EditText) findViewById(R.id.profit_if);
	    
	    Bundle bundle = getIntent().getExtras();
	    if (bundle!=null){
	    	results.setText(bundle.getString("string"));  
	    	
	    
	    	Integer integer = new Integer(bundle.getInt("numberofshares"));
	    	
   	     shares.setText(integer.toString());
   	     capital.setText(bundle.getString("total"));
   	     profit.setText(bundle.getString("profit"));
	    	
	    	
	    }
	    
	    else{
	    	results.setText("");
	    	
	    }
	    
	    
	    
	    Button Back = (Button) findViewById(R.id.btn_back);
	    Back.setOnClickListener(new OnClickListener(){
	    	public void onClick(View arg0){
    	
	    	
	    		
	    		finish();
	    	}
	    	});


	}





	public void onStart() {
	super.onStart();
	   
	}


	public void onResume() {
	super.onResume();
	
	   
	}


	public void onPause() {
	super.onPause();
	   
	}
	
	
	public void onStop() {
	super.onStop();
	   
	}
	
	
	public void onDestroy() {
	super.onDestroy();
	   
	}
	
	
	public void onRestart() {
	super.onRestart();
	   
	}










}
