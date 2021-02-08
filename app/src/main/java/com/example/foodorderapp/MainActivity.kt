package com.example.foodorderapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.SearchView
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import org.json.JSONObject

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var yemekList: ArrayList<Yemekler>
    private lateinit var adapter: YemeklerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbarMain.title = "Yemekler"
        setSupportActionBar(toolbarMain)


        rv.setHasFixedSize(true)
        rv.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        tumYemekler()

        sepetegit.setOnClickListener {
            val intent11 = Intent(this@MainActivity, YemekSepetActivity::class.java)
            startActivity(intent11)
        }
        infoyagit.setOnClickListener {
            val intent12 = Intent(this@MainActivity, Hakkimizda::class.java)
            startActivity(intent12)
        }
        iletişimegit.setOnClickListener {
            val intent13 = Intent(this@MainActivity, iletisimBilgileri::class.java)
            startActivity(intent13)
        }

    }

    // Inflater metodu res altındaki tasarımsal ürünleri kullanmamız
    // için koda aktarır.
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_search_menu, menu)

        val item = menu.findItem(R.id.action_ara)
        val searchView = item.actionView as SearchView
        searchView.setOnQueryTextListener(this)

        return super.onCreateOptionsMenu(menu)
    }

    //Aranacak şey yazılıp arama butonuna basılınca
    override fun onQueryTextSubmit(query: String): Boolean {
        yemekAra(query)
        return true
    }

    //Her harf yazıldığında
    override fun onQueryTextChange(newText: String): Boolean {
        yemekAra(newText)
        return true
    }

    fun tumYemekler() {
        val url = "http://kasimadalan.pe.hu/yemekler/tum_yemekler.php"

        val istek = StringRequest(Request.Method.GET, url, Response.Listener { cevap ->
            //Log.e("Gelen yanıt",cevap)

            try {
                yemekList = ArrayList()
                val jsonObject = JSONObject(cevap)
                val tum_yemekler = jsonObject.getJSONArray("yemekler")

                for (i in 0 until tum_yemekler.length()) {
                    val y = tum_yemekler.getJSONObject(i)

                    val yemek = Yemekler(y.getInt("yemek_id"),
                            y.getString("yemek_adi"),
                            y.getString("yemek_resim_adi"),
                            y.getInt("yemek_fiyat"))

                    yemekList.add(yemek)
                }

                adapter = YemeklerAdapter(this@MainActivity, yemekList)

                rv.adapter = adapter

            } catch (e: Exception) {
                e.printStackTrace()
            }

        }, Response.ErrorListener { Log.e("HATA", "Veri okuma") })

        Volley.newRequestQueue(this@MainActivity).add(istek)
    }

    fun yemekAra(aramaKelime: String) {
        val url = "http://kasimadalan.pe.hu/yemekler/tum_yemekler_arama.php"

        val istek = object : StringRequest(Request.Method.POST, url, Response.Listener { cevap ->
            //Log.e("Gelen yanıt",cevap)

            try {
                yemekList.clear()
                val jsonObject = JSONObject(cevap)
                val tum_yemekler = jsonObject.getJSONArray("yemekler")

                for (i in 0 until tum_yemekler.length()) {
                    val y = tum_yemekler.getJSONObject(i)

                    val yemek = Yemekler(y.getInt("yemek_id"),
                            y.getString("yemek_adi"),
                            y.getString("yemek_resim_adi"),
                            y.getInt("yemek_fiyat"))

                    yemekList.add(yemek)
                }

                //adapter = YemeklerAdapter(this@MainActivity, yemekList)

                //rv.adapter = adapter
                adapter.notifyDataSetChanged()

            } catch (e: Exception) {
                e.printStackTrace()
            }

        }, Response.ErrorListener { Log.e("HATA", "Veri okuma") }) {
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["yemek_adi"] = aramaKelime
                return params
            }
        }
        Volley.newRequestQueue(this@MainActivity).add(istek)
    }

    override fun onBackPressed() {
        val intent3 = Intent(this@MainActivity, Giris::class.java)
        startActivity(intent3)

    }
}