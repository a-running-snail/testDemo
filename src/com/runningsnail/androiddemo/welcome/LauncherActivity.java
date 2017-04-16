package com.runningsnail.androiddemo.welcome;

import java.io.File;
import java.util.ArrayList;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.runningsnail.androiddemo.R;
import com.runningsnail.androiddemo.notepad.AddContent;
import com.runningsnail.androiddemo.notepad.MyAdapter;
import com.runningsnail.androiddemo.notepad.MyAdapter.ViewHolder;
import com.runningsnail.androiddemo.notepad.NoteDB;
import com.runningsnail.androiddemo.notepad.SQLiteUtils;
import com.runningsnail.androiddemo.slidemenu.MenuLeftFragment;
import com.runningsnail.androiddemo.slidemenu.MenuRightFragment;
import com.runningsnail.androiddemo.tools.NoteLog;
import com.tandong.swichlayout.SwichLayoutInterFace;
import com.tandong.swichlayout.SwitchLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

@SuppressLint("NewApi")
public class LauncherActivity extends SlidingFragmentActivity implements OnClickListener,SwichLayoutInterFace  {

	private TextView leftButton,rightButton,textbtn,imgbtn,videobtn;
	/*
	 *pulltorefresh
	 */
	//private LinkedList<String> mListItems;  
    /** 
     * ����ˢ�µĿؼ� 
     */  
    private PullToRefreshListView mPullRefreshListView;  
  
   // private ArrayAdapter<String> mAdapter;  
    private MyAdapter adapter;
    private NoteDB dbHelper;
    private Cursor cursor;
    private Button delebtn;
    private CheckBox allck;
    private LinearLayout detelelayout;
    private boolean isdelete = false;
    private ArrayList<String> addstr;
    private ArrayList<String> addmediastr;
    //private int mItemCount = 9;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launcher);
		addstr = new ArrayList<>();
		addmediastr = new ArrayList<>();
		initView();
		// ��ʼ��SlideMenu
		initRightMenu();
		setEnterSwichLayout();
		// �õ��ؼ�  
        mPullRefreshListView = (PullToRefreshListView) findViewById(R.id.pull_refresh_list);  
        mPullRefreshListView.setMode(Mode.BOTH);  
        // ��ʼ�����  
       // initDatas();  
        // ����������  
        //mAdapter = new ArrayAdapter<String>(this,  
         //       android.R.layout.simple_list_item_1, mListItems);  
       // mPullRefreshListView.setAdapter(mAdapter);  
  
        mPullRefreshListView.setOnRefreshListener(new OnRefreshListener2<ListView>()
		{
			@Override
			public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView)
			{
				//����д����ˢ�µ�����
				new GetDataTask().execute();
			}

			@Override
			public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView)
			{
				//����д�������ظ�������
				new GetDataTask().execute();
			}
		});
        mPullRefreshListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,int pos, long id) {
				// TODO Auto-generated method stub
				int position = pos -1;
				if(!isdelete){
					itemclick(position);
				}else {
					ViewHolder holder = (ViewHolder) view.getTag();
					holder.ck.toggle();
					adapter.boollist.set(position, holder.ck.isChecked());
					if (adapter.boollist.contains(false)) {
						allck.setChecked(false);
					}else {
						allck.setChecked(true);
					}
				}
				
			}

		});
        mPullRefreshListView.getRefreshableView().setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,int position, long id) {
				// TODO Auto-generated method stub
				if(!isdelete){
					isdelete = true;
				}
				adapter.isdelete = true;
				adapter.boollist.clear();
				for (int i = 0; i < cursor.getCount(); i++) {
					if(adapter != null){
						adapter.boollist.add(false);
						detelelayout.setVisibility(View.VISIBLE);
					}
				}
				adapter.notifyDataSetChanged();
				return false;
			}
		});
	}
	
  
	private class GetDataTask extends AsyncTask<Void, Void, String>{   
    @Override  
        protected String doInBackground(Void... params)  
        {  
            try  
            {  
                Thread.sleep(2000);  
            } catch (InterruptedException e)  
            {  
            }  
            return "" ;//+ (mItemCount++);  
        }  
  
        @Override  
        protected void onPostExecute(String result)  
        {  
          //  mListItems.add(result);  
           // mAdapter.notifyDataSetChanged();  
            // Call onRefreshComplete when the list has been refreshed.  
            mPullRefreshListView.onRefreshComplete();  
        }  
    }  
	
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.title_left:
			showLeftMenu();
			break;
		case R.id.title_right:
			showRightMenu();
			break;
		case R.id.title_text:
			startAC(AddContent.class,0);
			break;
		case R.id.title_img:
			startAC(AddContent.class,1);
			break;
		case R.id.title_video:
			startAC(AddContent.class,2);
			break;
		case R.id.delete:
			deleteData();
			break;
		case R.id.allck:
			if(allck.isChecked()){
				adapter.boollist.clear();
				for (int i = 0; i < cursor.getCount(); i++) {
					adapter.boollist.add(true);
				}
			}else {
				adapter.boollist.clear();
				for (int i = 0; i < cursor.getCount(); i++) {
					adapter.boollist.add(false);
				}
			}
			adapter.notifyDataSetChanged();
			break;
		
		
		default:
			break;
		}
	}
	private void initView() {
		// TODO Auto-generated method stub
		leftButton = (TextView) findViewById(R.id.title_left);
		rightButton = (TextView) findViewById(R.id.title_right);
		textbtn = (TextView) findViewById(R.id.title_text);
		imgbtn = (TextView) findViewById(R.id.title_img);
		videobtn = (TextView) findViewById(R.id.title_video);
		detelelayout = (LinearLayout) findViewById(R.id.deletelayout);
		delebtn = (Button) findViewById(R.id.delete);
		allck = (CheckBox) findViewById(R.id.allck);
		textbtn.setOnClickListener(this);
		imgbtn.setOnClickListener(this);
		videobtn.setOnClickListener(this);
		leftButton.setOnClickListener(this);
		rightButton.setOnClickListener(this);
		delebtn.setOnClickListener(this);
		allck.setOnClickListener(this);
		SQLiteUtils sqlite = new SQLiteUtils();
		dbHelper = sqlite.getDBinstance(LauncherActivity.this);
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		queryData();
	}
	private void queryData(){
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		cursor = db.query(NoteDB.TABLENAME, null, null, null, null, null, "datatime desc");
		Log.e("", "------queryData---------count="+cursor.getCount());
		adapter = new MyAdapter(LauncherActivity.this, cursor);
		mPullRefreshListView.setAdapter(adapter);
		
	}
	private void refreshdata(){
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		cursor = db.query(NoteDB.TABLENAME, null, null, null, null, null, "datatime desc");
	    if(adapter == null){
	    	adapter = new MyAdapter(LauncherActivity.this, cursor);
	    }
	    adapter.isdelete = false;
	    isdelete = false;
	    adapter.setCursor(cursor);
	    adapter.notifyDataSetChanged();
	}
	private void startAC(Class<?> c,int tag){
		Intent it = new Intent(LauncherActivity.this,c);
		it.putExtra("tag", tag);
		it.putExtra("content", "");
		it.putExtra("time", "");
		it.putExtra("img", "");
		it.putExtra("video", "");
		startActivity(it);
	}
	private void initRightMenu() {
		// TODO Auto-generated method stub
		Fragment leftMenuFragment = new MenuLeftFragment();
		setBehindContentView(R.layout.left_menu_frame);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.id_left_menu_frame, leftMenuFragment).commit();
		SlidingMenu menu = getSlidingMenu();
		menu.setMode(SlidingMenu.LEFT_RIGHT);
		// 设置触摸屏幕的模式
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		menu.setShadowWidthRes(R.dimen.shadow_width);
		menu.setShadowDrawable(R.drawable.shadow);
		// 设置滑动菜单视图的宽度
		menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
//		menu.setBehindWidth()
		// 设置渐入渐出效果的值
		menu.setFadeDegree(0.35f);
		// menu.setBehindScrollScale(1.0f);
		menu.setSecondaryShadowDrawable(R.drawable.shadow);
		//设置右边（二级）侧滑菜单
		menu.setSecondaryMenu(R.layout.right_menu_frame);
		Fragment rightMenuFragment = new MenuRightFragment();
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.id_right_menu_frame, rightMenuFragment).commit();
	}
	public void showLeftMenu()
	{
		getSlidingMenu().showMenu();
	}

	public void showRightMenu()
	{
		getSlidingMenu().showSecondaryMenu();
	}
	private void itemclick(int position) {
		// TODO Auto-generated method stub
		cursor.moveToPosition(position);
		String content = cursor.getString(cursor.getColumnIndex(NoteDB.CONTENT));
		String time = cursor.getString(cursor.getColumnIndex(NoteDB.DATATIME));
		String img = cursor.getString(cursor.getColumnIndex(NoteDB.IMG));
		String video = cursor.getString(cursor.getColumnIndex(NoteDB.VIDEO));
		NoteLog.d("------setOnItemClickListener----img="+img);
		NoteLog.d("------setOnItemClickListener----video="+video);
		Intent it = new Intent(LauncherActivity.this,AddContent.class);
		it.putExtra("content", content);
		it.putExtra("time", time);
		it.putExtra("img", img);
		it.putExtra("video", video);
		if(!TextUtils.isEmpty(img)){
			it.putExtra("tag", 1);
		}else if (!TextUtils.isEmpty(video)) {
			it.putExtra("tag", 2);
		}else {
			it.putExtra("tag", 0);
		}
		startActivity(it);
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode == KeyEvent.KEYCODE_BACK){
			if(isdelete){
				isdelete = false;
				adapter.isdelete = false;
				detelelayout.setVisibility(View.GONE);
				adapter.notifyDataSetChanged();
				return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	private void startAsyncTask() {
	    // This async task is an anonymous class and therefore has a hidden reference to the outer
	    // class MainActivity. If the activity gets destroyed before the task finishes (e.g. rotation),
	    // the activity instance will leak.
	    new AsyncTask<Void, Void, Void>() {
	      @Override protected Void doInBackground(Void... params) {
	        // Do some slow work in background
	        for (int i = 0; i < addmediastr.size(); i++) {
				boolean bool = new File(addmediastr.get(i)).delete();
				Log.e("tag", "------------bool="+bool + "   address="+addmediastr.get(i));
			}
	        return null;
	      }

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			addmediastr.clear();
		}
	      
	    }.execute();
	  }
	private void deleteData(){
		addstr.clear();
		addmediastr.clear();
		detelelayout.setVisibility(View.GONE);
		for (int i = 0; i < adapter.boollist.size(); i++) {
			if (adapter.boollist.get(i)) {
				cursor.moveToPosition(i);
				String content = cursor.getString(cursor.getColumnIndex(NoteDB.CONTENT));
				String time = cursor.getString(cursor.getColumnIndex(NoteDB.DATATIME));
				String img = cursor.getString(cursor.getColumnIndex(NoteDB.IMG));
				String video = cursor.getString(cursor.getColumnIndex(NoteDB.VIDEO));
				String tempimg = cursor.getString(cursor.getColumnIndex(NoteDB.TEMPIMG));
				addstr.add(time);
				Log.e("tag", "--img----i="+i+"   time="+time+" content="+content);
				if (!TextUtils.isEmpty(img)) {
					addmediastr.add(img);
				}
				if (!TextUtils.isEmpty(video)) {
					addmediastr.add(video);
				}
				if (!TextUtils.isEmpty(tempimg)) {
					addmediastr.add(tempimg);
				}
			}
		}
		SQLiteUtils sqlite = new SQLiteUtils();
		NoteDB dbHelper = sqlite.getDBinstance(LauncherActivity.this);
		for (int i = 0; i < addstr.size(); i++) {
			sqlite.delete(dbHelper, addstr.get(i));
		}
		startAsyncTask();
		refreshdata();
	}
	@Override
	public void setEnterSwichLayout() {
		// TODO Auto-generated method stub
		SwitchLayout.ScaleBig(this, false, null);
	}


	@Override
	public void setExitSwichLayout() {
		// TODO Auto-generated method stub
		SwitchLayout.ScaleBig(this, true, null);
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		setExitSwichLayout();
	}
}
