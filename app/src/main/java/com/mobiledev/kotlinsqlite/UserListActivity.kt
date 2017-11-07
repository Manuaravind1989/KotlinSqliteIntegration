package com.mobiledev.kotlinsqlite

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ListView
import com.mobiledev.kotlinsqlite.db.DatabaseHandler

/**
 * Created by Manu on 11/7/2017.
 */
class UserListActivity: AppCompatActivity() {
    internal var helper = DatabaseHandler(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_userlist)

       var listofData: ArrayList<UserInfo> = helper.listOfUserInfo()

//        for(userInfo in listofData){
//            Log.e("Name===> ", userInfo.name)
//        }

        var listView = findViewById(R.id.listView) as ListView
        var listAdapter = UserListAdapter(this, listofData)
        listView.adapter = listAdapter


        listView.setOnItemClickListener { parent, view, position, id ->

            var intent = Intent(this, AddActivity::class.java)
            intent.putExtra("ADD", false)
            intent.putExtra("ID",listofData[position].id)
            startActivity(intent)
        }
    }
}