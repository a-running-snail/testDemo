package com.runningsnail.androiddemo.contentprovoder;

import com.runningsnail.androiddemo.R;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ProviderActivity extends Activity implements OnClickListener{
	
	private EditText et_name,et_age;
	private Button write,read;
	private TextView result;
	private String name,age;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contentprovider);
		initView();
	}

	private void initView() {
		// TODO Auto-generated method stub
		et_name = (EditText) findViewById(R.id.et_name);
		et_name.addTextChangedListener(watcher);
		et_age = (EditText) findViewById(R.id.et_age);
		et_age.addTextChangedListener(watcher);
		write = (Button) findViewById(R.id.write);
		write.setOnClickListener(this);
		read = (Button) findViewById(R.id.read);
		read.setOnClickListener(this);
		result = (TextView) findViewById(R.id.tv_result);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.write:
			name = et_name.getText().toString();
			age = et_age.getText().toString();
			ContentValues values = new ContentValues();
			values.put("name", name);
			values.put("age", age);
			Cursor cursors = getContentResolver().query(MyProvider.URI, null, "name=?", new String[]{name}, null);
			if (cursors != null) {
				if (cursors.getCount()> 0) {
					getContentResolver().update(MyProvider.URI, values, "name=?", new String[]{name});
					Toast.makeText(ProviderActivity.this, "已经存在", Toast.LENGTH_SHORT).show();
				}else {
					getContentResolver().insert(MyProvider.URI, values);
					Toast.makeText(ProviderActivity.this, "bu存在", Toast.LENGTH_SHORT).show();
				}
				cursors.close();
			}else {
				Toast.makeText(ProviderActivity.this, "不存在", Toast.LENGTH_SHORT).show();
				getContentResolver().insert(MyProvider.URI, values);
			}
			
			break;
		case R.id.read:
			Cursor cursor = getContentResolver().query(MyProvider.URI, null, null, null, null);
			if(cursor != null){
				StringBuilder builder = new StringBuilder();
				while (cursor.moveToNext()) {
					String name = cursor.getString(cursor.getColumnIndex("name"));
					String age = cursor.getString(cursor.getColumnIndex("age"));
					builder.append(" name :"+ name+" age :"+age+" \n");
				}
				result.setText(builder.toString());
				cursor.close();
			}
			
			break;

		default:
			break;
		}
	}
	private TextWatcher watcher = new TextWatcher() {
		
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,int after) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub
			if (!TextUtils.isEmpty(et_name.getText().toString())&& !TextUtils.isEmpty(et_age.getText().toString()) ) {
				write.setEnabled(true);
			}else {
				write.setEnabled(false);
			}
		}
	};

}
