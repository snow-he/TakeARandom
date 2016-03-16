package com.snow.shuijichouqian;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class numberinfo {
	public Context mcontext;
	numberinfo(Context context){
		mcontext = context;
	}
	
	public String[] getSharedPreference(String key) {
		String regularEx = "#";
		String[] str = null;
		SharedPreferences sp = mcontext.getSharedPreferences("data",
				Context.MODE_PRIVATE);
		String values;
		values = sp.getString(key, "");
		str = values.split(regularEx);

		return str;
	}

	public void setSharedPreference(String key, String[] values) {
		String regularEx = "#";
		String str = "";
		SharedPreferences sp = mcontext.getSharedPreferences("data",
				Context.MODE_PRIVATE);
		if (values != null && values.length > 0) {
			for (String value : values) {
				str += value;
				str += regularEx;
			}
			Editor et = sp.edit();
			et.putString(key, str);
			et.commit();
		}
	}
}
