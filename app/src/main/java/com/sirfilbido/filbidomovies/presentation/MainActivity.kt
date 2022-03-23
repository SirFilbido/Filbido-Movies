package com.sirfilbido.filbidomovies.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sirfilbido.filbidomovies.R
import com.sirfilbido.filbidomovies.presentation.ui.main.HomeFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, HomeFragment.newInstance())
                .commitNow()
        }
    }
}