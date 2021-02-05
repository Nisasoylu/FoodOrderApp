package com.example.foodorderapp

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_yemek_sepet.view.*

class SepetAdapter(var mContext: Context, var SepetListe: List<Sepet>)
    : RecyclerView.Adapter<SepetAdapter.CardTasarimTutucu>() {

    inner class CardTasarimTutucu(tasarim: View) : RecyclerView.ViewHolder(tasarim) {
        var satirCardView: CardView
        var satirYemekAdet: TextView
        var satirYemekAd: TextView
        var satirYemekFiyat: TextView
        var satirSil: ImageView

        init {
            satirCardView = tasarim.findViewById(R.id.satirCardView)
            satirYemekAdet = tasarim.findViewById(R.id.satirYemekAdet)
            satirYemekAd = tasarim.findViewById(R.id.satirYemekAd)
            satirYemekFiyat = tasarim.findViewById(R.id.satirYemekFiyat)
            satirSil = tasarim.findViewById(R.id.satirSil)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val tasarim = LayoutInflater.from(mContext).inflate(R.layout.satir_tasarim, parent, false)

        return CardTasarimTutucu(tasarim)
    }

    override fun getItemCount(): Int {
        return SepetListe.size
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        var sepet_yemek = SepetListe.get(position)


        holder.satirYemekAdet.text = sepet_yemek.yemek_siparis_adet.toString()
        holder.satirYemekAd.text = sepet_yemek.yemek_adi
        holder.satirYemekFiyat.text = (sepet_yemek.yemek_fiyat * sepet_yemek.yemek_siparis_adet).toString()



        holder.satirSil.setOnClickListener {
            Toast.makeText(mContext, "${sepet_yemek.yemek_adi} silindi", Toast.LENGTH_SHORT).show()

            val url = "http://kasimadalan.pe.hu/yemekler/delete_sepet_yemek.php"

            val istek = object : StringRequest(Request.Method.POST, url, Response.Listener { cevap ->
                Log.e("Silindi", cevap)

                /////////////////sepetteki_yemekler()

            }, Response.ErrorListener { Log.e("Sil", "Hata") }) {
                override fun getParams(): MutableMap<String, String> {
                    val params = HashMap<String, String>()
                    params["yemek_id"] = (sepet_yemek.yemek_id).toString()
                    return params
                }
            }

            Volley.newRequestQueue(mContext).add(istek)

            val intent2 = Intent(mContext, YemekSepetActivity::class.java)
            mContext.startActivity(intent2)


        }

        holder.satirCardView.setOnClickListener {
            val intent = Intent(mContext, YemekSepetActivity::class.java)
            intent.putExtra("sepetNesne", sepet_yemek)
            mContext.startActivity(intent)
        }


    }

}