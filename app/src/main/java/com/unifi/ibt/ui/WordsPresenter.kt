package com.unifi.ibt.ui

import androidx.lifecycle.ViewModel
import com.unifi.ibt.data.IWordRepository
import com.unifi.ibt.data.Result

class WordsPresenter(
    var wordRepository: IWordRepository,
    var contract: WordsContract
) : ViewModel() {

    fun getWords(isConnected: Boolean) {
        contract.progress(true)
        wordRepository.getAllWords(isConnected) { result ->
            contract.progress(false)
            when (result) {
                is Result.Success -> {
                    contract.displayWords(parseHTML(result.data).apply {
                        if (isNullOrEmpty()) contract.isEmpty(true)
                        else contract.isEmpty(false)
                    })
                }
                else ->
                    contract.error(Pair(true ,(result as Result.Error).exception.message))
            }
        }

    }


}