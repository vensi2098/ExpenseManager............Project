package com.example.expensemanager.allactivity.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.expensemanager.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val intent = Intent(this@MainActivity, ExpenseMANAgerActivity1::class.java)
        Handler().postDelayed({
            startActivity(intent)
            finish()
        }, 1000)


    }
}