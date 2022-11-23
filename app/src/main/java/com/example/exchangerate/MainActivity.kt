package com.example.exchangerate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.androidx.fragment.android.replace

class MainActivity : AppCompatActivity() {

    private val bottomNavMenu: BottomNavigationView by lazy { findViewById(R.id.bnvBar) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        supportFragmentManager.beginTransaction().replace(R.id.container, MainFragment())
            .commit()
        bottomNavMenu.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.itemMain -> {
                    if (bottomNavMenu.selectedItemId != it.itemId) {
                        selectTab(MainFragment())
                    }
                }
//                R.id.itemBookmarks -> {
//                    if (bottomNavMenu.selectedItemId != it.itemId) {
//                        selectTab(MainFragment())
//                    }
//                }
                else -> {}
            }
            true
        }

    }

    private fun selectTab(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
    }
}