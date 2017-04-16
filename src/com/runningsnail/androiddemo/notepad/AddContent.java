package com.runningsnail.androiddemo.notepad;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.runningsnail.androiddemo.R;
import com.runningsnail.androiddemo.application.MyApplication;
import com.runningsnail.androiddemo.tools.NoteLog;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

public class AddContent extends Activity implements OnClickListener{

	
	private ImageView img;
	private VideoView video;
	private EditText eText;
	private Button savebtn,clbtn;
	private int tag;
	private File imgfile,imgtempfile,videoFile;
	private String tempContent,tempImg,tempVideo,temptime;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addcontent);
		initView();
		
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.addsave:
			saveData();
			break;
        case R.id.addcancle:
			AddContent.this.finish();
			break;

		default:
			break;
		}
	}
	private void saveData(){
		String content = eText.getText().toString();
		NoteLog.e("","--addsave----content="+content+"  tempContent="+tempContent);
		if(!TextUtils.isEmpty(content)&& !content.equals(tempContent)){//
			if(TextUtils.isEmpty(tempContent)){//
				NoteLog.e("","--addsave----content="+content+"---isEmpty--tempContent---");
				SQLiteUtils sqlite = new SQLiteUtils();
				NoteDB dbHelper = sqlite.getDBinstance(AddContent.this);
				NoteInfo user = new NoteInfo();
				user.setContent(content);
				long curtime = System.currentTimeMillis();
				user.setDatetime(String.valueOf(curtime));
				user.setTime(getCurtime(curtime));
				if(imgfile !=null && imgfile.exists()){
					NoteLog.e("","--addsave----content="+content+"---imgfile.exists()---");
					user.setImg(imgfile.getAbsolutePath());
					user.setTempimg(imgtempfile.getAbsolutePath());
				}
				if(videoFile !=null && videoFile.exists()){
					user.setVideo(videoFile.getAbsolutePath());
				}
				sqlite.insert(dbHelper, user);
			}else {//�޸�
				SQLiteUtils sqlite = new SQLiteUtils();
				NoteDB dbHelper = sqlite.getDBinstance(AddContent.this);
				String datatime = sqlite.query(dbHelper,tempContent);
				NoteLog.e("","--addsave-----------id="+datatime+"  tempContent="+tempContent);
				NoteInfo user = new NoteInfo();
				user.setContent(content);
				long curtime = System.currentTimeMillis();
				user.setDatetime(String.valueOf(curtime));
				user.setTime(getCurtime(curtime));
				user.setImg(tempImg);
				user.setVideo(tempVideo);
				sqlite.update(dbHelper, datatime, user);
			}
		}
		AddContent.this.finish();
	}
	private void initView() {
		img = (ImageView) findViewById(R.id.addimg);
		video = (VideoView) findViewById(R.id.addvideo);
		eText = (EditText) findViewById(R.id.addtext);
		eText.addTextChangedListener(watcher);
		savebtn = (Button) findViewById(R.id.addsave);
		clbtn = (Button) findViewById(R.id.addcancle);
		savebtn.setOnClickListener(this);
		clbtn.setOnClickListener(this);
		tag = getIntent().getIntExtra("tag", 0);
		tempContent = getIntent().getStringExtra("content");
		tempImg = getIntent().getStringExtra("img");
		tempVideo = getIntent().getStringExtra("video");
		NoteLog.d("------getStringExtra----tempImg="+tempImg);
		NoteLog.d("------getStringExtra----tempVideo="+tempVideo);
		temptime = getIntent().getStringExtra("time");
		eText.setText(tempContent);
		eText.setSelection(tempContent.length()); 
		
		if(tag == 0){
			img.setVisibility(View.GONE);
			video.setVisibility(View.GONE);
		}else if (tag == 1) {
			img.setVisibility(View.VISIBLE);
			video.setVisibility(View.GONE);
			NoteLog.d("------getStringExtra----tag == 1------="+tempImg);
			if(!TextUtils.isEmpty(tempImg)){
				Bitmap bitmap = BitmapFactory.decodeFile(tempImg);
				img.setImageBitmap(bitmap);
			}else {
				showDialog(AddContent.this);
			}
		}else if (tag == 2) {
			img.setVisibility(View.GONE);
			video.setVisibility(View.VISIBLE);
			if(!TextUtils.isEmpty(tempVideo)){
				video.setVideoURI(Uri.parse(tempVideo));
				video.start();
			}else {
				Intent video = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
				videoFile = new File(MyApplication.PICPATH+String.valueOf(System.currentTimeMillis()) + ".mp4");
				video.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(videoFile));
				startActivityForResult(video, 4);
			}
		}
	}
	private TextWatcher watcher = new TextWatcher() {
	    
	    @Override
	    public void onTextChanged(CharSequence s, int start, int before, int count) {
	    }
	    
	    @Override
	    public void beforeTextChanged(CharSequence s, int start, int count,int after) {
	    }
	    
	    @Override
	    public void afterTextChanged(Editable s) {
	        if(TextUtils.isEmpty(eText.getText().toString().trim())){
	        	savebtn.setEnabled(false);
	        }else {
	        	savebtn.setEnabled(true);
			}
	    }
	};
	private String getCurtime(long time){
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日   HH:mm:ss");
		Date curDate =  new Date(time); 
		return  format.format(curDate);
	}
	private void showDialog(Context mContext){
		AlertDialog.Builder build = new AlertDialog.Builder(mContext);
		final AlertDialog dialog = build.create();
		View view = LayoutInflater.from(mContext).inflate(R.layout.dialogview, null);
	    TextView photo = (TextView) view.findViewById(R.id.photo);
	    TextView camera = (TextView) view.findViewById(R.id.camera);
	    photo.setOnClickListener(new OnClickListener() {//���ѡȡ
			
			@Override
			public void onClick(View v) {
				Intent it = new Intent(Intent.ACTION_PICK, null);
				it.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(it, 3);
                dialog.dismiss();
			}
		});
	    camera.setOnClickListener(new OnClickListener() {//���
			
			@Override
			public void onClick(View v) {
				Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				imgtempfile = new File(MyApplication.PICPATH+String.valueOf(System.currentTimeMillis())+".jpg");
				it.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imgtempfile));
				startActivityForResult(it, 1);
				dialog.dismiss();
			}
		});
	    dialog.setView(view);
	    dialog.show();
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == 1){
			if (resultCode == RESULT_OK) {
                cropPhoto(Uri.fromFile(imgtempfile));// �ü�ͼƬ
			}
		}else if (requestCode == 2) {
			if (data != null) {
                Bundle extras = data.getExtras();
                Bitmap head = extras.getParcelable("data");
                if (head != null) {
                    /**
                     * �ϴ�����������
                     */
                    setPicToView(head);// ������SD����
                    img.setImageBitmap(head);// ��ImageView��ʾ����
                }
            }
		}else if(requestCode == 3) {
			cropPhoto(data.getData());// �ü�ͼƬ
		}else if (requestCode == 4) {
			video.setVideoURI(Uri.fromFile(videoFile));
			video.start();
		}
		
	}
	 /**
     * ����ϵͳ�Ĳü�����
     * 
     * @param uri
     */
    public void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY �ǿ�ߵı���
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY �ǲü�ͼƬ���
        intent.putExtra("outputX", 200);
        intent.putExtra("outputY", 200);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 2);
    }
    private void setPicToView(Bitmap mBitmap) {
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // ���sd�Ƿ����
            return;
        }
        FileOutputStream b = null;
        imgfile = new File(MyApplication.PICPATH+String.valueOf(System.currentTimeMillis())+".jpg");
        try {
            b = new FileOutputStream(imgfile.getAbsoluteFile());
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// �����д���ļ�
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                // �ر���
                b.flush();
                b.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
