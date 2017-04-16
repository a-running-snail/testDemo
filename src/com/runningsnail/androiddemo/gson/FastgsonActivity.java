package com.runningsnail.androiddemo.gson;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.runningsnail.androiddemo.R;
import com.tandong.swichlayout.SwichLayoutInterFace;
import com.tandong.swichlayout.SwitchLayout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

@SuppressLint("NewApi")
public class FastgsonActivity extends Activity implements SwichLayoutInterFace{

	
	private String url = "https://api.douban.com/v2/book/1220562";
	private StringRequest request;
	private RequestQueue queue;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fastgson);
		request = new StringRequest(url, new Response.Listener<String>() {

			@Override
			public void onResponse(String result) {
				// TODO Auto-generated method stub
				//Log.e("tag", "-----------onRespon--------result = "+result);
				gsonData(result);
			}
		}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		queue = Volley.newRequestQueue(getApplicationContext());
		queue.add(request);
		setEnterSwichLayout();
	}
	private void gsonData(String result){
		/*
		 * gson瑙ｆ瀽
		 */
		//Gson gson = new Gson();
		//Book book = gson.fromJson(result, Book.class);
		/*
		 * fastgson瑙ｆ瀽list涓巓bject
		 */
		Book book = JSON.parseObject(result, Book.class);
		/*try {
			JSONObject object = new JSONObject(result);
			ArrayList<Tag>  tags = (ArrayList<Tag>) JSON.parseArray(object.getString("tags"), Tag.class);
			Log.e("tag", "-------tags="+tags.size());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		//Log.e("tag", "---------gsonData-------publisher="+book.getPublisher()
		//		+" \n title="+book.getTitle()+" \n summary="+book.getSummary()
		//		+" \n price="+book.getPrice()+" \n numRaters="+book.getRating().getNumRaters()
		//		+" \n large="+book.getImages().getLarge()+" \n tags="+book.getTags().size());
	}
	@Override
	public void setEnterSwichLayout() {
		// TODO Auto-generated method stub
		SwitchLayout.RotateCenterOut(this, false, null);
	}


	@Override
	public void setExitSwichLayout() {
		// TODO Auto-generated method stub
		SwitchLayout.RotateCenterOut(this, true, null);
	}
	public boolean onKeyDown(int keyCode, KeyEvent event) {// 按返回键时退出Activity的Activity特效动画

		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			setExitSwichLayout();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
