package com.unifi.ibt.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.unifi.ibt.models.Word
import com.unifi.ibt.data.IWordRepository
import com.unifi.ibt.data.Result

class MainViewModel(
    var wordRepository: IWordRepository
) {
    var _wordsResult = MutableLiveData<ArrayList<Word>>()
    val wordsResult: LiveData<ArrayList<Word>> = _wordsResult
    fun  getAllWords(isConnected:Boolean) {
        wordRepository.getAllWords(isConnected) { result ->
            when (result) {
                is Result.Success -> _wordsResult.value = parseHTML(result.response)
                else -> (result as Result.Error).exception
            }
        }

    }


}