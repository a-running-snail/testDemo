package com.runningsnail.androiddemo.contacts;

import java.util.ArrayList;
import java.util.List;

import com.runningsnail.androiddemo.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.widget.ListView;

@SuppressLint("NewApi")
public class ContactActivity extends Activity {
	private ListView lv;
	private List<Userinfo> list = new ArrayList<>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact);
		list = getData();
		lv = (ListView) findViewById(R.id.contact_lv);
		lv.setAdapter(new ContactAdapter(ContactActivity.this, list));
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	private List<Userinfo> getData() {
		// TODO Auto-generated method stub
		list.clear();
		Cursor cursor = getContentResolver().query(Phone.CONTENT_URI, null, null, null, null);
		if (cursor != null) {
			while (cursor.moveToNext()) {
				String num = cursor.getString(cursor.getColumnIndex(Phone.NUMBER));
				String name = cursor.getString(cursor.getColumnIndex(Phone.DISPLAY_NAME));
			    Userinfo userinfo = new Userinfo();
			    userinfo.setName(name);
			    userinfo.setNum(num);
			    list.add(userinfo);
			}
		}
		return list;
	}
}
