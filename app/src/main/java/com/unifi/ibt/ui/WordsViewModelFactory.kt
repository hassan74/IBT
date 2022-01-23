package com.unifi.ibt.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.unifi.ibt.data.IWordRepository

class WordsViewModelFactory(
    var wordRepository: IWordRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(wordRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}