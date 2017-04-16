package com.runningsnail.androiddemo.notepad;

import com.runningsnail.androiddemo.tools.NoteLog;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SQLiteUtils {
     
	private NoteDB dbs;
	
	public NoteDB getDBinstance(Context mContext) {
		// TODO Auto-generated method stub
         if(dbs == null ){
        	 dbs = new NoteDB(mContext);
         }
         return dbs;
	}
	
	public void insert(NoteDB dbHelper, NoteInfo user) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NoteDB.CONTENT, user.getContent());
        values.put(NoteDB.TIME, user.getTime());
        values.put(NoteDB.IMG, user.getImg());
        values.put(NoteDB.VIDEO, user.getVideo());
        values.put(NoteDB.DATATIME, user.getDatetime());
        values.put(NoteDB.TEMPIMG, user.getTempimg());
        db.insert(NoteDB.TABLENAME, null, values);
        db.close();
	}

	public void update(NoteDB dbHelper,String datatime, NoteInfo user) {
		NoteLog.d("--addsave----修改过----修改-update---datatime="+datatime);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NoteDB.CONTENT, user.getContent());
        values.put(NoteDB.TIME, user.getTime());
        values.put(NoteDB.IMG, user.getImg());
        values.put(NoteDB.VIDEO, user.getVideo());
        values.put(NoteDB.DATATIME, user.getDatetime());
        values.put(NoteDB.TEMPIMG, user.getTempimg());
        db.update(NoteDB.TABLENAME, values, "datatime=?", new String[]{datatime});
        db.close();
	}

	public void delete(NoteDB dbHelper, String time) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		db.delete(NoteDB.TABLENAME, "datatime=?", new String[]{time});
		db.close();
	}
	public String query(NoteDB dbHelper, String content) {
		String id = "";
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor cursor = db.query(NoteDB.TABLENAME, new String[]{NoteDB.DATATIME}, "content=?", new String[]{content}, null, null, null);
		NoteLog.d("--addsave----修改过----修改-update---content="+content+"  getCount="+cursor.getCount());
		if(cursor !=null && cursor.getCount()> 0){
			while (cursor.moveToNext()) {
				id = cursor.getString(cursor.getColumnIndex(NoteDB.DATATIME));
			}
			cursor.close();
	    }
		db.close();
	    return id;
	}
	
}
