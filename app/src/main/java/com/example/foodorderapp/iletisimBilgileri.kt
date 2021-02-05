package com.example.foodorderapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_iletisim_bilgileri.*
import kotlinx.android.synthetic.main.activity_main.*

class iletisimBilgileri : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_iletisim_bilgileri)

        toolbariletisimBilgileri.title = "İletişim Bilgileri"
        setSupportActionBar(toolbariletisimBilgileri)

        SabitTel.setText("(0216) 504 95 12")
        whatsappHat.setText("0(850) 214 73 06")
        mail.setText("bon_appetit_restaurant@gmail.com")
        webSitesi.setText("bonappetitrestaurant.com.tr")
        adres.setText("Mimar Sinan, Otopark Arkası Sk. no:5, 34672 Üsküdar/İstanbul")

    }
}