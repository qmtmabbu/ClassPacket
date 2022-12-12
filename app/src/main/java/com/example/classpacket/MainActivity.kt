package com.example.classpacket


import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import com.example.classpacket.databinding.ActivityMainBinding
import com.example.classpacket.home.HomeFragment
import com.example.classpacket.login.LoginFragment
import com.example.classpacket.IP.IP
import com.example.classpacket.menu.AboutFragment
import com.example.classpacket.menu.FAQFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavView: BottomNavigationView
    lateinit var binding: ActivityMainBinding
    lateinit var toggle: ActionBarDrawerToggle

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
        val IP = IP()

        setThatFragment(homeFragment)

        bottomNavView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.bottom_nav_home -> {setThatFragment(homeFragment)}
                R.id.bottom_nav_packet -> {setThatFragment(IP)}
            }
            true
        }

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

}