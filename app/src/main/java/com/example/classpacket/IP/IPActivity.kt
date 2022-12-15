package com.example.classpacket.IP

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.classpacket.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*

class Weather: AppCompatActivity() {
    private var IP: TextView? = null
    private var hostname: TextView? = null
    private var continent: TextView? = null
    private var country: TextView? = null
    private var region: TextView? = null
    private var city: TextView? = null
    private var longtitude: TextView? = null
    private var latitude: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ipactivity)

        IP = findViewById(R.id.IP)
        hostname = findViewById(R.id.hostname)
        continent = findViewById(R.id.continent_code)
        country = findViewById(R.id.country)
        region = findViewById(R.id.region_code)
        city = findViewById(R.id.city)
        longtitude = findViewById(R.id.longtitude)
        latitude = findViewById(R.id.latitude)
        findViewById<View>(R.id.data_button).setOnClickListener {
            getCurrentData()
        }
        val actionbar = supportActionBar
        actionbar!!.title = "Weather"
        actionbar.setDisplayHomeAsUpEnabled(true)
    }

    private fun getCurrentData() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(IPService::class.java)
        val call = service.getCurrentIPData(ip, hostname, APPId)
        call.enqueue(object : Callback<IPResponse> {
            override fun onResponse(
                call: Call<IPResponse>,
                response: Response<IPResponse>
            ) {
                if (response.code() == 200) {
                    val IPResponse = response.body()!!

                    val IP = IPResponse.name + ", " + IPResponse.Sys!!.country
                    val hostname =IPResponse.hostname!!
                    val updatedAt = IPResponse.dt.toLong()
                    val upd = "Updated at: " + SimpleDateFormat(
                        "MM/dd/yyyy hh:mm a",
                        Locale.ENGLISH
                    ).format(Date(updatedAt * 1000))
                    val longtitude = "Min Temp: " + IPResponse.main!!.temp_min + "°C"
                    val latitude = "Max Temp: " + IPResponse.main!!.temp_max + "°C"
                    val humid = IPResponse.main!!.humidity
                    val hpa = IPResponse.main!!.pressure
                    val speed = IPResponse.wind!!.speed

                    IP!!.text = IP
                    hostname!!.text = hostname
                    continent!!.text = tempe
                    country!!.text = tempMin
                    region!!.text = tempMax
                    city!!.text = humid.toString()
                    longtitude!!.text = hpa.toString()
                    latitude!!.text = speed.toString()
                }
            }

            override fun onFailure(call: Call<IPResponse>, t: Throwable) {
                IP!!.text = t.message
                hostname!!.text = t.message
                continent!!.text = t.message
                country!!.text = t.message
                region!!.text = t.message
                city!!.text = t.message
                longtitude!!.text = t.message
                latitude!!.text = t.message
            }
        })
    }

    companion object {
        var BaseUrl = "http://api.ipstack.com/"
        var AppId = "a39376440df76d4010fdb31ce741b04c"
        var lat = "15.44125"
        var lon = "120.72863"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}