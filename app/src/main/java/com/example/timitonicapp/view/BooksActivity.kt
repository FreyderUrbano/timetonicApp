package com.example.timitonicapp.view

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.timitonicapp.R
import com.example.timitonicapp.databinding.ActivityBooksBinding
import com.example.timitonicapp.view.response.BooksDataResponse
import com.example.timitonicapp.view.service.ApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BooksActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBooksBinding
    private lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBooksBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofit = getRetrofit()
        beginUI()
        getAllBooks(query = String())

    }



    private fun beginUI() {
        binding.btnBack.setOnClickListener {
            val back = Intent(this, MainActivity::class.java)
            startActivity(back)
        }


    }

    private fun getAllBooks(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val service = retrofit.create(ApiService::class.java).getAllBooks()
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