package com.unifi.ibt.ui

import com.unifi.ibt.models.Word

interface WordsContract {
    fun displayWords(words:List<Word>)
    fun progress(loading:Boolean)
    fun error(error:Pair<Boolean , String?>)
    fun isEmpty(empty: Boolean)
}