package com.runningsnail.androiddemo.slidemenu;

import java.util.Arrays;
import java.util.List;

import com.runningsnail.androiddemo.R;
import com.runningsnail.androiddemo.actionbarsherlock.ActionbarSherlockActivity;
import com.runningsnail.androiddemo.anim.AnimActivity;
import com.runningsnail.androiddemo.banner.BannerActivity;
import com.runningsnail.androiddemo.contacts.ContactActivity;
import com.runningsnail.androiddemo.contentprovoder.ProviderActivity;
import com.runningsnail.androiddemo.dialog.DialogActivity;
import com.runningsnail.androiddemo.downloadmanage.DownloadActivity;
import com.runningsnail.androiddemo.eventbus.MainEventbus;
import com.runningsnail.androiddemo.glide.GlideActivity;
import com.runningsnail.androiddemo.gson.FastgsonActivity;
import com.runningsnail.androiddemo.guaguale.GuaguaActivity;
import com.runningsnail.androiddemo.leakcanary.LeakCanaryActivitys;
import com.runningsnail.androiddemo.lock.LockActivity;
import com.runningsnail.androiddemo.lock.SettingLock;
import com.runningsnail.androiddemo.notification.NotificationActivity;
import com.runningsnail.androiddemo.photoview.SimpleSampleActivity;
import com.runningsnail.androiddemo.popwindow.PopWindows;
import com.runningsnail.androiddemo.roundimageview.RoundActivity;
import com.runningsnail.androiddemo.sharesdk.SharesdkActivity;
import com.runningsnail.androiddemo.slitemenu.SliteActivity;
import com.runningsnail.androiddemo.surfaceview.SurfaceViewActivity;
import com.runningsnail.androiddemo.switchlayout.MainActivity;
import com.runningsnail.androiddemo.tulingrobot.RobotActivity;
import com.runningsnail.androiddemo.twod.TwoDRotate;
import com.runningsnail.androiddemo.view.MyViewActivity;
import com.runningsnail.androiddemo.wheel.CitiesActivity;
import com.runningsnail.androiddemo.windowmanager.FloatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MenuRightFragment extends Fragment {

	private View mView;
	private ListView mCategories;
	private List<String> mDatas = Arrays
			.asList("switchLayout Activity","leckcanary","popwindow", "roundImageview", 
					"photoview","Fastgson","ActionbarSherlock","Eventbus","Sharesdk",
					"notification","lock","surfaceView","2d rotate","myview","contentprovider"
					,"anim","contact","robot","slitemenu","guaguaka","down","dialog"
					,"wheel","banner","float","glide");
	private ListAdapter mAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if (mView == null) {
			initView(inflater, container);
		}
		return mView;
	}

	private void initView(LayoutInflater inflater, ViewGroup container) {
		mView = inflater.inflate(R.layout.right_menu, container, false);
		mCategories = (ListView) mView
				.findViewById(R.id.id_listview_categories);
		mAdapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, mDatas);
		mCategories.setAdapter(mAdapter);
		mCategories.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				//Toast.makeText(getActivity(), position+"", Toast.LENGTH_SHORT).show();
				switch (position) {
				case 0:
					startActivity(new Intent(getActivity(), MainActivity.class));
					break;
				case 1:
					startActivity(new Intent(getActivity(), LeakCanaryActivitys.class));
					break;
				case 2:
					startActivity(new Intent(getActivity(), PopWindows.class));
					break;
				case 3:
					startActivity(new Intent(getActivity(), RoundActivity.class));
					break;
				case 4:
					startActivity(new Intent(getActivity(), SimpleSampleActivity.class));
					break;
				case 5:
					startActivity(new Intent(getActivity(), FastgsonActivity.class));
					break;
				case 6:
					startActivity(new Intent(getActivity(), ActionbarSherlockActivity.class));
					break;
				case 7:
					startActivity(new Intent(getActivity(), MainEventbus.class));
					break;
				case 8:
					startActivity(new Intent(getActivity(), SharesdkActivity.class));
					break;
				case 9:
					startActivity(new Intent(getActivity(), NotificationActivity.class));
					break;
				case 10:
					startActivity(new Intent(getActivity(), LockActivity.class));
					break;
				case 11:
					startActivity(new Intent(getActivity(), SurfaceViewActivity.class));
					break;
				case 12:
					startActivity(new Intent(getActivity(), TwoDRotate.class));
					break;
				case 13:
					startActivity(new Intent(getActivity(), MyViewActivity.class));
					break;
				case 14:
					startActivity(new Intent(getActivity(), ProviderActivity.class));
					break;
				case 15:
					startActivity(new Intent(getActivity(), AnimActivity.class));
					break;
				case 16:
					startActivity(new Intent(getActivity(), ContactActivity.class));
					break;
				case 17:
					startActivity(new Intent(getActivity(), RobotActivity.class));
					break;
				case 18:
					startActivity(new Intent(getActivity(), SliteActivity.class));
					break;
				case 19:
					startActivity(new Intent(getActivity(), GuaguaActivity.class));
					break;
				case 20:
					startActivity(new Intent(getActivity(), DownloadActivity.class));
					break;
				case 21:
					startActivity(new Intent(getActivity(), DialogActivity.class));
					break;
				case 22:
					startActivity(new Intent(getActivity(), CitiesActivity.class));
					break;
				case 23:
					startActivity(new Intent(getActivity(), BannerActivity.class));
					break;
				case 24:
					startActivity(new Intent(getActivity(), FloatActivity.class));
					getActivity().finish();
					break;
				case 25:
					startActivity(new Intent(getActivity(), GlideActivity.class));
					getActivity().finish();
					break;
				default:
					break;
				}
			}
		});
	}
}