package com.example.oweyou;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View.OnClickListener;



public class DB {
	
	public static final String KEY_ROWID = "_id";
	public static final String KEY_NAME = "Name";
	public static final String KEY_BALANCE = "Balance";
	
	private static final String DATABASE_NAME = "OweYou";
	private static final String DATABASE_TABLE = "mainTable";
	private static final String DATABASE_TEMPNAME = "tempTable";
	private static final int DATABASE_VERSION = 1;
	

	private DbHelper ourHelper;   //Instance of the class DBhelper
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;
	
	private static class DbHelper extends SQLiteOpenHelper{

		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" + 
			KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + 
					KEY_NAME + " TEXT NOT NULL, " + KEY_BALANCE + " TEXT);"
					);
			db.execSQL("CREATE TABLE " + DATABASE_TEMPNAME + " (" + 
					KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + 
							KEY_NAME + " TEXT NOT NULL, " + KEY_BALANCE + " TEXT);"
							);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(db);
		}

	}
	public DB(Context c){             //Another Constructor
		ourContext = c;
	}
	
	

	public DB open() throws SQLException{
		ourHelper = new DbHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
	}
		
	public void close(){
		ourHelper.close();
	}

	public void createEntry(String name, String balance) throws SQLException{
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		cv.put(KEY_NAME, name);
		cv.put(KEY_BALANCE, balance);
		ourDatabase.insert(DATABASE_TABLE, null, cv);
	}

	

	public String getData() {
		// TODO Auto-generated method stub
		String[] columns = new String[]{ KEY_ROWID, KEY_NAME, KEY_BALANCE};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
		String result = "";
		int iRow = c.getColumnIndex(KEY_ROWID);
		int iName = c.getColumnIndex(KEY_NAME);
		int iHotness = c.getColumnIndex(KEY_BALANCE);
		
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			result = result + c.getString(iRow) + " " + c.getString(iName) + "\t" + c.getString(iHotness) + "\n"; 
			
		}
		return result;
	}

	public boolean checkiftablexists() {
		// TODO Auto-generated method stub
		boolean hastables;
		Cursor c = ourDatabase.rawQuery("SELECT * FROM " + DATABASE_TABLE, null);
		if(c.getCount() == 0){
			hastables = false;
		}else{
			hastables = true;
		}
		return hastables;
	}



	public String getusername() {
		// TODO Auto-generated method stub
		String[] columns = new String[]{ KEY_ROWID, KEY_NAME, KEY_BALANCE};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_ROWID + "=1", null, null, null, null);
		if(c != null){
			c.moveToFirst();
			String name = c.getString(1);
			return name;
		}
		return null;
	}



	public int getCount() {
		// TODO Auto-generated method stub
		String[] columns = new String[]{ KEY_ROWID, KEY_NAME, KEY_BALANCE};
		Cursor c = ourDatabase.rawQuery("SELECT * FROM " + DATABASE_TABLE, null);
		return c.getCount();
	}



	public String getname(int i) {
		// TODO Auto-generated method stub
		String[] columns = new String[]{ KEY_ROWID, KEY_NAME, KEY_BALANCE};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_ROWID + "=" + i, null, null, null, null);
		if(c != null){
			c.moveToFirst();
			String name = c.getString(1);
			return name;
		}
		return null;
	}



	public void updatedb(int i, String n, String bal) {
		// TODO Auto-generated method stub
		ContentValues cvUp = new ContentValues();
		cvUp.put(KEY_NAME, n);
		cvUp.put(KEY_BALANCE, bal);
		ourDatabase.update(DATABASE_TABLE, cvUp, KEY_ROWID + "=" + i, null);
	}



	public void deleteEntry(long l1) {
		// TODO Auto-generated method stub
		int i;
		ourDatabase.delete(DATABASE_TABLE, KEY_ROWID + "=" + l1, null);
		/*for(i=(int) l1;i<getCount();i++){
			ContentValues cvD = new ContentValues();
			cvD.put(KEY_ROWID, i);
			ourDatabase.update(DATABASE_TABLE, cvD, KEY_ROWID + "=" + i+1, null);
			
		}*/
	}



	public long getAdminBalance() {
		// TODO Auto-generated method stub
		String[] columns = new String[]{ KEY_ROWID, KEY_NAME, KEY_BALANCE};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_ROWID + "=1", null, null, null, null);
		if(c != null){
			c.moveToFirst();
			String name = c.getString(2);
			long l = Long.parseLong(name);
			return l;
		}
		return 0;
	}



	public long getBalance(int i) {
		// TODO Auto-generated method stub
		String[] columns = new String[]{ KEY_ROWID, KEY_NAME, KEY_BALANCE};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_ROWID + "=" + i, null, null, null, null);
		if(c != null){
			c.moveToFirst();
			String name = c.getString(2);
			long l = Long.parseLong(name);
			return l;
		}
		return 0;
	}



	public void createTempEntry(String na, String bal) throws SQLException {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		cv.put(KEY_NAME, na);
		cv.put(KEY_BALANCE, bal);
		ourDatabase.insert(DATABASE_TEMPNAME, null, cv);
		
	}



	public long getTempBalance(int i) {
		// TODO Auto-generated method stub
		String[] columns = new String[]{ KEY_ROWID, KEY_NAME, KEY_BALANCE};
		Cursor c = ourDatabase.query(DATABASE_TEMPNAME, columns, KEY_ROWID + "=" + i, null, null, null, null);
		if(c != null){
			c.moveToFirst();
			String name = c.getString(2);
			long l = Long.parseLong(name);
			return l;
		}
		return 0;
	}



	public void dropTempTable() throws SQLException{
		// TODO Auto-generated method stub
		//ourDatabase.execSQL("DELETE * FROM " + DATABASE_TEMPNAME);
		ourDatabase.execSQL("TRUNCATE TABLE " + DATABASE_TEMPNAME);
	}



	public void updateTempEntry(int i, String na, String bal) throws SQLException {
		// TODO Auto-generated method stub
		ContentValues cvUp = new ContentValues();
		cvUp.put(KEY_NAME, na);
		cvUp.put(KEY_BALANCE, bal);
		ourDatabase.update(DATABASE_TEMPNAME, cvUp, KEY_ROWID + "=" + i, null);
		
	}



	public void deleteTempEntry(long l1) {
		// TODO Auto-generated method stub
		ourDatabase.delete(DATABASE_TEMPNAME, KEY_ROWID + "=" + l1, null);
	}



	public void dropTables() {
		// TODO Auto-generated method stub
		ourDatabase.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
		ourDatabase.execSQL("DROP TABLE IF EXISTS " + DATABASE_TEMPNAME);
		ourDatabase.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" + 
				KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + 
						KEY_NAME + " TEXT NOT NULL, " + KEY_BALANCE + " TEXT);"
						);
		ourDatabase.execSQL("CREATE TABLE " + DATABASE_TEMPNAME + " (" + 
						KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + 
								KEY_NAME + " TEXT NOT NULL, " + KEY_BALANCE + " TEXT);"
								);
	}
	}
	
