package com.runningsnail.androiddemo.notepad;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class NoteDB extends SQLiteOpenHelper {

	public static final String TABLENAME = "notepad";
	public static final String DBNAME = "notes.db";
	public static final String ID = "_id";
	public static final String CONTENT = "content";
	public static final String TIME = "time";
	public static final String IMG = "img";
	public static final String VIDEO = "video";
	public static final String DATATIME = "datatime";
	public static final String TEMPIMG = "tempimg";
	public static final int VERSION = 1;
	/*public NoteDB(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}*/

	public NoteDB(Context context) {
		super(context, DBNAME, null, VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE "+ TABLENAME+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
		+CONTENT+" TEXT,"+TIME+" TEXT,"+IMG+" TEXT,"+DATATIME+" TEXT,"
		+VIDEO+" TEXT,"+TEMPIMG+" TEXT)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
        switch (newVersion) {
		case 2:
			db.beginTransaction();
			db.execSQL("alter table notepad rename to temp_notepad");
			db.execSQL("CREATE TABLE "+ TABLENAME+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
					+CONTENT+" TEXT,"+TIME+" TEXT,"+IMG+" TEXT,"+DATATIME+" TEXT,"
					+VIDEO+" TEXT,"+TEMPIMG+" TEXT)"); 
			db.execSQL("insert into notepad select *,'' from temp_notepad");
			db.execSQL("drop table temp_notepad");
			db.setTransactionSuccessful();
			db.endTransaction();
			break;

		default:
			break;
		}
	}

}
