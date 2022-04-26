package com.example.dictionary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        val imageView = findViewById<ImageView>(R.id.icon_splash_screen)
        val fragment =findViewById<View>(R.id.nav_host_fragment)
        imageView.alpha=0f
        imageView.animate().setDuration(3500).alpha(1f).withEndAction {
            fragment.visibility= View.VISIBLE
            imageView.visibility=View.GONE
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
        }
    }
}