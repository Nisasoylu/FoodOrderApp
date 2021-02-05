package com.example.foodorderapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_yemek_detaylar.*

class YemekDetaylarActivity : AppCompatActivity() {

    private lateinit var yemek: Yemekler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_yemek_detaylar)

        var yemek = intent.getSerializableExtra("yemekNesne") as Yemekler

        buttonSepeteGit.setOnClickListener {
            var intent = Intent(this@YemekDetaylarActivity, YemekSepetActivity::class.java)
            startActivity(intent)
        }

        button_up.setOnClickListener {
            if (text_miktar.text.equals("20")) {
                text_miktar.text = "20"
            } else {
                text_miktar.text = (text_miktar.text.toString().toInt() + 1).toString()
                textViewYemekFiyat.text = (yemek.yemek_fiyat * text_miktar.text.toString().toInt()).toString()
            }
        }

        button_down.setOnClickListener {
            if (text_miktar.text.equals("1")) {
                text_miktar.text = "1"
            } else {
                text_miktar.text = (text_miktar.text.toString().toInt() - 1).toString()
                textViewYemekFiyat.text = (yemek.yemek_fiyat * text_miktar.text.toString().toInt()).toString()
            }
        }

        buttonSepeteEkle.setOnClickListener {
            val yemek_ad: String = textViewYemekAdi.text.toString()
            val yemek_fiyat: String = textViewYemekFiyat.text.toString()
            val yemek_siparis_adet: String = text_miktar.text.toString()

            sepete_yemek_ekle(yemek.yemek_id.toString(), yemek_ad, yemek.yemek_resim_adi, yemek.yemek_fiyat.toString(), yemek_siparis_adet)

        }

        toolbarDetaylar.title = "Yemek Detayları"
        setSupportActionBar(toolbarDetaylar)


        textViewYemekAdi.text = yemek.yemek_adi
        textViewYemekFiyat.text = yemek.yemek_fiyat.toString()

        if (textViewYemekAdi.text.equals("Ayran")) {
            textViewYemekDetayliAnlatim.text = "Buz gibi Sütaş ayran."
        } else if (textViewYemekAdi.text.equals("Baklava")) {
            textViewYemekDetayliAnlatim.text = "Ortalama 40 kat yufka ile hazırlanan cevizli baklavamız, içerisindeki ünlü süt beyaz ceviziyle enfes bir tada sahiptir."
        } else if (textViewYemekAdi.text.equals("Fanta")) {
            textViewYemekDetayliAnlatim.text = "Portakal aromalı gazoz."
        } else if (textViewYemekAdi.text.equals("Izgara Somon")) {
            textViewYemekDetayliAnlatim.text = "Farklı soslarla terbiye edilmiş lezzetli, sulu ve yumuşak balık ızgara."
        } else if (textViewYemekAdi.text.equals("Izgara Tavuk")) {
            textViewYemekDetayliAnlatim.text = "Enfes patates kızaartması ile mangalda ızgara tavuk."
        } else if (textViewYemekAdi.text.equals("Kadayıf")) {
            textViewYemekDetayliAnlatim.text = "Gaziantep fıstığı, sade yağı, irmikli unu ve şekerpancarı şerbeti ile enfes kadayıf tatlısı."
        } else if (textViewYemekAdi.text.equals("Kahve")) {
            textViewYemekDetayliAnlatim.text = "En kaliteli kahve çekirdekleri ve kahve demleme ekipmanları ile hazırlanmış sıcacık kahvemiz sizinle."
        } else if (textViewYemekAdi.text.equals("Köfte")) {
            textViewYemekDetayliAnlatim.text = "Patates kızartması, salata ve pilavı ile enfes İnegöl köfte."
        } else if (textViewYemekAdi.text.equals("Lazanya")) {
            textViewYemekDetayliAnlatim.text = "Peynir, domates sosu ve ragu ile hazırlanmış enfes tad."
        } else if (textViewYemekAdi.text.equals("Makarna")) {
            textViewYemekDetayliAnlatim.text = "Domates soslu ve köfteli enfes Spagetti makarna."
        } else if (textViewYemekAdi.text.equals("Pizza")) {
            textViewYemekDetayliAnlatim.text = "Bol malzemesi ile enfes pizza."
        } else if (textViewYemekAdi.text.equals("Su")) {
            textViewYemekDetayliAnlatim.text = "Buz gibi Sırma su."
        } else if (textViewYemekAdi.text.equals("Sütlaç")) {
            textViewYemekDetayliAnlatim.text = "Enfes kıvamıyla Türk mutfağının vazgeçilmez tatlısı."
        } else if (textViewYemekAdi.text.equals("Tiramisu")) {
            textViewYemekDetayliAnlatim.text = "Özel kreması ve Mascarpone peyniri ile enfes 3 katlı tiramisu."
        }


        val resimler_url = "http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemek_resim_adi}"

        Picasso.get().load(resimler_url).into(imageViewResim)
    }

    fun sepete_yemek_ekle(yemek_id: String, yemek_ad: String, yemek_resim_adi: String, yemek_fiyat: String, yemek_siparis_adet: String) {
        Log.e("Sepete yemek ekleme", "$yemek_id - $yemek_ad - $yemek_resim_adi - $yemek_fiyat - $yemek_siparis_adet")

        val url = "http://kasimadalan.pe.hu/yemekler/insert_sepet_yemek.php"

        val istek = object : StringRequest(Request.Method.POST, url, Response.Listener { cevap ->
            Log.e("Eklenen yemek", cevap)

            //startActivity(Intent(this@YemekDetaylarActivity,MainActivity::class.java))


        }, Response.ErrorListener { Log.e("Ekle", "Hata") }) {
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["yemek_id"] = yemek_id
                params["yemek_adi"] = yemek_ad
                params["yemek_resim_adi"] = yemek_resim_adi
                params["yemek_fiyat"] = yemek_fiyat
                params["yemek_siparis_adet"] = yemek_siparis_adet
                return params
            }
        }
        Volley.newRequestQueue(this@YemekDetaylarActivity).add(istek)
    }
}