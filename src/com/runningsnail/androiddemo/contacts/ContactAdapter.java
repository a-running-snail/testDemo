package com.runningsnail.androiddemo.contacts;

import java.util.List;

import com.runningsnail.androiddemo.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ContactAdapter extends BaseAdapter{

	private Context mContext;
	private List<Userinfo> list;
	protected ContactAdapter(Context mContext,List<Userinfo> list){
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
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.contact_item, null);
			holder = new ViewHolder();
			holder.name = (TextView) convertView.findViewById(R.id.contact_name);
			holder.num = (TextView) convertView.findViewById(R.id.contact_number);
			convertView.setTag(holder);
		}else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.name.setText(list.get(position).getName());
		holder.num.setText(list.get(position).getNum());
		return convertView;
	}
   private class ViewHolder{
	   TextView name;
	   TextView num;
   }
}
