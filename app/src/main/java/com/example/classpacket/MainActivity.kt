package com.example.classpacket


import android.os.AsyncTask
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import com.example.classpacket.IP.IP
import com.example.classpacket.databinding.ActivityMainBinding
import com.example.classpacket.home.HomeFragment
import com.example.classpacket.login.LoginFragment
import com.example.classpacket.IP.IPActivity
import com.example.classpacket.menu.AboutFragment
import com.example.classpacket.menu.FAQFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.json.JSONObject
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavView: BottomNavigationView
    lateinit var binding: ActivityMainBinding
    lateinit var toggle: ActionBarDrawerToggle
    val CITY: String = "Caloocan, PH"
    val API: String = "73951ad3a1285b6f39e03753e4d96a6f"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.apply {
            toggle= ActionBarDrawerToggle(this@MainActivity,drawerLayout,R.string.app_name,R.string.app_name)
            drawerLayout.addDrawerListener(toggle)
            toggle.syncState()

            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            val loginFragment = LoginFragment()
            val homeFragment = HomeFragment()

            navView.setNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.nav_drawer_home -> {
                        setThatFragment(homeFragment)
                    }
                    R.id.nav_drawer_logout -> {
                        setThatFragment(loginFragment)
                    }
                }
                true
            }

        }

        bottomNavView = binding.bottomNavView

        val homeFragment = HomeFragment()
        val ip = IP()

        setThatFragment(homeFragment)

        bottomNavView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.bottom_nav_home -> {setThatFragment(homeFragment)}
                R.id.bottom_nav_packet -> {setThatFragment(ip)}
            }
            true
        }
    weatherTask().execute()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val aboutFragment = AboutFragment()
        val faqFragment = FAQFragment()
        if (toggle.onOptionsItemSelected(item)){
            true
        }

        when (item.itemId){
            R.id.options_about -> {setThatFragment(aboutFragment)}
            R.id.options_faq -> {setThatFragment(faqFragment)}
        }

        return super.onOptionsItemSelected(item)
    }

    private fun setThatFragment(fragment : Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame,fragment)
            commit()
        }

    inner class weatherTask() : AsyncTask<String, Void, String>() {
        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun doInBackground(vararg params: String?): String? {
            var response:String?
            try{
                response = URL("https://api.openweathermap.org/data/2.5/weather?q=$CITY&units=metric&appid=$API").readText(
                    Charsets.UTF_8
                )
            }catch (e: Exception){
                response = null
            }
            return response
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            try {
                /* Extracting JSON returns from the API */
                val jsonObj = JSONObject(result)
                val main = jsonObj.getJSONObject("main")
                val sys = jsonObj.getJSONObject("sys")
                val wind = jsonObj.getJSONObject("wind")
                val weather = jsonObj.getJSONArray("weather").getJSONObject(0)

                val updatedAt:Long = jsonObj.getLong("dt")
                val updatedAtText = "Updated at: "+ SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.ENGLISH).format(
                    Date(updatedAt*1000)
                )
                val temp = main.getString("temp")+"°C"
                val tempMin = "Min Temp: " + main.getString("temp_min")+"°C"
                val tempMax = "Max Temp: " + main.getString("temp_max")+"°C"
                val pressure = main.getString("pressure")
                val humidity = main.getString("humidity")

                val sunrise:Long = sys.getLong("sunrise")
                val sunset:Long = sys.getLong("sunset")
                val windSpeed = wind.getString("speed")
                val weatherDescription = weather.getString("description")

                val address = jsonObj.getString("name")+", "+sys.getString("country")

                /* Populating extracted data into our views */
                findViewById<TextView>(R.id.address).text = address
                findViewById<TextView>(R.id.updated_at).text =  updatedAtText
                findViewById<TextView>(R.id.status).text = weatherDescription.capitalize()
                findViewById<TextView>(R.id.temp).text = temp
                findViewById<TextView>(R.id.temp_min).text = tempMin
                findViewById<TextView>(R.id.temp_max).text = tempMax
                findViewById<TextView>(R.id.sunrise).text = SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(
                    Date(sunrise*1000)
                )
                findViewById<TextView>(R.id.sunset).text = SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(
                    Date(sunset*1000)
                )
                findViewById<TextView>(R.id.wind).text = windSpeed
                findViewById<TextView>(R.id.pressure).text = pressure
                findViewById<TextView>(R.id.humidity).text = humidity
                findViewById<RelativeLayout>(R.id.mainContainer).visibility = View.VISIBLE

            } catch (e: Exception) {

            }

        }
    }
}