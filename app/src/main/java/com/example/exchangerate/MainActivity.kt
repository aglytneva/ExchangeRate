package com.example.exchangerate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val bottomNavMenu: BottomNavigationView by lazy { findViewById(R.id.bnvBar) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.container, MainFragment())
            .commit()
        bottomNavMenu.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.itemMain -> {
                    if (bottomNavMenu.selectedItemId != it.itemId) {
                        selectTab(MainFragment())
                    }
                }
                R.id.itemBookmarks -> {
                    if (bottomNavMenu.selectedItemId != it.itemId) {
                        selectTab(MainFragment())
                    }
                }
                else -> null
            }
            true
        }

    }

    private fun selectTab(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
    }
}