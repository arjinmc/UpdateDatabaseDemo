package com.arjinmc.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @usage SQLite Database Helper
 * @author Eminem Lu
 * @email arjinmc@hotmail.com
 * @date 2014年7月14日
 */
public class DBOpenHelper extends SQLiteOpenHelper {

	/**database file name*/
	private static final String DB_NAME = "data"+".db";
	/**database version number*/
	private static final int DB_VERSION = 1;
	
	private static final String CREATE_TABLE_INFO = "CREATE TABLE IF NOT EXISTS INFOTB ("
			+ "ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT);";
	
	//content for update
	//private static final String CREATE_TABLE_INFO = "CREATE TABLE IF NOT EXISTS INFOTB ("
	//		+ "ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,PHONE TEXT DEFAULT \"012354\");";
	private static final String ALTER_TABLE_INFO_TEMP = "ALTER TABLE INFOTB RENAME TO INFOTB_TEMP";
	private static final String INSER_TABLE_INFO = "INSERT INTO INFOTB(ID,NAME) SELECT ID,NAME FROM INFOTB_TEMP";
	private static final String DROUP_TABLE_INFO_TEMP = "DROP TABLE IF EXISTS INFOTB_TEMP";
	
	public DBOpenHelper(Context context){
		super(context, DB_NAME, null, DB_VERSION);
	}
	
	public DBOpenHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, DB_NAME, factory, DB_VERSION);
	}
	
	public DBOpenHelper(Context context, String name, CursorFactory factory,
			int version, DatabaseErrorHandler errorHandler) {
		super(context, DB_NAME, factory, DB_VERSION, errorHandler);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		
		db.execSQL(CREATE_TABLE_INFO);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//content for update to alter table
//		if(newVersion>oldVersion){
//			try  
//		    {  
//		        db.beginTransaction();  
//		  
//		        // 1, Rename table.  
//		        db.execSQL(ALTER_TABLE_INFO_TEMP);
//		  
//		        // 2, Create table.  
//		        db.execSQL(CREATE_TABLE_INFO);
//		  
//		        // 3, Load data  
//		        db.execSQL(INSER_TABLE_INFO);  
//		  
//		        // 4, Drop the temporary table.  
//		        db.execSQL(DROUP_TABLE_INFO_TEMP);
//		        
//		  
//		        db.setTransactionSuccessful();  
//		    }  
//		    catch (SQLException e)  
//		    {  
//		        e.printStackTrace();  
//		    }  
//		    catch (Exception e)  
//		    {  
//		        e.printStackTrace();  
//		    }  
//		    finally  
//		    {  
//		        db.endTransaction();  
//		    }  
//		}
	}

}
