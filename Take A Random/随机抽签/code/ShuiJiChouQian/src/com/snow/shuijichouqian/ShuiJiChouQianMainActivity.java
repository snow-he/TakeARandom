package com.snow.shuijichouqian;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class ShuiJiChouQianMainActivity extends Activity {

	public EditText nameinfoinput;
	public Button startbt1, startbt2, addinfobt;
	public CheckBox sw;
	public ListView infolist;
	public numberinfo numinfo;
	public String[] infostr = new String[1];
	public int inputinfocount = 0;
	public int inputnumber;
	private List<String> inputdata = new ArrayList<String>();
	String inputnum;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shui_ji_chou_qian_main);

		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		inputnum = bundle.getString("inputnum");
		
		nameinfoinput = (EditText) findViewById(R.id.nameinfoinput);
		startbt1 = (Button) findViewById(R.id.choosebt1);
		startbt2 = (Button) findViewById(R.id.choosebt2);
		addinfobt = (Button) findViewById(R.id.addbt);
		sw = (CheckBox) findViewById(R.id.checkBox1);
		infolist = (ListView) findViewById(R.id.inputlist);
		numinfo = new numberinfo(this);

		startbt1.setOnClickListener(startbt1click);
		startbt2.setOnClickListener(startbt1click);
		addinfobt.setOnClickListener(addbt1click);
		sw.setOnCheckedChangeListener(checkclick);
		infolist.setOnItemLongClickListener(itemlongclick);

	}

	public OnItemLongClickListener itemlongclick = new OnItemLongClickListener() {
		@Override
		public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
				int arg2, long arg3) {
			// TODO Auto-generated method stub
			return false;
		}
	};
	
	public OnClickListener startbt1click = new OnClickListener() {

		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			inputnumber = Integer.parseInt(inputnum);
			if (inputnumber <= 1) {
				Toast.makeText(getApplicationContext(),
						getResources().getString(R.string.inputnotice),
						Toast.LENGTH_SHORT).show();
			} else {
				if (v.getId() == R.id.choosebt1) {
					intent.putExtra("type", "repeat");
				} else if (v.getId() == R.id.choosebt2) {
					intent.putExtra("type", "no-repeat");
				}
				intent.putExtra("num", inputnum);
				if(sw.isChecked()){
					intent.putExtra("hasinfo", "true");
					if(inputinfocount < inputnumber){
						Toast.makeText(getApplicationContext(), getResources().getString(R.string.addnotenough), Toast.LENGTH_SHORT).show();
						return;
					}
				}else{
					intent.putExtra("hasinfo", "false");
				}
				intent.setClass(ShuiJiChouQianMainActivity.this,
						RandomChoose1.class);
				startActivity(intent);

			}
		}
	};

	public OnClickListener addbt1click = new OnClickListener() {

		@Override
		public void onClick(View v) {
			if(sw.isChecked()){
				inputnumber = Integer.parseInt(inputnum);
				if(inputinfocount == inputnumber){
					Toast.makeText(getApplicationContext(), getResources().getString(R.string.addenough),Toast.LENGTH_SHORT).show();
				}else{
					String inputstr = nameinfoinput.getText().toString();
					infostr = insert(infostr, inputstr);
					numinfo.setSharedPreference("nums", infostr);
					inputdata.add(inputstr);
					setListView(inputdata);
					nameinfoinput.setText(null);
					inputinfocount++;
					Toast.makeText(getApplicationContext(), getResources().getString(R.string.addsuccess),Toast.LENGTH_SHORT).show();
				}
			}
		}
	};

	private static String[] insert(String[] arr, String str) {
		int size = arr.length;
		String[] tmp = new String[size + 1];
		System.arraycopy(arr, 0, tmp, 0, size);
		tmp[size] = str;
		return tmp;
	}

	private void setListView(List<String> data) {
		infolist.setAdapter(null);
		infolist.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_expandable_list_item_1, data));
		infolist.setSelection(infolist.getBottom());  
	}
	
	public OnCheckedChangeListener checkclick = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			// TODO Auto-generated method stub
			if (isChecked) {
				addinfobt.setVisibility(View.VISIBLE);
				nameinfoinput.setVisibility(View.VISIBLE);
				infolist.setVisibility(View.VISIBLE);
			} else {
				addinfobt.setVisibility(View.GONE);
				nameinfoinput.setVisibility(View.GONE);
				infolist.setVisibility(View.GONE);
			}
		}
	};

}
