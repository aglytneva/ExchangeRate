package com.example.exchangerate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.example.exchangerate.favoriteCurrency.ui.FavoriteFragment
import com.example.exchangerate.maincurrency.ui.MainFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

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
                R.id.itemBookmarks -> {
                    if (bottomNavMenu.selectedItemId != it.itemId) {
                        selectTab(FavoriteFragment())
                    }
                }
                else -> {selectTab(MainFragment())}
            }
            true
        }

    }

    private fun selectTab(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
    }

}