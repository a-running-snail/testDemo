package com.runningsnail.androiddemo.tulingrobot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.runningsnail.androiddemo.R;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class RobotActivity extends Activity implements OnClickListener,OnResultListener{
	private ListView lv;
	private Button send;
	private EditText et;
	private List<ChatInfo> lists = new ArrayList<>();
	private RobotAdapter adapter;
	private String input;
	private String[] array;
	private GetData getData;
	private double currentTime=0, oldTime = 0;
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_robot);
		initView();
		int index = (int) (Math.random() * (array.length - 1));
		String wel = array[index];
		ChatInfo info = new ChatInfo(wel,getTime(),ChatInfo.RECEIVE);
		lists.add(info);
		adapter = new RobotAdapter(RobotActivity.this, lists);
		lv.setAdapter(adapter);
		//new GetData("http://www.tuling123.com/openapi/api?key=6af9822f5491fadfc142b53818bbd63a&info=%E5%8C%97%E4%BA%AC").execute();
	}
	private void initView(){
		lv = (ListView) findViewById(R.id.robot_lv);
		send = (Button) findViewById(R.id.robot_bt);
		send.setOnClickListener(this);
		et = (EditText) findViewById(R.id.robot_et);
		et.addTextChangedListener(watcher);
		array = getResources().getStringArray(R.array.welcome_tips);
	}
	
	
	
	private TextWatcher watcher = new TextWatcher() {
		
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub
			if (!TextUtils.isEmpty(et.getText().toString())) {
				send.setEnabled(true);
			}else {
				send.setEnabled(false);
			}
		}
	};

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.robot_bt:
			input = et.getText().toString();
			et.setText("");
			String dropk = input.replace(" ", "");
			String droph = dropk.replace("\n", "");
			ChatInfo info = new ChatInfo(input,getTime(),ChatInfo.SEND);
			lists.add(info);
			if (lists.size() > 30) {
				for (int i = 0; i < 10; i++) {
					lists.remove(i);
				}
			}
			adapter.notifyDataSetChanged();
			getData = new GetData("http://www.tuling123.com/openapi/api?key=6af9822f5491fadfc142b53818bbd63a&info="+droph, this);
			getData.execute();
			break;

		default:
			break;
		}
	}
	private String getTime(){
		currentTime = System.currentTimeMillis();
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		Date curDate = new Date();
		String str = format.format(curDate);
		if (currentTime - oldTime >= 5*1000*60) {
			oldTime = currentTime;
			return str;
		} else {
			return "";
		}
	}
	@Override
	public void getResult(String result) {
		// TODO Auto-generated method stub
		try {
			JSONObject object = new JSONObject(result);
			String content = object.getString("text");
			ChatInfo info = new ChatInfo(content,getTime(),ChatInfo.RECEIVE);
			lists.add(info);
			adapter.notifyDataSetChanged();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
