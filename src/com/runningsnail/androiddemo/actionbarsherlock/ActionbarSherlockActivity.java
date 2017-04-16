package com.runningsnail.androiddemo.actionbarsherlock;

import android.app.SearchManager;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.SubMenu;
import com.actionbarsherlock.widget.SearchView;
import com.runningsnail.androiddemo.R;
import com.tandong.swichlayout.SwichLayoutInterFace;
import com.tandong.swichlayout.SwitchLayout;

public class ActionbarSherlockActivity extends SherlockActivity implements SwichLayoutInterFace,SearchView.OnQueryTextListener,SearchView.OnSuggestionListener{
	
	private static final String[] COLUMNS = { BaseColumns._ID, SearchManager.SUGGEST_COLUMN_TEXT_1, };
	private QuerySuggestionsAdapter mSuggestionsAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_actionbarsherlock);
		initActionBarTitle();
		setEnterSwichLayout();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		
		createSearchItem(menu);

		/*************************************************/

		MenuItem share = menu.add(0, 1, 2, "share");
		share.setIcon(R.drawable.abs__ic_menu_share_holo_dark);
		share.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

		/***************************************************/
		SubMenu addMenu = menu.addSubMenu(0, 2, 3, "overflow");
		addMenu.add(0, 3, 0, "鏇存崲涓婚");
		addMenu.add(0, 4, 0, "绯荤粺璁剧疆");
		addMenu.add(0, 5, 0, "淇敼瀵嗙爜");
		addMenu.add(0, 6, 0, "鏇存崲澶村儚");

		MenuItem overFlowItem = addMenu.getItem();
		overFlowItem.setIcon(R.drawable.abs__ic_menu_moreoverflow_holo_dark);
		overFlowItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

		return super.onCreateOptionsMenu(menu);
	}




	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case 1:
			Toast.makeText(ActionbarSherlockActivity.this, "鍒嗕韩", Toast.LENGTH_SHORT).show();
			break;
		case 3:
			Toast.makeText(ActionbarSherlockActivity.this, "鏇存崲涓婚", Toast.LENGTH_SHORT).show();
			break;
		case 4:
			Toast.makeText(ActionbarSherlockActivity.this, "绯荤粺璁剧疆", Toast.LENGTH_SHORT).show();
			break;
		case 5:
			Toast.makeText(ActionbarSherlockActivity.this, "淇敼瀵嗙爜", Toast.LENGTH_SHORT).show();
			break;
		case 6:
			Toast.makeText(ActionbarSherlockActivity.this, "鏇存崲澶村儚", Toast.LENGTH_SHORT).show();
			break;
		}
		return super.onOptionsItemSelected(item);
	}



	/**
	 * Create the search view(鎼滅储鐨勬牳蹇冧唬鐮�
	 * 
	 * @param menu
	 * @return void
	 * @author hsx
	 * @time 2014-3-16涓嬪崍03:48:01
	 */
	private void createSearchItem(com.actionbarsherlock.view.Menu menu) {

		SearchView searchView = new SearchView(getSupportActionBar().getThemedContext());
		searchView.setQueryHint("Search for countries");
		searchView.setOnQueryTextListener(this);
		searchView.setOnSuggestionListener(this);

		if (mSuggestionsAdapter == null) {
			MatrixCursor cursor = new MatrixCursor(COLUMNS);
			cursor.addRow(new String[] { "1", "'Murica" });
			cursor.addRow(new String[] { "2", "Canada" });
			cursor.addRow(new String[] { "3", "Denmark" });
			mSuggestionsAdapter = new QuerySuggestionsAdapter(getSupportActionBar().getThemedContext(), cursor);
		}

		searchView.setSuggestionsAdapter(mSuggestionsAdapter);

		MenuItem searchItem = menu.add(0, 0, 0, "search");
		searchItem.setIcon(R.drawable.abs__ic_search);
		searchItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
		searchItem.setActionView(searchView);
	}
	private void initActionBarTitle() {
		// 鍙互鑷畾涔塧ctionbar
		getSupportActionBar().setDisplayShowCustomEnabled(true);
		getSupportActionBar().setDisplayShowTitleEnabled(false);
		// 涓嶅湪actionbar鏄剧ずlogo
		getSupportActionBar().setDisplayShowHomeEnabled(false);
		View mainActionBarView = LayoutInflater.from(this).inflate(R.layout.actionbar_normal, null);
		getSupportActionBar().setCustomView(mainActionBarView);
	}
	
	/***
	 * implements SearchView.OnQueryTextListener
	 */
	@Override
	public boolean onQueryTextSubmit(String query) {
		//ToastUtil.showShortToast(this, "You searched for: " + query);
		return true;
	}

	/***
	 * implements SearchView.OnQueryTextListener
	 */
	@Override
	public boolean onQueryTextChange(String newText) {
		return false;
	}

	/**
	 * implements SearchView.OnSuggestionListener
	 */
	@Override
	public boolean onSuggestionSelect(int position) {
		return false;
	}

	/**
	 * implements SearchView.OnSuggestionListener
	 */
	@Override
	public boolean onSuggestionClick(int position) {
		Cursor c = (Cursor) mSuggestionsAdapter.getItem(position);
		String query = c.getString(c.getColumnIndex(SearchManager.SUGGEST_COLUMN_TEXT_1));
		//ToastUtil.showShortToast(this, "Suggestion clicked: " + query);
		return true;
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
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		setExitSwichLayout();
	}
}
