package com.example.islamiclovers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE user ( email text primary key, password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS  user");

    }
    public boolean insert(String user, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", user);
        contentValues.put("password", password);
        long ins = db.insert("user", null, contentValues);
        if (ins == -1)
            return false;
        else return true;
    }
    //check email exists
    public Boolean checkUser(String user){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c=db.rawQuery("Select * from user where email=?",new String[]{user});
        if(c.getCount()>0)return false;
        else return true;
    }
    //checking email and password
    public  boolean emailpassword(String email,String password){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c=db.rawQuery("Select * from user where email=? and password=?",new String[]{email,password});
        if(c.getCount()>0)return true;
        else return  false;
    }
}
