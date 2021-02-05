package com.example.foodorderapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SepetOnayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sepet_onay)
    }

    override fun onBackPressed() {
        val intent3 = Intent(this@SepetOnayActivity, MainActivity::class.java)
        startActivity(intent3)
    }
}