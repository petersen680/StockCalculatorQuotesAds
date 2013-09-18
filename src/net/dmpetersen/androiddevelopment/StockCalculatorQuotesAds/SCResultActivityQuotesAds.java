package net.dmpetersen.androiddevelopment.StockCalculatorQuotesAds;

import android.os.Bundle;
import com.google.ads.*;


public class SCResultActivityQuotesAds extends SCResultActivityQuotes{

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

