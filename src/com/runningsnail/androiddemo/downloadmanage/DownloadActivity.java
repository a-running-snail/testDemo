package com.runningsnail.androiddemo.downloadmanage;

import java.io.File;


import com.runningsnail.androiddemo.R;

import android.app.Activity;
import android.app.DownloadManager;
import android.app.DownloadManager.Query;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.VideoView;

public class DownloadActivity extends Activity {

	private Button btn;
	private ProgressBar pro;
	private VideoView video;
	private DownloadManager downmaManager;
	private long lastDownloadId = 0;
	private String path;
	private DownloadObserver observer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_download);
		initView();
	}

	private void initView() {
		// TODO Auto-generated method stub
		btn = (Button) findViewById(R.id.btn_download);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Uri uri = Uri.parse("http://commonsware.com/misc/test.mp4");
				lastDownloadId = downmaManager.enqueue(new DownloadManager.Request(uri)
	               .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI)
	               .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "test.mp4")
	               .setAllowedOverRoaming(false)
	               .setDescription("测试")
	               .setTitle("测试视频")
			);
			}
		});
		pro = (ProgressBar) findViewById(R.id.progress);
		video = (VideoView) findViewById(R.id.video_view);
		path = Environment.getExternalStoragePublicDirectory(    
				Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
		File file = new File(path+File.separator+"test.mp4");
		if (file.exists()) {
			file.delete();
		}
		downmaManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
		observer = new DownloadObserver(myHandler);
		getContentResolver().registerContentObserver(Uri.parse("content://downloads/my_downloads"), true,observer);
		video.setMediaController(new MediaController(this));
		//播放完成回调
		video.setOnCompletionListener( new MyPlayerOnCompletionListener());
	 
	    //设置视频路径
		Log.e("tag", "----path="+path);
		video.setVideoURI(Uri.parse(path+File.separator+"test.mp4"));
		video.setVisibility(View.GONE);
	    //开始播放视频
		//video.start();
	}

	class DownloadObserver extends ContentObserver {

		public DownloadObserver(Handler handler) {
			super(handler);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onChange(boolean selfChange) {
			// TODO Auto-generated method stub
			super.onChange(selfChange);
			queryDownloadStatus();
		}

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		getContentResolver().unregisterContentObserver(observer);
	}

	private Handler myHandler = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.obj.equals("STATUS_RUNNING")) {
				pro.setMax(msg.arg2);
				pro.setProgress(msg.arg1);
			}else if (msg.obj.equals("STATUS_SUCCESSFUL")) {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				video.setVisibility(View.VISIBLE);
				video.start();
			}
		};
	};
	private void queryDownloadStatus(){
		Query query = new DownloadManager.Query();       
        query.setFilterById(lastDownloadId);       
        Cursor c = downmaManager.query(query);
        if (c != null) {
			while (c.moveToNext()) {
				//String title = c.getString(c.getColumnIndex(DownloadManager.COLUMN_TITLE));
				int cur = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
				int total = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));
				Log.e("tag", "-queryDownloadStatus---cur="+cur+"  total="+total+"  lastDownloadId="+lastDownloadId);
				//String desc = c.getString(c.getColumnIndex(DownloadManager.COLUMN_DESCRIPTION));
				int status = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS)); 
				switch (status) {
				case DownloadManager.STATUS_FAILED:
                    downmaManager.remove(lastDownloadId);
                    Toast.makeText(DownloadActivity.this, "下载失败", Toast.LENGTH_SHORT).show();
					break;
				case DownloadManager.STATUS_RUNNING:
					Message msg = Message.obtain();
					msg.obj = "STATUS_RUNNING";
					msg.arg1 = cur;
					msg.arg2 = total;
					myHandler.sendMessage(msg);
					break;
				case DownloadManager.STATUS_SUCCESSFUL:
					Message msgs = Message.obtain();
					msgs.obj = "STATUS_SUCCESSFUL";
					myHandler.sendMessage(msgs);
					Toast.makeText(DownloadActivity.this, "下载成功", Toast.LENGTH_SHORT).show();
					break;

				default:
					break;
				}
			}
		}
	}
	class MyPlayerOnCompletionListener implements MediaPlayer.OnCompletionListener {
		 
	    @Override
	    public void onCompletion(MediaPlayer mp) {
	      Toast.makeText( DownloadActivity.this, "播放完成了", Toast.LENGTH_SHORT).show();
	    }
	  }
}
