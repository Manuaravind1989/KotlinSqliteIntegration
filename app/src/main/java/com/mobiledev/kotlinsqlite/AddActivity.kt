package com.mobiledev.kotlinsqlite

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.mobiledev.kotlinsqlite.db.DatabaseHandler
import kotlinx.android.synthetic.main.activity_add.*

/**
 * Created by Manu on 11/7/2017.
 */
class AddActivity : AppCompatActivity() {
    internal var helper = DatabaseHandler(this)
    var isAdd: Boolean = false;
    var id: Int = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        isAdd = intent.getBooleanExtra("ADD", true);
        id = intent.getIntExtra("ID", 0)

        if (!isAdd) {
            var user = helper.getParticularUserData("" + id)
            nameTV.setText(user.name)
            ageTV.setText("" + user.age)
            phoneTV.setText("" + user.phone)
            emailTV.setText(user.email)
            deleteButton.setVisibility(View.VISIBLE)
        }

        addData()
    }

    fun validate(): Boolean {
        if (nameTV.text.isEmpty()) {
            Toast.makeText(this@AddActivity, "Enter Name", Toast.LENGTH_SHORT).show()
            return false
        } else if (ageTV.text.isEmpty()) {
            Toast.makeText(this@AddActivity, "Enter Age", Toast.LENGTH_SHORT).show()
            return false
        } else if (phoneTV.text.isEmpty()) {
            Toast.makeText(this@AddActivity, "Enter Phone", Toast.LENGTH_SHORT).show()
            return false
        } else if (emailTV.text.isEmpty()) {
            Toast.makeText(this@AddActivity, "Enter Email", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    fun addData() {
        addButton.setOnClickListener {
            if (validate()) {
                if(isAdd) {
                    helper.insertData(nameTV.text.toString(),
                            ageTV.text.toString(),
                            phoneTV.text.toString(), emailTV.text.toString())
                }else{
                    helper.updateData(""+id,nameTV.text.toString(),
                            ageTV.text.toString(),
                            phoneTV.text.toString(), emailTV.text.toString())
                }
                clearAllFields()
                finish()
            }
        }
        deleteButton.setOnClickListener {

            helper.deleteData(""+id)
        }
    }


    fun clearAllFields() {
        nameTV.text.clear();
        ageTV.text.clear();
        phoneTV.text.clear();
        emailTV.text.clear();
    }
}