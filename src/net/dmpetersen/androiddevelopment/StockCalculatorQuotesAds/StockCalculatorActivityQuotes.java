package net.dmpetersen.androiddevelopment.StockCalculatorQuotesAds;


import hykwokgonzales.StockDataProviderYahoo;
import hykwokgonzales.StockData;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.app.Dialog;
import android.content.Intent;


public class StockCalculatorActivityQuotes extends StockCalculatorActivity{

	
    private StockDataProviderYahoo provider = null;
	
	private StockData stockData = new StockData();
	
	private String[] symbol = {""};
	
    private EditText editTextSymbol;
    
	private Button send;
	
	private TextView forSymbol;
	
	private String stringSymbol;
	
	private Double doublePrice;
	
	private String stringName;
	
	private boolean flag;
	
	
	@Override public void onCreate(Bundle savedInstanceState) {
		
        super.onCreate(savedInstanceState);
        
      
        editTextSymbol = (EditText) findViewById(R.id.EditTextSymbol);
        
        editTextSymbol.setText("");
        
        
        send = (Button) findViewById(R.id.Send);
        
      
        
       
        forSymbol = (TextView) findViewById(R.id.ForSymbol);
        
        
        provider = new StockDataProviderYahoo(this);
        	
     		flag = false;
     	
     		
     		
     		
     		
     		
     
     		
    send.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
        
        
        	
        	
            symbol[0] = editTextSymbol.getText().toString();
            
            
            	
            
            if (provider.startGettingDataFromYahoo(symbol)) {
            	
            	
        		stockData = provider.getStockData();
        	
            //still blowing up - here
            stringSymbol = new String(stockData.getSymbol());
            
            stringName = new String(stockData.getName());
            
            doublePrice = new Double(stockData.getPrice());
            
            forSymbol.setText(" " + stringSymbol.toString() + "   " + stringName.toString() + "   " + doublePrice.toString());
            
            flag = true;
            
            
            }
        	else{
        		
        		
    
        		
        		//stockData = provider.getStockData();
            
        		stockData.setSymbol("xxx");
    	    	stockData.setPrice(0.0);
    	 		stockData.setPercentileChange(0.0);
    	 		stockData.setMaximum(0.0);
    	 		stockData.setMinimum(0.0);
    	 		stockData.setName("xxx");
        		
        		
        		stringSymbol = new String(stockData.getSymbol());
            
            stringName = new String(stockData.getName());
            
            doublePrice = new Double(stockData.getPrice());
            
            forSymbol.setText(" " + stringSymbol.toString() + "   " + stringName.toString() + "   " + doublePrice.toString());
            
            showError();
            
            flag = true;
            
         
            
        	}
            
        }
    
    });
    
    
    
    
   
    
  
  
   
        	
    
	}
	
	
	
	
	
	
	

	public void onStart() {
	super.onStart();
	
	
	   
	  // flag = false;
	   
	   Log.d("SCA", "onStart");
	   
	   
	   
	}
	
	
	
	public void onRestart() {
	super.onRestart();
	
	//flag = false;
	
	Log.d("SCA", "onRestart");
	
	
	}
	
	
	public void onResume() {
	super.onResume();
	
	//flag = false;
	
	Log.d("SCA", "onResume");
	
	
	   
	}
	
	
	
	/*
	
	
	public void onPause() {
	super.onPause();
	
	
	
	
	}
	
	
	
	
	
	public void onStop() {
	super.onStop();
	   
	}
	
	
	public void onDestroy() {
	super.onDestroy();
	   
	}

*/
    
	
	

	
	
	 
	
	 private void showError(){
	    	
		    
	    	
	  	  dialog = new Dialog(this);


	        dialog.setContentView(R.layout.errordialog);

	        dialog.setTitle(getString(R.string.error));

	        dialog.setCancelable(true);
	        
	        //there are a lot of settings, for dialog, check them all out!

	        //set up text

	        TextView text = (TextView) dialog.findViewById(R.id.errortextview);

	        text.setText(R.string.errortext); 


	        //set up image view

	        //ImageView img = (ImageView) dialog.findViewById(R.id.ImageView01);

	        //img.setImageResource(R.drawable.nista_logo);


	        //set up button

	        Button button = (Button) dialog.findViewById(R.id.errorbutton);

	        button.setOnClickListener(new OnClickListener() {

	      

	            public void onClick(View v) {

	                dialog.dismiss();

	            }


	        
	        });
	         

	        dialog.show();

	    }

	
	
    
    private void showError2(){
    	
    	
    	  dialog = new Dialog(this);


          dialog.setContentView(R.layout.errordialog2);

          dialog.setTitle(getString(R.string.error2));

          dialog.setCancelable(true);

          //set up text

          TextView text = (TextView) dialog.findViewById(R.id.errortextview2);

          text.setText(R.string.errortext2); 


          //set up image view

          //ImageView img = (ImageView) dialog.findViewById(R.id.ImageView01);

          //img.setImageResource(R.drawable.nista_logo);


          //set up button

          Button button = (Button) dialog.findViewById(R.id.errorbutton2);

          button.setOnClickListener(new OnClickListener() {

     

              public void onClick(View v) {

                  dialog.dismiss();

              }


          
          });
       

          dialog.show();

      }

     
    
  	
}