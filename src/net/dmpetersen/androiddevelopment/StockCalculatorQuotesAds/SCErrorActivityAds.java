package net.dmpetersen.androiddevelopment.StockCalculatorAds;

import android.os.Bundle;
import com.google.ads.*;

;


public class SCErrorActivityAds extends SCErrorActivityQuotes{

	private AdView adView;
	
	
	@Override public void onCreate(Bundle savedInstanceState) {
		

        super.onCreate(savedInstanceState);
        
        
        
       
            
        
     // Look up the AdView as a resource and load a request.    
        AdView adView = (AdView)this.findViewById(R.id.adView);    
        adView.loadAd(new AdRequest());
            
            
            
	       }  
    	

    	

    	@Override  public void onDestroy() {    
    		if (adView != null) {      
    			adView.destroy();    
    			}    
    		
    		super.onDestroy();  
    		}
        

}

