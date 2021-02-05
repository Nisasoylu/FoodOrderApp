package com.example.foodorderapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_giris.*

class Giris : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_giris)

        arrow.setOnClickListener {
            val intent = Intent(this@Giris, MainActivity::class.java)
            startActivity(intent)
        }

    }
    override fun onBackPressed() {
        val intent3 = Intent(Intent.ACTION_MAIN)
        intent3.addCategory(Intent.CATEGORY_HOME)
        intent3.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent3)
    }
}
