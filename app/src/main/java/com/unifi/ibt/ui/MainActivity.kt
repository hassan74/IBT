package com.unifi.ibt.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.unifi.ibt.R
import com.unifi.ibt.data.CachedLocalDataSource
import com.unifi.ibt.data.IWordRepository
import com.unifi.ibt.data.RemoteDataSource
import com.unifi.ibt.data.WordRepository
import com.unifi.ibt.databinding.ActivityMainBinding
import com.unifi.ibt.models.Word
import com.unifi.ibt.network.NetworkConnection
import com.unifi.ibt.utils.checkInternetConnection
import com.unifi.ibt.utils.sortWords
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() ,
    androidx.appcompat.widget.SearchView.OnQueryTextListener {
    var words = ArrayList<Word>()
    lateinit var wordsViewModel: MainViewModel
    lateinit var binding: ActivityMainBinding
    lateinit var wordsAdapter: WordsAdapter
    var sortAsc = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.rvWord.layoutManager = LinearLayoutManager(this)
        wordsAdapter = WordsAdapter(words)
        binding.rvWord.adapter = wordsAdapter
        wordsViewModel = initWordsViewModel()
        showLoading()
        showMessage()
        sortAscDsc()
        searchWords()
        getWords()

    }

    private fun searchWords() {
        binding.include.searchWord.setOnQueryTextListener(this)
    }

    private fun sortAscDsc() {
        binding.include.sortList.setOnClickListener {
            words.sortWords(sortAsc)
            sortAsc = sortAsc.not()
            binding.rvWord.adapter?.notifyDataSetChanged()
        }
    }

    private fun getWords() {
        wordsViewModel.getAllWords(checkInternetConnection())
        wordsViewModel.wordsResult.observe(this, Observer {
            words.addAll(it)
            binding.rvWord.adapter?.notifyDataSetChanged()
        })
    }

    private fun initWordsViewModel(): MainViewModel {
        //Initialize objects Since we don't use third party libraries like dagger or hilt
        val sharedPref = getPreferences(MODE_PRIVATE)

        val networkConnection = NetworkConnection()
        val remoteDataSource = RemoteDataSource(networkConnection)
        val localDataSource = CachedLocalDataSource(sharedPref)
        val executor = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())
        val wordRepository: IWordRepository =
            WordRepository(
                remoteDataSource, localDataSource, executor, handler
            )
        return MainViewModel(wordRepository)
    }

    private fun showLoading() {
        wordsViewModel.showLoading.observe(this, {
            if (it) binding.progressBar.visibility = View.VISIBLE
            else binding.progressBar.visibility = View.GONE
        })
    }

    private fun showMessage() {
        wordsViewModel.showMessage.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        wordsAdapter.filter.filter(query)
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        wordsAdapter.filter.filter(newText)
        return false
    }

}