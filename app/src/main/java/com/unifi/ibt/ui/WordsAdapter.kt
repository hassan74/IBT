package com.unifi.ibt.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.unifi.ibt.R
import com.unifi.ibt.models.Word
import com.unifi.ibt.utils.filter

class WordsAdapter(var words: ArrayList<Word>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    Filterable {
    lateinit var wordsListFiltered: ArrayList<Word>

    init {
        wordsListFiltered = words
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return WordsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_word_count, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        with(holder as WordsViewHolder) {
            tvWordCount.text = wordsListFiltered[position].count.toString()
            tvWordName.text = wordsListFiltered[position].name
        }
    }

    override fun getItemCount(): Int {
        return wordsListFiltered.size
    }

    class WordsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvWordName: TextView = view.findViewById<TextView>(R.id.tvWordName)
        var tvWordCount = view.findViewById<TextView>(R.id.tvWordCount)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                wordsListFiltered = words.filter(constraint.toString())
                val filterResults = FilterResults()
                filterResults.values = wordsListFiltered
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                wordsListFiltered = results?.values as ArrayList<Word>
                notifyDataSetChanged()

            }

        }
    }

}