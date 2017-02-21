package com.example.naukamagisterka;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class LoginDataBaseAdapter {
	static final String DATABASE_NAME = "WylaczSwiatloDB";
	static final int DATABASE_VERSION=3;
	public static final int NAME_COULMN=1;
	
	public String Email;
	public String Password;
	public int LEVEL;
	
	LoginDataBaseAdapter loginDataBaseAdapter;
	
	static final String DATABASE_CREATE = "CREATE TABLE IF NOT EXISTS "+"USERS"+"(USERNAME VARCHAR UNIQUE, PASSWORD VARCHAR, LEVEL INT, POINTS INT)"+";"+"";
	
	public SQLiteDatabase db;
	private final Context context;
	private DataBaseHelper dbHelper;
	
	
	public LoginDataBaseAdapter(Context _context){
		context=_context;
		dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
		
	}
	
	public LoginDataBaseAdapter open() throws SQLException{
		db=dbHelper.getWritableDatabase();
		return this;
	}
	
	public void close(){
		db.close();
	}
	
	public SQLiteDatabase getDatabaseInstance(){
		return db;
	}
	
	public void insertEntry(String userName, String password){
		ContentValues newValues = new ContentValues();
		
		newValues.put("USERNAME", userName);
		newValues.put("PASSWORD", password);
		newValues.put("LEVEL", 1);
		newValues.put("POINTS", 0);
		
		db.insert("USERS", null, newValues);
	}
	
	public int deleteEntry(String UserName){
		String where = "USERNAME=?";
		int numberOFEntriesDeleted = db.delete("USERS", where, new String[]{UserName});
		return numberOFEntriesDeleted;
	}
	
	public String getSingleEntry(String userName){
		Cursor cursor = db.query("USERS", null, " USERNAME=?", new String[]{userName},null,null,null);
		if (cursor.getCount()<1) //Nie ma username
		{
			cursor.close();
			return "NOT EXIST";
		}
		
		else{
		cursor.moveToFirst();
		String password = cursor.getString(cursor.getColumnIndex("PASSWORD"));
		cursor.close();
		return password;
		}
		
	}
	
	
	public String getSingleEntryUsers(String userName){
		Cursor cursor = db.query("USERS", null, " USERNAME=?", new String[]{userName},null,null,null);
		if (cursor.getCount()<1) //Nie ma username
		{
			cursor.close();
			return "NOT EXIST";
		}
		
		else{
		cursor.moveToFirst();
		String user = cursor.getString(cursor.getColumnIndex("USERNAME"));
		cursor.close();
		return user;
		}
		
	}
	
	public String getSingleEntryLevel(String userName){
		Cursor cursor = db.query("USERS", null, " USERNAME=?", new String[]{userName},null,null,null);
		if (cursor.getCount()<1) //Nie ma username
		{
			cursor.close();
			return "NOT EXIST";
		}
		
		else{
		cursor.moveToFirst();
		String level = cursor.getString(cursor.getColumnIndex("LEVEL"));
		cursor.close();
		return level;
		}
		
	}
	
	public void updateEntry(String userName, String password, String level, String points){
			ContentValues updateValues = new ContentValues();
			updateValues.put("USERNAME", userName);
			updateValues.put("PASSWORD", password);
			updateValues.put("LEVEL", level);
			updateValues.put("POINTS", points);
			
			String where = "USERNAME=?";
			db.update("USERS", updateValues, where, new String[]{userName});

		//String storedUsers = loginDataBaseAdapter.getSingleEntry(userName);
		
	}
	
	public void displayToast(String value){
		
		 Toast.makeText(context, value, Toast.LENGTH_LONG).show();
	}

}
