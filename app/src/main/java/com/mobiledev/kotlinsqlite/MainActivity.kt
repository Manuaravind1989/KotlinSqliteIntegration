package com.mobiledev.kotlinsqlite

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mobiledev.kotlinsqlite.db.DatabaseHandler
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    internal var helper = DatabaseHandler(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addButton.setOnClickListener {
            var intent = Intent(this, AddActivity::class.java)
            intent.putExtra("ADD", true)
            startActivity(intent)
        }
        viewButton.setOnClickListener {
            var intent = Intent(this, UserListActivity::class.java)
            startActivity(intent)
        }
    }
}