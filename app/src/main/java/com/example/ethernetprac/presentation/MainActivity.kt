package com.example.ethernetprac.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ethernetprac.R
import com.example.ethernetprac.presentation.numberFragment.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            val fragment = MainFragment()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainerView, fragment)
                .commit()
        }
    }
}