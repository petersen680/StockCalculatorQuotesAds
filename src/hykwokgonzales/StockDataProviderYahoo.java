/*
	Copyright 2010 Kwok Ho Yin and Jonathan Gonzalez (jonathan@jonbaraq.eu)

 	Licensed under the Apache License, Version 2.0 (the "License");
 	you may not use this file except in compliance with the License.
 	You may obtain a copy of the License at

  	http://www.apache.org/licenses/LICENSE-2.0

 	Unless required by applicable law or agreed to in writing, software
 	distributed under the License is distributed on an "AS IS" BASIS,
 	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 	See the License for the specific language governing permissions and
 	limitations under the License.
*/

package hykwokgonzales;

import android.content.Context;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import android.util.Log;

public class StockDataProviderYahoo {

	// Debug tag for LogCat.
	private static final String TAG = "SPV:StockDataProviderYahoo";
	
	private List<StockData>	data = new ArrayList<StockData>();
	
	// Format URL path (refer to http://www.gummy-stuff.org/Yahoo-data.htm for more information).
	private static final String URL_PREFIX = "http://finance.yahoo.com/d/quotes.csv?s=";
	
	// 4 items that we want to get symbol + last trade (price only) + percent change + name + day max + day min.
	private static final String[] YAHOO_FLAGS = { "s", "l1", "p2", "n", "h", "g"};
	
	private Context context;
	
	
	//begin modification - Petersen
	private StockData stockData = new StockData();
	
	private StockData stockData2 =new StockData();	
	
	private StockData stockDataReturn;  // = new StockData();
	
	private boolean exceptionFlag = false;
	
	private boolean exceptionFlag2 = false;
	//end modification - Petersen
	
	
	public StockDataProviderYahoo(Context context) {
		this.context = context;
	}
	  
	private String getUrl(String[] symbols) {
		String szURL = URL_PREFIX;
		//begin modification - Petersen
		//data.clear();
		//end modification - Petersen
		szURL += symbols[0];
		for(int i = 1; i < symbols.length; i++){
			szURL += "+" + symbols[i];
		}		
		szURL += "&f=";
		for(String flag : YAHOO_FLAGS) {
			szURL += flag;
		}
		Log.d(TAG, "URL=" + szURL);
		return szURL;
	}
	
	private void addStockData(String[] decodeString) {
		
		
		
  		
		
		try {
			
			
			//begin modification - Petersen
			//if (Double.parseDouble(decodeString[1]) > 0) {
			//end modification - Petersen
				
	  		
	  		
	  		// Symbol
	  		stockData2.setSymbol(decodeString[0].replace('"', ' ').toUpperCase());
	  		stockData2.setSymbol(stockData2.getSymbol().trim());
	  		// Price
	  		stockData2.setPrice(Double.parseDouble(decodeString[1]));
	  		// Percentile Change
	  		String percentileChangeString = decodeString[2];
	  		percentileChangeString = percentileChangeString.replace("%", "");
	  		percentileChangeString = percentileChangeString.replace("\"", "");
	  		stockData2.setPercentileChange(Double.parseDouble(percentileChangeString));
	  		// Name
	  		stockData2.setName(decodeString[3].replace('"', ' ').trim());
	  		// Day high
	  		stockData2.setMaximum(Double.parseDouble(decodeString[4]));
	  		// Day Low
	  		stockData2.setMinimum(Double.parseDouble(decodeString[5]));
	  		Log.d(TAG, "Symbol: " + stockData2.getSymbol());
	  		Log.d(TAG, "Name: " + stockData2.getName());
	  		Log.d(TAG, "Price: " + stockData2.getPrice());
	  		Log.d(TAG, "Change: " + stockData2.getPercentileChange());
	  		Log.d(TAG, "Maximum: " + stockData2.getMaximum());
	  		Log.d(TAG, "Minimum: " + stockData2.getMinimum());		
	  		
	  		
	 
	  		
	  	//begin modification - Petersen
	  		stockDataReturn = stockData2;
	  	//end modification - Petersen
			
	  } catch (Exception e) {
		  
		//begin modification - Petersen
    	// Just display error and handle next line
    	//Log.e(TAG, "decode error: " + e.toString());
    
		  
		//  if (decodeString[5].toString() == "N/A"){
		  
		  
		//begin modification - Petersen
			stockData.setSymbol("xxx");
	 		stockData.setPrice(0.0);
	 		stockData.setPercentileChange(0.0);
	 		stockData.setMaximum(0.0);
	 		stockData.setMinimum(0.0);
	 		stockData.setName("xxx");
				//end modification - Petersen
	 		
	 		
		  
		  		stockDataReturn = stockData;
		  		exceptionFlag = true;
		  		
		  		
		 // Log.d(TAG,  decodeString[5].toString()+ "3");
		 // }
		  	//	else
		  	//	{
		  		//	Log.d(TAG, decodeString[5].toString() + "4");
		  			
		  			//stockDataReturn = stockData2;
		  		//}
		  
		  		
		//end modification - Petersen
		  
    }	
	}
	
	public boolean startGettingDataFromYahoo(String[] symbols) {
		

		
	
 		
		boolean result = false;
		String[] decodeString = null;
		String szURL = getUrl(symbols);
		try {
			StockDataConnection stockDataConnection = new StockDataConnection(context);
			if (stockDataConnection.hasConnectivity()) {
				URL url = new URL(szURL);
				InputStream stream = url.openStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		    String line = null;
		    while ((line = reader.readLine()) != null) {
	        Log.d(TAG, "Received: " + line);   
	        decodeString = line.split(",", YAHOO_FLAGS.length);
	        addStockData(decodeString);
		    }
		    stream.close();
				result = true;
			} else {
				Log.d(TAG, "No connectivity available.");
				
				exceptionFlag2 = true;
			}
		} catch (Exception e) {
			
			
			//begin modification - Petersen
			//Log.e(TAG, "Cannot start parser for Input stream. Err= " + e.toString());
			//data.clear();
			//return result;
			
			
			stockData.setSymbol("xxx");
	    	stockData.setPrice(0.0);
	 		stockData.setPercentileChange(0.0);
	 		stockData.setMaximum(0.0);
	 		stockData.setMinimum(0.0);
	 		stockData.setName("xxx");
			
			exceptionFlag2 = true;
			
			stockDataReturn = stockData;
			//end modification - Petersen
	
		}	
		
		//begin modification - Petersen
		if(exceptionFlag == true || exceptionFlag2 == true){
			//Log.d(TAG, "flags");
			//end modification - Petersen
			
			result = false;
		}
			
		return result;
	}
	
	
	public int getStockDataCount() {
		return data.size();
	}
	
	public StockData getStockData() {
		
		//begin modification - Petersen
		//StockData stockData = null;
		

		//try {
			//stockData = data.get(index);
		//} catch (Exception e) {
			//Log.e(TAG, "getStockData: " + e.toString());
		//}	
		
	
		exceptionFlag = false;
		
		exceptionFlag2 = false;
		
		return stockDataReturn;
		//end modification - Petersen

	}
}