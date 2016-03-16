package com.snow.shuijichouqian;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class RandomChoose1 extends Activity {
	public ListView resultlv;
	public Button startchoosebt;
	public Button startchoosebt2;
	private List<String> resultdata = new ArrayList<String>();
	private List<String> resultdata2 = new ArrayList<String>();
	private List<Integer> choosedresult = new ArrayList<Integer>();
	private int choosecount = 0;
	public int playernum;
	public int prchoosenum;
	public String choosetype;
	public numberinfo numinfo;
	public String[] infostr = new String[1];
	public String hasinfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.random1_layout);
		resultlv = (ListView) findViewById(R.id.resultlist);
		startchoosebt = (Button) findViewById(R.id.startchoose);

		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		playernum = Integer.parseInt(bundle.getString("num"));
		choosetype = bundle.getString("type");
		numinfo = new numberinfo(this);
		infostr = numinfo.getSharedPreference("nums");
		hasinfo = bundle.getString("hasinfo");

		startchoosebt.setOnClickListener(startchoosebtclick);
	}

	public OnClickListener startchoosebtclick = new OnClickListener() {

		@Override
		public void onClick(View v) {

			if (choosetype.equals("repeat")) {
				prchoosenum = ((int) (Math.random() * playernum) + 1);
				resultdata
						.add(getResources().getString(R.string.choosedorder)
								+ (choosecount + 1)
								+ getResources().getString(
										R.string.choosedordermessage)
								+ prchoosenum
								+ (hasinfo.equals("true") ? (":" + infostr[prchoosenum])
										: ""));
				setListView(resultdata);
				choosecount++;
				dialog(prchoosenum);

			} else {
				if (choosecount == playernum - 1) {
					Toast.makeText(RandomChoose1.this, R.string.choosedall,
							Toast.LENGTH_SHORT).show();
				} else {
					getchoosenum();
					resultdata2
							.add(getResources()
									.getString(R.string.choosedorder)
									+ (choosecount + 1)
									+ getResources().getString(
											R.string.choosedordermessage)
									+ prchoosenum
									+ (hasinfo.equals("true") ? (":" + infostr[prchoosenum])
											: ""));
					choosedresult.add(prchoosenum);
					dialog(prchoosenum);
					setListView(resultdata2);
					choosecount++;
					if (choosecount == playernum - 1) {
						getchoosenum();
						resultdata2
								.add(getResources().getString(
										R.string.choosedorder)
										+ (choosecount + 1)
										+ getResources().getString(
												R.string.choosedordermessage)
										+ prchoosenum
										+ (hasinfo.equals("true") ? (":" + infostr[prchoosenum])
												: ""));
						choosedresult.add(prchoosenum);
						setListView(resultdata2);
					}
				}
			}

		}
	};

	protected void dialog(int resultvalue) {
		AlertDialog.Builder builder = new Builder(this);
		builder.setMessage(getResources().getString(R.string.resultinfo)
				+ resultvalue);
		builder.setTitle(getResources().getString(R.string.result));
		builder.setNegativeButton(getResources().getString(R.string.ok),
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
		builder.create().show();
	}

	public void getchoosenum() {
		prchoosenum = ((int) (Math.random() * playernum) + 1);
		if (!choosedresult.isEmpty()) {
			for (Integer i : choosedresult) {
				if (i.equals(prchoosenum)) {
					getchoosenum();
				}
			}
		}
	}

	private void setListView(List<String> data) {
		resultlv.setAdapter(null);
		resultlv.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_expandable_list_item_1, data));
		resultlv.setSelection(resultlv.getBottom());
	}
}
