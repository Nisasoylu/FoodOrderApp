package com.example.foodorderapp

import java.io.Serializable

class Sepet(var yemek_id:Int, var yemek_adi:String, var yemek_resim_adi:String,
            var yemek_fiyat:Int, var yemek_siparis_adet:Int):Serializable {
}