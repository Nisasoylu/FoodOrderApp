package com.example.foodorderapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class YemeklerAdapter(var mContext:Context, var yemeklerListe:List<Yemekler>)
    :RecyclerView.Adapter<YemeklerAdapter.CardTasarimTutucu>(){

    inner class CardTasarimTutucu(tasarim: View): RecyclerView.ViewHolder(tasarim){
        var yemek_kart: CardView
        var textViewYemekAd: TextView
        var imageViewYemekResim: ImageView

        init {
            yemek_kart = tasarim.findViewById(R.id.yemek_kart)
            textViewYemekAd = tasarim.findViewById(R.id.textViewYemekAd)
            imageViewYemekResim = tasarim.findViewById(R.id.imageViewYemekResim)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val tasarim = LayoutInflater.from(mContext).inflate(R.layout.yemek_kart_tasarim,parent,false)
        return CardTasarimTutucu(tasarim)
    }

    override fun getItemCount(): Int {
        return yemeklerListe.size
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        val yemek = yemeklerListe.get(position)

        holder.textViewYemekAd.text = yemek.yemek_adi

        val resimler_url = "http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemek_resim_adi}"

        Picasso.get().load(resimler_url).into(holder.imageViewYemekResim)

        holder.yemek_kart.setOnClickListener {

            val intent = Intent(mContext, YemekDetaylarActivity::class.java)
            intent.putExtra("yemekNesne", yemek)
            mContext.startActivity(intent)


        }
    }

}