package com.example.timitonicapp.view.adapter

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.timitonicapp.databinding.ItemBookBinding
import com.example.timitonicapp.view.response.OwnerPrefs

class BooksViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemBookBinding.bind(view)

    fun bind(ownerPrefs: OwnerPrefs) {
        binding.tvBooks.text = ownerPrefs.title
        //Glide library to work with images
        if (ownerPrefs.oCoverImage != null) {
            val imageUrl = "https://timetonic.com/live/${ownerPrefs.oCoverImage}"
            Log.i("Frey", ownerPrefs.oCoverImage.toString())
            val newUrl = imageUrl.replace("/dev", "")
            Log.i("Frey", "${newUrl.toString()}")
            Glide.with(itemView.context).load(newUrl).into(binding.ivBooks)
        } else {
            Log.i("Frey", "ES NULA")
        }

    }
}
//https://timetonic.com/live/dbi/in/tb/FU-1701419839-65699b3f78400/modele-suivi-projet.jpg