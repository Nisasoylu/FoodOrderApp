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

        sertifika.setText("Lokantamız 40 saatlik eğitimin ardından sahada bakanlık tarafından hazırlanan genelgeye uygun bulunarak ve süreci başarıyla bitirerek ‘’Uluslararası GT Hijyen Sertifikası’’ ve ‘’Firma logolu GT Cardcert Kart’’ı almaya hak kazandı.")
        hizli.setText("Siparişiniz lokantamızdan çıktıktan sonra en geç 30 dk içinde kapınızda.")
        sicak.setText("Lokantamızdan çıkan siparişiniz özel çantalarımızla taşınarak, tüm sıcaklığı ile kapınıza ulaştırılır.")
        tamsure.setText("7/24 hizmet ile ne zaman acıkırsanız kapınızda.")

    }
}