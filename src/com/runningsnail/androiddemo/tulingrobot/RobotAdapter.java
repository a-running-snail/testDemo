package com.runningsnail.androiddemo.tulingrobot;

import java.util.List;

import com.runningsnail.androiddemo.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class RobotAdapter extends BaseAdapter {

	private Context mContext;
	private List<ChatInfo> list;

	RobotAdapter(Context mContext, List<ChatInfo> list) {
		this.mContext = mContext;
		this.list = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	@Override
    public int getItemViewType(int position)
    {
        ChatInfo msg = list.get(position);
        return msg.getTag() == ChatInfo.RECEIVE ? 1 : 0;
    }
	 @Override  
     public int getViewTypeCount() {  
         // 这个方法默认返回1，如果希望listview的item都是一样的就返回1，我们这里有两种风格，返回2  
         return 2;  
     }  
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			if (list.get(position).getTag() == ChatInfo.SEND) {
				convertView = (RelativeLayout) LayoutInflater.from(mContext)
						.inflate(R.layout.right_item, parent,false);
				holder.content = (TextView) convertView.findViewById(R.id.right_robot_tv);
				holder.time = (TextView) convertView.findViewById(R.id.right_robot_time);
				holder.iv = (ImageView) convertView.findViewById(R.id.right_robot_iv);
				convertView.setTag(holder);
			} else if (list.get(position).getTag() == ChatInfo.RECEIVE) {
				convertView = (RelativeLayout) LayoutInflater.from(mContext)
						.inflate(R.layout.left_item, null);
				holder.content = (TextView) convertView.findViewById(R.id.left_robot_tv);
				holder.time = (TextView) convertView.findViewById(R.id.left_robot_time);
				holder.iv = (ImageView) convertView.findViewById(R.id.left_robot_iv);
				convertView.setTag(holder);
			}
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.content.setText(list.get(position).getContent());
		holder.time.setText(list.get(position).getTime());
		return convertView;
	}

	private class ViewHolder {

		TextView time;
		TextView content;
		ImageView iv;
	}
}
