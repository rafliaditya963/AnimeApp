package com.example.animeapp

import android.content.Intent
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide


class Halaman_Detail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_halaman_detail)

        val actionBar = supportActionBar
        val upArrow = resources.getDrawable(R.drawable.baseline_arrow_back_ios_24) // Ganti dengan nama file gambar Anda
        upArrow.setColorFilter(resources.getColor(R.color.orange_light), PorterDuff.Mode.SRC_ATOP)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(upArrow)
        actionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this, R.color.brown)))

        val tvDetailName : TextView = findViewById(R.id.tv_detail_name)
        val tvDetailDescription  : TextView = findViewById(R.id.tv_detail_description)
        val ivDetailPhoto: ImageView  = findViewById(R.id.iv_detail_photo)

        val dataAnime = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Anime>("key_Anime", Anime::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Anime>("key_Anime")
        }

        tvDetailName.text = dataAnime?.name
        tvDetailDescription.text = dataAnime?.description
        if (dataAnime?.photo != null) {
            Glide.with(this)
                .load(dataAnime.photo) // Assuming dataAnime.photo is a URL or drawable resource
                .into(ivDetailPhoto)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main2, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_profile -> {
                startActivity(Intent(this, Halaman_About::class.java))
                return true
            }
            android.R.id.home -> {
                finish()
                return true
            }
            R.id.berbagi -> {

                val dataAnime = if (Build.VERSION.SDK_INT >= 33) {
                    intent.getParcelableExtra<Anime>("key_Anime", Anime::class.java)
                } else {
                    @Suppress("DEPRECATION")
                    intent.getParcelableExtra<Anime>("key_Anime")
                }

                val imageUrl = dataAnime?.photo.toString()

                val shareIntent1 = Intent(Intent.ACTION_SEND)
                shareIntent1.type = "text/plain"
                shareIntent1.putExtra(Intent.EXTRA_TEXT, imageUrl+"\n"+dataAnime?.name+"\n"+dataAnime?.description+"\n")
                startActivity(Intent.createChooser(shareIntent1, "Bagikan"))

                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}