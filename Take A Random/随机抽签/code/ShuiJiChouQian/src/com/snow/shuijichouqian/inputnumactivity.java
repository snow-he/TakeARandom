package com.snow.shuijichouqian;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherAdView;



public class inputnumactivity extends Activity {

	public Button nextbt;
	public EditText inputnumet;
	//admob
	public PublisherAdView mAdView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inputnum_main);
		
		nextbt = (Button)findViewById(R.id.inputok1);
		inputnumet = (EditText)findViewById(R.id.playernuminput);
		nextbt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(inputnumet.length() == 0){
					Toast.makeText(getApplicationContext(),
							getResources().getString(R.string.inputnotnull),
							Toast.LENGTH_SHORT).show();
					return;
				}
				Intent intent = new Intent();
				intent.putExtra("inputnum", inputnumet.getText().toString());
				intent.setClass(inputnumactivity.this,
						ShuiJiChouQianMainActivity.class);
				startActivity(intent);
				
			}
		});
		showadmobad();
	}
	
	public void showadmobad(){
        mAdView = (PublisherAdView) findViewById(R.id.ad_view);
        PublisherAdRequest adRequest = new PublisherAdRequest.Builder().build();
        mAdView.loadAd(adRequest);
	}
}
