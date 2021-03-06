package com.runningsnail.androiddemo.tulingrobot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SynthesizerListener;
import com.iflytek.cloud.util.ResourceUtil;
import com.iflytek.cloud.util.ResourceUtil.RESOURCE_TYPE;
import com.iflytek.mscv5plusdemo.TtsDemo;
import com.iflytek.speech.setting.TtsSettings;
import com.runningsnail.androiddemo.R;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class RobotActivity extends Activity implements OnClickListener,OnResultListener{
	private ListView lv;
	private Button send;
	private EditText et;
	private List<ChatInfo> lists = new ArrayList<>();
	private RobotAdapter adapter;
	// 语音合成对象
	private SpeechSynthesizer mTts;
	private Toast mToast;
	private String input;
	private String[] array;
	private GetData getData;
	private SharedPreferences mSharedPreferences;
	private double currentTime=0, oldTime = 0;
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_robot);
		initView();
		// 初始化合成对象
		mTts = SpeechSynthesizer.createSynthesizer(this, mTtsInitListener);
		mToast = Toast.makeText(this,"",Toast.LENGTH_SHORT);
		mSharedPreferences = getSharedPreferences(TtsSettings.PREFER_NAME, Activity.MODE_PRIVATE);
		int index = (int) (Math.random() * (array.length - 1));
		String wel = array[index];
		setParam();
		int code = mTts.startSpeaking(wel, mTtsListener);
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
			int code = mTts.startSpeaking(droph, mTtsListener);
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
			int code = mTts.startSpeaking(content, mTtsListener);
			ChatInfo info = new ChatInfo(content,getTime(),ChatInfo.RECEIVE);
			lists.add(info);
			adapter.notifyDataSetChanged();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 初始化监听。
	 */
	private InitListener mTtsInitListener = new InitListener() {
		@Override
		public void onInit(int code) {
			//Log.d(TAG, "InitListener init() code = " + code);
			if (code != ErrorCode.SUCCESS) {
        		showTip("初始化失败,错误码："+code);
        	} else {
				// 初始化成功，之后可以调用startSpeaking方法
        		// 注：有的开发者在onCreate方法中创建完合成对象之后马上就调用startSpeaking进行合成，
        		// 正确的做法是将onCreate中的startSpeaking调用移至这里
			}		
		}
	};
	private void showTip(final String str){
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				mToast.setText(str);
				mToast.show();
			}
		});
	}
	/**
	 * 参数设置
	 * @param param
	 * @return 
	 */
	private void setParam(){
		// 清空参数
		mTts.setParameter(SpeechConstant.PARAMS, null);
		//设置合成
		
			//设置使用本地引擎
		mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_LOCAL);
			//设置发音人资源路径
		mTts.setParameter(ResourceUtil.TTS_RES_PATH,getResourcePath());
			//设置发音人
		mTts.setParameter(SpeechConstant.VOICE_NAME,"xiaoyan");
		
		//设置合成语速
		mTts.setParameter(SpeechConstant.SPEED, mSharedPreferences.getString("speed_preference", "50"));
		//设置合成音调
		mTts.setParameter(SpeechConstant.PITCH, mSharedPreferences.getString("pitch_preference", "50"));
		//设置合成音量
		mTts.setParameter(SpeechConstant.VOLUME, mSharedPreferences.getString("volume_preference", "50"));
		//设置播放器音频流类型
		mTts.setParameter(SpeechConstant.STREAM_TYPE, mSharedPreferences.getString("stream_preference", "3"));
		
		// 设置播放合成音频打断音乐播放，默认为true
		mTts.setParameter(SpeechConstant.KEY_REQUEST_FOCUS, "true");
		
		// 设置音频保存路径，保存音频格式支持pcm、wav，设置路径为sd卡请注意WRITE_EXTERNAL_STORAGE权限
		// 注：AUDIO_FORMAT参数语记需要更新版本才能生效
		mTts.setParameter(SpeechConstant.AUDIO_FORMAT, "wav");
		mTts.setParameter(SpeechConstant.TTS_AUDIO_PATH, Environment.getExternalStorageDirectory()+"/msc/tts.wav");
	}
	//获取发音人资源路径
		private String getResourcePath(){
			StringBuffer tempBuffer = new StringBuffer();
			//合成通用资源
			tempBuffer.append(ResourceUtil.generateResourcePath(this, RESOURCE_TYPE.assets, "tts/common.jet"));
			tempBuffer.append(";");
			//发音人资源
			tempBuffer.append(ResourceUtil.generateResourcePath(this, RESOURCE_TYPE.assets, "tts/"+TtsDemo.voicerLocal+".jet"));
			return tempBuffer.toString();
		}
		private SynthesizerListener mTtsListener = new SynthesizerListener() {
			
			@Override
			public void onSpeakBegin() {
				showTip("开始播放");
			}

			@Override
			public void onSpeakPaused() {
				showTip("暂停播放");
			}

			@Override
			public void onSpeakResumed() {
				showTip("继续播放");
			}

			@Override
			public void onBufferProgress(int percent, int beginPos, int endPos,
					String info) {
				// 合成进度
				/*mPercentForBuffering = percent;
				showTip(String.format(getString(R.string.tts_toast_format),
						mPercentForBuffering, mPercentForPlaying));*/
			}

			@Override
			public void onSpeakProgress(int percent, int beginPos, int endPos) {
				// 播放进度
				/*mPercentForPlaying = percent;
				showTip(String.format(getString(R.string.tts_toast_format),
						mPercentForBuffering, mPercentForPlaying));*/
			}

			@Override
			public void onCompleted(SpeechError error) {
				/*if (error == null) {
					showTip("播放完成");
				} else if (error != null) {
					showTip(error.getPlainDescription(true));
				}*/
			}

			@Override
			public void onEvent(int eventType, int arg1, int arg2, Bundle obj) {
				// 以下代码用于获取与云端的会话id，当业务出错时将会话id提供给技术支持人员，可用于查询会话日志，定位出错原因
				// 若使用本地能力，会话id为null
				//	if (SpeechEvent.EVENT_SESSION_ID == eventType) {
				//		String sid = obj.getString(SpeechEvent.KEY_EVENT_SESSION_ID);
				//		Log.d(TAG, "session id =" + sid);
				//	}
			}
		};
		protected void onDestory() {
			if( null != mTts ){
				mTts.stopSpeaking();
				// 退出时释放连接
				mTts.destroy();
			}
		};
}
