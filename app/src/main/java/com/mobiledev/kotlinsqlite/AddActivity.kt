package com.mobiledev.kotlinsqlite

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.mobiledev.kotlinsqlite.db.DatabaseHandler
import kotlinx.android.synthetic.main.activity_add.*

/**
 * Created by Manu on 11/7/2017.
 */
class AddActivity: AppCompatActivity() {
    internal var helper = DatabaseHandler(this)
    var isAdd : Boolean =  false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        isAdd = intent.getBooleanExtra("ADD",true);
      //  Toast.makeText(this@AddActivity, ""+isAdd, Toast.LENGTH_SHORT).show()

        addData()
    }

    fun validate(): Boolean {
        if(nameTV.text.isEmpty()){
            Toast.makeText(this@AddActivity, "Enter Name", Toast.LENGTH_SHORT).show()
            return false
        }
        else if(ageTV.text.isEmpty()){
            Toast.makeText(this@AddActivity, "Enter Age", Toast.LENGTH_SHORT).show()
            return false
        }
        else if(phoneTV.text.isEmpty()){
            Toast.makeText(this@AddActivity, "Enter Phone", Toast.LENGTH_SHORT).show()
            return false
        }
        else if(emailTV.text.isEmpty()){
            Toast.makeText(this@AddActivity, "Enter Email", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    fun addData() {
        addButton.setOnClickListener {
            if(validate()) {
                helper.insertData(nameTV.text.toString(),
                        ageTV.text.toString(),
                        phoneTV.text.toString(), emailTV.text.toString())
                clearAllFields()
            }
        }
    }


    fun clearAllFields(){
        nameTV.text.clear();
        ageTV.text.clear();
        phoneTV.text.clear();
        emailTV.text.clear();
    }
}