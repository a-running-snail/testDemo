package com.runningsnail.androiddemo.contentprovoder;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class MyProvider extends ContentProvider {

	private SQLiteDatabase database;
	public static final Uri URI = Uri.parse("content://com.runningsnail.androiddemo.cp");
	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		database = getContext().openOrCreateDatabase("mycp.db", Context.MODE_PRIVATE, null);
		database.execSQL("create table if not exists cp( _id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT NOT NULL,age TEXT NOT NULL)");
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		 Cursor cursor = database.query("cp", null,selection ,selectionArgs , null, null, null);
		return cursor;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		database.insert("cp", null, values);
		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		int result = database.update("cp", values,selection ,selectionArgs);
		return result;
	}

}
