package com.example.animeapp

import android.content.Intent
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.content.ContextCompat

class Halaman_About : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_halaman_about)

        val actionBar = supportActionBar
        val upArrow = resources.getDrawable(R.drawable.baseline_arrow_back_ios_24) // Ganti dengan nama file gambar Anda
        upArrow.setColorFilter(resources.getColor(R.color.orange_light), PorterDuff.Mode.SRC_ATOP)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(upArrow)
        actionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this, R.color.brown)))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}