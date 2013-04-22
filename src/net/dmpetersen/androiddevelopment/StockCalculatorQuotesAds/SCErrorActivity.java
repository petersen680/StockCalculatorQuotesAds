package net.dmpetersen.androiddevelopment.StockCalculatorAds;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class SCErrorActivity extends Activity 
{

	

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.errormain);
	    
	    
	  
	    
	    
	   
	    
	    
	 TextView error = (TextView) findViewById(R.id.data_display2);
	    
	    Bundle bundle = getIntent().getExtras();
	    if (bundle!=null){
	    	error.setText(bundle.getString("message"));  
	    }
	    
	    else{
	    	error.setText("");
	    }
	    
	   
	    
	    Button Back = (Button) findViewById(R.id.btn_back2);
	    Back.setOnClickListener(new OnClickListener(){
	    	public void onClick(View arg0){
    	
	    	
	    		
	    		finish();
    
	    	}
	    	});

	    
	   
	    
	    
	}

	

	    
	
	
	
}
