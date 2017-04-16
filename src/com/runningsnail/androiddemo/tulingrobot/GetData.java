package com.runningsnail.androiddemo.tulingrobot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class GetData extends AsyncTask<String, Void, String> {

	private String url;
	private OnResultListener listener;
	public GetData(String url,OnResultListener listener){
		this.url = url;
		this.listener = listener;
	}
	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		try {
			
			HttpClient httpClient = new DefaultHttpClient();
			HttpGet get = new HttpGet(url);
			HttpResponse response = httpClient.execute(get);
			HttpEntity entity = response.getEntity();
			InputStream io = entity.getContent();
			BufferedReader buffer = new BufferedReader(new InputStreamReader(io));
			String data;
			StringBuilder builder = new StringBuilder();
			while ((data = buffer.readLine()) != null) {
				builder.append(data);
			}
			buffer.close();
			io.close();
			return builder.toString();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		if (listener !=  null) {
			listener.getResult(result);
		}
		super.onPostExecute(result);
	}

}
