package com.unifi.ibt.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.unifi.ibt.R
import com.unifi.ibt.data.IWordRepository
import com.unifi.ibt.data.WordRepository
import com.unifi.ibt.data.localdata.CachedLocalDataSource
import com.unifi.ibt.data.remotedata.RemoteDataSource
import com.unifi.ibt.models.Word
import com.unifi.ibt.network.NetworkConnection
import com.unifi.ibt.utils.checkInternetConnection
import com.unifi.ibt.utils.sortWords
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity(),
    SearchView.OnQueryTextListener, WordsContract {

    var words = ArrayList<Word>()
    lateinit var wordsPresenter: WordsPresenter
    lateinit var wordsAdapter: WordsAdapter
    lateinit var rvWords: RecyclerView
    lateinit var searchVew: SearchView
    lateinit var sortList: ImageView
    lateinit var progress: ProgressBar
    lateinit var retry: Button
    lateinit var layoutError: ConstraintLayout
    lateinit var layoutEmpty: ConstraintLayout
    var sortAsc = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        intiViews()
        initWordsPresenter()
        setClickListeners()
    }

    private fun intiViews() {
        rvWords = findViewById(R.id.rvWord)
        sortList = findViewById(R.id.sort_list)
        searchVew = findViewById(R.id.search_word)
        progress = findViewById(R.id.progress_bar)
        retry = findViewById(R.id.retry)
        layoutEmpty = findViewById(R.id.layout_empty)
        layoutError = findViewById(R.id.layout_error)
        rvWords.layoutManager = LinearLayoutManager(this)
        wordsAdapter = WordsAdapter(words)
        rvWords.adapter = wordsAdapter
    }

    private fun setClickListeners() {
        searchVew.setOnQueryTextListener(this)
        retry.setOnClickListener {
            error(Pair(false, null))
            wordsPresenter.getWords(checkInternetConnection())
        }
        sortList.setOnClickListener {
            words.sortWords(sortAsc)
            sortAsc = sortAsc.not()
            rvWords.adapter?.notifyDataSetChanged()
        }
    }

    private fun initWordsPresenter() {
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

        wordsPresenter = WordsPresenter(wordRepository, this)
        wordsPresenter.getWords(checkInternetConnection())

    }


    override fun onQueryTextSubmit(query: String?): Boolean {
        wordsAdapter.filter.filter(query)
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        wordsAdapter.filter.filter(newText)
        return false
    }

    override fun displayWords(words: List<Word>) {
        this.words.addAll(words)
        rvWords.adapter?.notifyDataSetChanged()
    }

    override fun progress(loading: Boolean) {
        when (loading) {
            true -> progress.visibility = View.VISIBLE
            false -> progress.visibility = View.GONE
        }

    }

    override fun isEmpty(empty: Boolean) {
        when (empty) {
            true -> layoutEmpty.visibility = View.VISIBLE
            false -> layoutEmpty.visibility = View.GONE
        }
    }

    override fun error(error: Pair<Boolean, String?>) {
        when (error.first) {
            true -> {
                layoutError.visibility = View.VISIBLE
                error.second?.let {
                    Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                }
            }
            false -> layoutError.visibility = View.GONE
        }

    }

}