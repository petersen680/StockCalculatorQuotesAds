package net.dmpetersen.androiddevelopment.StockCalculatorQuotesAds;

import android.os.Bundle;
import com.google.ads.*;
import com.google.ads.AdView;


public class StockCalculatorActivityQuotesAds extends StockCalculatorActivityQuotes{

	private AdView adView;
	
	
	@Override public void onCreate(Bundle savedInstanceState) {
		
        super.onCreate(savedInstanceState);
        
        
        
        // Look up the AdView as a resource and load a request.    
        AdView adView = (AdView)this.findViewById(R.id.adView);    
        adView.loadAd(new AdRequest());
        
        
      AppRater.app_launched(this);

        
        }  
	
	
	

	@Override  public void onDestroy() {    
		if (adView != null) {      
			adView.destroy();    
			}    
		
		super.onDestroy();  
		}
	}
		
		
		