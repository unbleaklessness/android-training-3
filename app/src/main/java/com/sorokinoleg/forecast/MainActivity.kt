package com.sorokinoleg.forecast

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val fragmentContainer = R.id.container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showFragment(TodayFragment())

        bottomNavigationView.setOnNavigationItemSelectedListener OnNavigationItemSelectedListener@{
            when (it.itemId) {
                R.id.navigationToday -> {
                    val today = TodayFragment()
                    today.arguments = Bundle().apply {
                        putString("city", currentCity.text.toString())
                    }
                    showFragment(today)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigationWeek -> {
                    showFragment(WeekFragment())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigationSettings -> {
                    showFragment(SettingsFragment())
                    return@OnNavigationItemSelectedListener true
                }

            }
            false
        }

        swipeRefresh.setOnRefreshListener {
            val today = TodayFragment()
            today.arguments = Bundle().apply {
                putString("city", currentCity.text.toString())
            }
            showFragment(today)
            swipeRefresh.isRefreshing = false
        }
    }

    private fun showFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(fragmentContainer, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    public fun updateCity(city: String) {
        currentCity.text = city
    }
}
