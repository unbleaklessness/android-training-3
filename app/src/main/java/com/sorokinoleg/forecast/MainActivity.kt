package com.sorokinoleg.forecast

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
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
                    showFragment(TodayFragment())
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
    }

    private fun showFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(fragmentContainer, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
