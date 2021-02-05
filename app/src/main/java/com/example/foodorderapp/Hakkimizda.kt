package com.example.foodorderapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_hakkimizda.*
import kotlinx.android.synthetic.main.activity_main.*

class Hakkimizda : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hakkimizda)

        toolbarHakkimizda.title = "Hakkımızda"
        setSupportActionBar(toolbarHakkimizda)


    }
}