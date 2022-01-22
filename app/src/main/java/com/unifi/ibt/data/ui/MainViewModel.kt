package com.unifi.ibt.data.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.unifi.ibt.data.models.Word
import com.unifi.ibt.data.repo.IWordRepository
import com.unifi.ibt.data.repo.ResponseListener

class MainViewModel(
    var wordRepository: IWordRepository
) {
    var _wordsResult = MutableLiveData<ArrayList<Word>>()
    val wordsResult: LiveData<ArrayList<Word>> = _wordsResult
    fun getAllWords() {
        //if(!isOffline)
        wordRepository.getAllWords(false, object : ResponseListener {
            override fun onSuccess(response: String) {
                _wordsResult.value = parseHTML(response)
            }

            override fun onError(exception: Exception) {
            }

        })
    }


}