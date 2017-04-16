package com.runningsnail.androiddemo.notepad;

import java.util.ArrayList;

import com.runningsnail.androiddemo.R;
import com.runningsnail.androiddemo.tools.NoteLog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("NewApi")
public class MyAdapter extends BaseAdapter {

	private Cursor cursor;
	private Context mContext;
	public ArrayList<Boolean> boollist;
	public boolean isdelete = false;
	
	public MyAdapter(Context mContext,Cursor cursor){
		this.cursor = cursor;
		this.mContext = mContext;
		boollist = new ArrayList<>();
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return cursor.getCount();
	}
    public void setCursor(Cursor cursor){
    	this.cursor = cursor;
    }
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return cursor.getPosition();
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view;
		ViewHolder holder;
		if(convertView == null){
			view = LayoutInflater.from(mContext).inflate(R.layout.item, null);
			holder = new ViewHolder();
			holder.img = (ImageView) view.findViewById(R.id.list_img);
			holder.video = (ImageView) view.findViewById(R.id.list_video);
			holder.content = (TextView) view.findViewById(R.id.list_content);
			holder.time = (TextView) view.findViewById(R.id.list_time);
			holder.ck = (CheckBox) view.findViewById(R.id.ck);
			view.setTag(holder);
		}else {
			view = convertView;
			holder = (ViewHolder) view.getTag();
		}
		//Log.e("tag", "-----position="+position  +"    boollist="+boollist.size());
		if(isdelete){
			holder.ck.setVisibility(View.VISIBLE);
			holder.ck.setChecked(boollist.get(position));
		}else {
			holder.ck.setVisibility(View.GONE);
		}
		//NoteLog.d("--addsave--getView---position="+position);
		cursor.moveToPosition(position);
		String content = cursor.getString(cursor.getColumnIndex(NoteDB.CONTENT));
		String time = cursor.getString(cursor.getColumnIndex(NoteDB.TIME));
		String img = cursor.getString(cursor.getColumnIndex(NoteDB.IMG));
		String video = cursor.getString(cursor.getColumnIndex(NoteDB.VIDEO));
		holder.content.setText(content);
		Log.e("", "----------getview-------content=="+content);
		holder.time.setText(time);
		holder.img.setImageBitmap(getImageThumbnail(img, 200, 200));
		holder.video.setImageBitmap(getVideoThumbnail(video, 200, 200,
				MediaStore.Images.Thumbnails.MICRO_KIND));
		return view;
	}

	public static class ViewHolder {
		ImageView img;
		ImageView video;
		TextView content;
		TextView time;
		public CheckBox ck;
	}
	 /** 
     * ���ָ����ͼ��·���ʹ�С����ȡ����ͼ 
     * �˷���������ô��� 
     *     1. ʹ�ý�С���ڴ�ռ䣬��һ�λ�ȡ��bitmapʵ����Ϊnull��ֻ��Ϊ�˶�ȡ��Ⱥ͸߶ȣ� 
     *        �ڶ��ζ�ȡ��bitmap�Ǹ�ݱ���ѹ�����ͼ�񣬵���ζ�ȡ��bitmap����Ҫ������ͼ�� 
     *     2. ����ͼ����ԭͼ������û�����죬����ʹ����2.2�汾���¹���ThumbnailUtils��ʹ 
     *        �����������ɵ�ͼ�񲻻ᱻ���졣 
     * @param imagePath ͼ���·�� 
     * @param width ָ�����ͼ��Ŀ�� 
     * @param height ָ�����ͼ��ĸ߶� 
     * @return ��ɵ�����ͼ 
     */  
    private Bitmap getImageThumbnail(String imagePath, int width, int height) {  
        Bitmap bitmap = null;  
        BitmapFactory.Options options = new BitmapFactory.Options();  
        options.inJustDecodeBounds = true;  
        // ��ȡ���ͼƬ�Ŀ�͸ߣ�ע��˴���bitmapΪnull  
        bitmap = BitmapFactory.decodeFile(imagePath, options);  
        options.inJustDecodeBounds = false; // ��Ϊ false  
        // �������ű�  
        int h = options.outHeight;  
        int w = options.outWidth;  
        int beWidth = w / width;  
        int beHeight = h / height;  
        int be = 1;  
        if (beWidth < beHeight) {  
            be = beWidth;  
        } else {  
            be = beHeight;  
        }  
        if (be <= 0) {  
            be = 1;  
        }  
        options.inSampleSize = be;  
        // ���¶���ͼƬ����ȡ���ź��bitmap��ע�����Ҫ��options.inJustDecodeBounds ��Ϊ false  
        bitmap = BitmapFactory.decodeFile(imagePath, options);  
        // ����ThumbnailUtils����������ͼ������Ҫָ��Ҫ�����ĸ�Bitmap����  
        bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height,  
                ThumbnailUtils.OPTIONS_RECYCLE_INPUT);  
        return bitmap;  
    }  
  
    /** 
     * ��ȡ��Ƶ������ͼ 
     * ��ͨ��ThumbnailUtils������һ����Ƶ������ͼ��Ȼ��������ThumbnailUtils�����ָ����С������ͼ�� 
     * �����Ҫ������ͼ�Ŀ�͸߶�С��MICRO_KIND��������Ҫʹ��MICRO_KIND��Ϊkind��ֵ��������ʡ�ڴ档 
     * @param videoPath ��Ƶ��·�� 
     * @param width ָ�������Ƶ����ͼ�Ŀ�� 
     * @param height ָ�������Ƶ����ͼ�ĸ߶ȶ� 
     * @param kind ����MediaStore.Images.Thumbnails���еĳ���MINI_KIND��MICRO_KIND�� 
     *            ���У�MINI_KIND: 512 x 384��MICRO_KIND: 96 x 96 
     * @return ָ����С����Ƶ����ͼ 
     */  
    private Bitmap getVideoThumbnail(String videoPath, int width, int height,  
            int kind) {  
        Bitmap bitmap = null;  
        // ��ȡ��Ƶ������ͼ  
        bitmap = ThumbnailUtils.createVideoThumbnail(videoPath, kind);  
        bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height,  
                ThumbnailUtils.OPTIONS_RECYCLE_INPUT);  
        return bitmap;
    }  
}
