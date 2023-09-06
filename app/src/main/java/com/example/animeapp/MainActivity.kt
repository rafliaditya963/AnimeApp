package com.example.animeapp

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvAnime: RecyclerView
    private val list = ArrayList<Anime>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvAnime = findViewById(R.id.rv_anime)
        rvAnime.setHasFixedSize(true)

        list.addAll(getListAnimes())
        showRecyclerList()
    }

    private fun getListAnimes(): ArrayList<Anime> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        val listAnime = ArrayList<Anime>()

        val actionBar = supportActionBar
        actionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this, R.color.brown)))

        for (i in dataName.indices) {
            val Anime = Anime(dataName[i], dataDescription[i], dataPhoto[i])
            listAnime.add(Anime)
        }
        return listAnime
    }

    private fun showRecyclerList() {
        rvAnime.layoutManager = LinearLayoutManager(this)
        val listAnimeAdapter = List_Anime_Adapter(list)
        rvAnime.adapter = listAnimeAdapter

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_profile -> {
                startActivity(Intent(this, Halaman_About::class.java))
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}