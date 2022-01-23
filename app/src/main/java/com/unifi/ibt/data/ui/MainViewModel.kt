package com.unifi.ibt.data.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.unifi.ibt.data.models.Word
import com.unifi.ibt.data.repo.IWordRepository
import com.unifi.ibt.data.repo.Result

class MainViewModel(
    var wordRepository: IWordRepository
) {
    var _wordsResult = MutableLiveData<ArrayList<Word>>()
    val wordsResult: LiveData<ArrayList<Word>> = _wordsResult
    fun getAllWords() {
        wordRepository.getAllWords(false) { result ->
            when (result) {
                is Result.Success -> _wordsResult.value = parseHTML(result.response)
                else -> (result as Result.Error).exception
            }
        }

    }


}