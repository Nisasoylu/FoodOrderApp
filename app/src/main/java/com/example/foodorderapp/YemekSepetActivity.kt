package com.example.foodorderapp

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_yemek_detaylar.*
import kotlinx.android.synthetic.main.activity_yemek_sepet.*
import kotlinx.android.synthetic.main.satir_tasarim.*
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList

public class YemekSepetActivity : AppCompatActivity() {

    private lateinit var sepetList: ArrayList<Sepet>
    private lateinit var adapter: SepetAdapter

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_yemek_sepet)

        toolbarSepet.title = "Sepetteki Ürünler"
        setSupportActionBar(toolbarSepet)

        rv2.setHasFixedSize(true)
        rv2.layoutManager = LinearLayoutManager(this@YemekSepetActivity)
        sepetteki_yemekler()

        buttonSepetiOnayla.setOnClickListener {
            if (!(sepetList.isEmpty())) {
                val intent = Intent(this@YemekSepetActivity, SepetOnayActivity::class.java)
                startActivity(intent)
            } else {
                val intentt = Intent(this@YemekSepetActivity, SepetBosActivity::class.java)
                startActivity(intentt)
            }

        }



        editTextSaat.setOnClickListener {
            val cal = Calendar.getInstance()
            val saat = cal.get(Calendar.HOUR_OF_DAY)
            val dakika = cal.get(Calendar.MINUTE)

            val timep = TimePickerDialog(this@YemekSepetActivity, TimePickerDialog.OnTimeSetListener { view, s, dk ->

                editTextSaat.setText("$s : $dk")

            }, saat, dakika, true)

            timep.setTitle("Saat seçiniz")
            timep.setButton(DialogInterface.BUTTON_POSITIVE, "AYARLA", timep)
            timep.setButton(DialogInterface.BUTTON_NEGATIVE, "İPTAL", timep)

            timep.show()

        }

        editTextTarih.setOnClickListener {
            val cal = Calendar.getInstance()
            val yil = cal.get(Calendar.YEAR)
            val ay = cal.get(Calendar.MONTH)
            val gun = cal.get(Calendar.DAY_OF_MONTH)

            val datep = DatePickerDialog(this@YemekSepetActivity, DatePickerDialog.OnDateSetListener { view, yil, ay, gun ->

                editTextTarih.setText("$gun/${ay + 1}/$yil")

            }, yil, ay, gun)
            datep.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            datep.setTitle("Saat seçiniz")
            datep.setButton(DialogInterface.BUTTON_POSITIVE, "AYARLA", datep)
            datep.setButton(DialogInterface.BUTTON_NEGATIVE, "İPTAL", datep)

            datep.show()
        }

    }

    fun sepetteki_yemekler() {
        val url = "http://kasimadalan.pe.hu/yemekler/tum_sepet_yemekler.php"
        var sepetTutar: Int = 0
        val istek = StringRequest(Request.Method.GET, url, Response.Listener { cevap ->
            //Log.e("Gelen yanıt",cevap)

            try {
                sepetList = ArrayList()
                val jsonObject = JSONObject(cevap)
                val tum_yemekler = jsonObject.getJSONArray("sepet_yemekler")

                for (i in 0 until tum_yemekler.length()) {
                    val y = tum_yemekler.getJSONObject(i)

                    val sepet_yemek = Sepet(y.getInt("yemek_id"),
                            y.getString("yemek_adi"),
                            y.getString("yemek_resim_adi"),
                            y.getInt("yemek_fiyat"),
                            y.getInt("yemek_siparis_adet"))
                    sepetTutar += (sepet_yemek.yemek_fiyat * sepet_yemek.yemek_siparis_adet)
                    sepetList.add(sepet_yemek)
                }
                textViewToplamTutar.setText(sepetTutar.toString() + " ₺")

                adapter = SepetAdapter(this@YemekSepetActivity, sepetList)

                rv2.adapter = adapter

            } catch (e: Exception) {
                e.printStackTrace()
            }

        }, Response.ErrorListener { Log.e("HATA", "Veri okuma") })

        Volley.newRequestQueue(this@YemekSepetActivity).add(istek)
    }

    override fun onBackPressed() {
        val intent3 = Intent(this@YemekSepetActivity, MainActivity::class.java)
        startActivity(intent3)
    }

}