package com.example.timitonicapp.view

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.timitonicapp.databinding.ActivityBooksBinding
import com.example.timitonicapp.view.adapter.BooksAdapter
import com.example.timitonicapp.view.service.ApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BooksActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBooksBinding
    private lateinit var retrofit: Retrofit
    private lateinit var adapter: BooksAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBooksBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofit = getRetrofit()
        beginUI()
        getAllBooks(query = String())

    }

    private fun beginUI() {

        adapter = BooksAdapter()
        binding.RVBooks.setHasFixedSize(true)
        binding.RVBooks.layoutManager = LinearLayoutManager(this)
        binding.RVBooks.adapter = adapter

        binding.btnBack.setOnClickListener {
            val back = Intent(this, MainActivity::class.java)
            startActivity(back)
        }
    }

    private fun getAllBooks(query: String) {
        binding.progresBar.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
            val myResponse = retrofit.create(ApiService::class.java).getAllBooks(
                "1.47",
                "getAllBooks",
                "androiddeveloper",
                "androiddeveloper",
                "7iim-Kmlj-YIsS-Pbfc-iKTd-CwNe-QnyK"
            )
            if (myResponse != null) {
                Log.i("Frey", "woks ${myResponse.toString()}")
                runOnUiThread {
                    adapter.updatList(myResponse.allBooks.books.map { it.ownerPrefs })
                    binding.progresBar.isVisible = false
                }
            } else {
                Log.i("Frey", "Dont Works")
            }
        }

    }

    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://timetonic.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun alert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Try Again")

        builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, id ->
            // Acciones a realizar cuando el usuario presiona el botón "OK"
        })
        builder.show()
    }
}