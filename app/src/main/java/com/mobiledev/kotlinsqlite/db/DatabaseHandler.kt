package com.mobiledev.kotlinsqlite.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.mobiledev.kotlinsqlite.UserInfo

/**
 * Created by Manu on 11/7/2017.
 */
class DatabaseHandler(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {

    companion object {
        val DATABASE_NAME = "MYDatabase.db"
        val TABLE_NAME = "my_table"
        val ID = "ID"
        val NAME = "NAME"
        val AGE = "AGE"
        val PHONE = "PHONE"
        val EMAIL = "EMAIL"    }


    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("create table $TABLE_NAME (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,AGE INTEGER,PHONE TEXT, EMAIL TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    /**
     * insert data
     */
    fun insertData(name: String, age: String, phone: String, email: String) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(NAME, name)
        contentValues.put(AGE, age)
        contentValues.put(PHONE, phone)
        contentValues.put(EMAIL,email)
        db.insert(TABLE_NAME, null, contentValues)
    }

    /**
     * list of user data
     */
    fun listOfUserInfo(): ArrayList<UserInfo>  {
        val db = this.writableDatabase
        val res = db.rawQuery("select * from " + TABLE_NAME, null)
        val useList = ArrayList<UserInfo>()
        while (res.moveToNext()) {
            var userInfo = UserInfo()
            userInfo.id = Integer.valueOf(res.getString(0))
            userInfo.name = res.getString(1)
            userInfo.age = Integer.valueOf(res.getString(2))
            userInfo.phone = Integer.valueOf(res.getString(3))
            userInfo.email = res.getString(4)
            useList.add(userInfo)
        }
        return useList
    }

//    fun getUserInfoById(id: String): UserInfo {
//        val db = this.writableDatabase
//        val res = db.rawQuery("select * from " + TABLE_NAME+ " where = "+ id, null)
//
//        while (res.moveToNext()) {
//            var userInfo = UserInfo()
//            userInfo.id = Integer.valueOf(res.getString(0))
//            userInfo.name = res.getString(1)
//            userInfo.age = Integer.valueOf(res.getString(2))
//            userInfo.phone = Integer.valueOf(res.getString(3))
//            userInfo.email = res.getString(4)
//            return userInfo
//        }
//      //  return null
//    }




    //Getting all user list
    fun getAllUserData(): ArrayList<UserInfo> {
        val stuList: ArrayList<UserInfo> = arrayListOf<UserInfo>()
        val cursor: Cursor = getReadableDatabase().query(TABLE_NAME, arrayOf(ID, NAME, AGE, PHONE, EMAIL), null, null, null, null, null)
        try {
            if (cursor.getCount() != 0) {
                cursor.moveToFirst()
                if (cursor.getCount() > 0) {
                    do {
                        val id : Int = cursor.getInt(cursor.getColumnIndex(ID))
                        val name: String = cursor.getString(cursor.getColumnIndex(NAME))
                        val age: Int = cursor.getInt(cursor.getColumnIndex(AGE))
                        val phone: Int = cursor.getInt(cursor.getColumnIndex(PHONE))
                        val email: String =  cursor.getString(cursor.getColumnIndex(EMAIL))
                        var userInfo = UserInfo()
                        userInfo.id = id
                        userInfo.name = name
                        userInfo.age = age
                        userInfo.phone = phone
                        userInfo.email = email
                        stuList.add(userInfo)
                    } while ((cursor.moveToNext()))
                }
            }
        } finally {
            cursor.close()
        }

        return stuList
    }

    fun getParticularUserData(id: String): UserInfo {
        var userInfo  = UserInfo()
        val db = this.readableDatabase
        val selectQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE " + ID + " = '" + id + "'"
        val cursor = db.rawQuery(selectQuery, null)
        try {
            if (cursor.getCount() != 0) {
                cursor.moveToFirst();
                if (cursor.getCount() > 0) {
                    do {
                        userInfo.id = cursor.getInt(cursor.getColumnIndex(ID))
                        userInfo.name = cursor.getString(cursor.getColumnIndex(NAME))
                        userInfo.age = cursor.getInt(cursor.getColumnIndex(AGE))
                        userInfo.phone = cursor.getInt(cursor.getColumnIndex(PHONE))
                        userInfo.email = cursor.getString(cursor.getColumnIndex(EMAIL))
                    } while ((cursor.moveToNext()));
                }
            }
        } finally {
            cursor.close();
        }
        return userInfo
    }

    /**
     * update te userdata
     */
    fun updateData(id: String, name: String, age: String, phone: String, email: String): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ID, id)
        contentValues.put(NAME, name)
        contentValues.put(AGE, age)
        contentValues.put(PHONE, phone)
        contentValues.put(EMAIL,email)
        db.update(TABLE_NAME, contentValues, "ID = ?", arrayOf(id))
        return true
    }

    /**
     * delete the userData
     */
    fun deleteData(id : String) : Int {
        val db = this.writableDatabase
        return db.delete(TABLE_NAME,"ID = ?", arrayOf(id))

    }


}