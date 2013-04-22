/*
	Copyright 2010 Jonathan Gonz‡lez (jonathan@jonbaraq.eu).

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

package net.dmpetersen.androiddevelopment.StockCalculatorAds;

//package hykwokgonzales;

import hykwokgonzales.StockData;

import java.io.InputStream;
import java.net.URL;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class StockViewActivity extends Activity {
  // Debug tag for LogCat.
	private static final String TAG = "SPV:StockViewActivity";
	
	private static final String TODAY = "Today";
	private static final String WEEK = "1 week";
	private static final String YEAR = "1 year";
	String chartUrl = "http://ichart.finance.yahoo.com/t?s=";
	private static final String[] TIME_RANGES = { TODAY, WEEK, YEAR};
	private static final String CHART_URL_TODAY = "http://ichart.finance.yahoo.com/t?s=";
	private static final String CHART_URL_FIVE_DAYS = "http://ichart.finance.yahoo.com/v?s=";
	private static final String CHART_URL_ONE_YEAR = "http://chart.finance.yahoo.com/c/bb/m/";
	
	private static int NAME_MAX_LENGTH = 10;
	
	// Controls.
	private ImageView chart;
	private TextView textViewSymbol;
	private TextView textViewStockName;
	private TextView textViewStockPrice;
	private TextView textViewChange;
	private TextView textViewMaximum;
	private TextView textViewMinimum;
	private Spinner timeSpinner;
	

	/** Called when the activity is first created. */
	@SuppressWarnings("unchecked")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		Log.d(TAG, "Launching new Activity!");
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.stock_view_activity);
	  StockData stockData = getStockDataFromContext();
	  
	  // Get Controls.
	  textViewSymbol = (TextView) findViewById(R.id.symbol_register);
	  textViewStockName = (TextView) findViewById(R.id.stock_label_register);
	  textViewStockPrice = (TextView) findViewById(R.id.value_register);
	  textViewChange = (TextView) findViewById(R.id.difference_register);
	  textViewMaximum = (TextView) findViewById(R.id.maximum_value_register);
	  textViewMinimum = (TextView) findViewById(R.id.minimum_value_register);
	  
	  chart = (ImageView) findViewById(R.id.ImageView01);
	  
	  int numberChars = NAME_MAX_LENGTH;
	  if (stockData.getName().length() < NAME_MAX_LENGTH) {
	  	numberChars = stockData.getName().length();
	  }
	  // Set Controls values.
	  textViewSymbol.setText(stockData.getSymbol());
	  textViewStockName.setSingleLine();
	  
	  textViewStockName.setText(stockData.getName().substring(0, numberChars));
	  textViewStockPrice.setText(Double.toString(stockData.getPrice()));
	  textViewChange.setText(Double.toString(stockData.getPercentileChange()) + "%");
	  if (stockData.getPercentileChange() > 0) {
	  	textViewChange.setTextColor(Color.GREEN);
	  } else {
	  	textViewChange.setTextColor(Color.RED);
	  }
	  textViewMaximum.setText(Double.toString(stockData.getMaximum()));
	  textViewMinimum.setText(Double.toString(stockData.getMinimum()));
	  Drawable drawable = loadImageFromWebOperations(CHART_URL_TODAY + stockData.getSymbol());
	  Log.d(TAG, "Image downloaded");
	  
	  // Spinner
	  timeSpinner = (Spinner)findViewById(R.id.SpinnerTime);
	  ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.time_range, 
	  		android.R.layout.simple_spinner_item);
	  adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	  timeSpinner.setAdapter(adapter);
	  timeSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
      public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        int index = timeSpinner.getSelectedItemPosition();
        String timeRangeSelected = TIME_RANGES[index]; 
        final CharSequence title = getString(R.string.Dialog_Progress_Title);
				final CharSequence message = getString(R.string.Dialog_Progress_Message);
				ProgressDialog progressDialog = ProgressDialog.show(StockViewActivity.this, title, message, true);
        if (timeRangeSelected.equals(TODAY)) {
        	Drawable drawable = loadImageFromWebOperations(CHART_URL_TODAY 
        			+ textViewSymbol.getText().toString());
        	chart.setImageDrawable(drawable);	
        	progressDialog.dismiss();
        } else if (timeRangeSelected.equals(WEEK)) {	
        	Drawable drawable = loadImageFromWebOperations(CHART_URL_FIVE_DAYS 
        			+ textViewSymbol.getText().toString());
        	chart.setImageDrawable(drawable);
        	progressDialog.dismiss();
        } else if (timeRangeSelected.equals(YEAR)) {
        	Drawable drawable = loadImageFromWebOperations(CHART_URL_ONE_YEAR 
        			+ textViewSymbol.getText().toString());
        	chart.setImageDrawable(drawable);	
        	progressDialog.dismiss();
        }
      }

      public void onNothingSelected(AdapterView<?> arg0) { 
      }
  });
	  chart.setImageDrawable(drawable);
	} 

	private StockData getStockDataFromContext() {
		Bundle bundle = this.getIntent().getExtras();
	  StockData stockData = new StockData();
	  stockData.setSymbol(bundle.getString("Symbol"));
	  stockData.setName(bundle.getString("SymbolName"));
	  stockData.setPrice(bundle.getDouble("Price"));
	  stockData.setPercentileChange(bundle.getDouble("Change"));
	  stockData.setMaximum(bundle.getDouble("High"));
	  stockData.setMinimum(bundle.getDouble("Low"));
	  return stockData;
	}
	
  private Drawable loadImageFromWebOperations(String url) {
	  Log.d(TAG, "Downloading " + url);
	  try {
	  	URL chartUrl = new URL(url);
	    InputStream is = chartUrl.openStream();
	    Drawable d = Drawable.createFromStream(is, "src name");
	    return d;
	  } catch (Exception e) {
	    Log.d(TAG, "Exception = "+e.getMessage());
	    return null;
	  }
	}
}