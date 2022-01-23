package com.unifi.ibt.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.unifi.ibt.models.Word
import com.unifi.ibt.data.IWordRepository
import com.unifi.ibt.data.Result

class MainViewModel(
    var wordRepository: IWordRepository
)  : ViewModel(){
    var _wordsResult = MutableLiveData<ArrayList<Word>>()
    val wordsResult: LiveData<ArrayList<Word>> = _wordsResult

    var _showLoading = MutableLiveData<Boolean>()
    var showLoading:LiveData<Boolean> = _showLoading

    var _showMessage = MutableLiveData<String>()
    var showMessage:LiveData<String> = _showMessage

    fun  getAllWords(isConnected:Boolean) {
        _showLoading.value=true
        wordRepository.getAllWords(isConnected) { result ->
            _showLoading.value=false
            when (result) {
                is Result.Success -> {
                    _wordsResult.value =parseHTML(result.data).apply {
                        if (isNullOrEmpty())_showMessage.value ="Error"
                    }
                }
                else -> _showMessage.value =(result as Result.Error).exception.message
            }
        }

    }


}