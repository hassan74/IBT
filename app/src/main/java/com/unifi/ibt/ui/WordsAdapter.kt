package com.unifi.ibt.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.unifi.ibt.R
import com.unifi.ibt.models.Word

class WordsAdapter(var words: ArrayList<Word>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return WordsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_word_count,parent,false  )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        with(holder as WordsViewHolder) {
            tvWordCount.text=words[position].count.toString()
            tvWordName.text=words[position].name
        }
    }

    override fun getItemCount(): Int {
        return words.size
    }

    class WordsViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var tvWordName =view.findViewById<TextView>(R.id.tvWordName)
        var tvWordCount =view.findViewById<TextView>(R.id.tvWordCount)
    }
}