package com.unifi.ibt.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.unifi.ibt.R
import com.unifi.ibt.data.CachedLocalDataSource
import com.unifi.ibt.data.RemoteDataSource
import com.unifi.ibt.models.Word
import com.unifi.ibt.network.NetworkConnection
import com.unifi.ibt.data.IWordRepository
import com.unifi.ibt.data.WordRepository
import com.unifi.ibt.databinding.ActivityMainBinding
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    var words=ArrayList<Word>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      //  setContentView(R.layout.activity_main)
        var binding :ActivityMainBinding=DataBindingUtil.setContentView(this ,R.layout.activity_main)
        val networkConnection = NetworkConnection()
        val remoteDataSource = RemoteDataSource(networkConnection)
        val localDataSource = CachedLocalDataSource()
        val executor = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())
        val wordRepository: IWordRepository =
            WordRepository(
                remoteDataSource, localDataSource, executor, handler
            )
        val mainViewModel = MainViewModel(wordRepository)
        mainViewModel.getAllWords()
        binding.rvWord.layoutManager=LinearLayoutManager(this)
        binding.rvWord.adapter=WordsAdapter(words)
        mainViewModel.wordsResult.observe(this , Observer {
            words.addAll(it)
            binding.rvWord.adapter?.notifyDataSetChanged()
        })

    }

}