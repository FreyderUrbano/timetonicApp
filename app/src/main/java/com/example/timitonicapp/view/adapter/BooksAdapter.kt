package com.example.timitonicapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.timitonicapp.R
import com.example.timitonicapp.view.response.BooksDataResponse
import com.example.timitonicapp.view.response.OwnerPrefs

//build the adapter to calls BooksViewHolder
class BooksAdapter(private var booksList: List<OwnerPrefs> = emptyList()) :
    RecyclerView.Adapter<BooksViewHolder>() {

        fun updatList (newbookslist: List<OwnerPrefs>){
            this.booksList = newbookslist
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        return BooksViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
        )
    }

    override fun onBindViewHolder(viewHolder: BooksViewHolder, position: Int) {
        viewHolder.bind(booksList[position])
    }

    override fun getItemCount() = booksList.size
}