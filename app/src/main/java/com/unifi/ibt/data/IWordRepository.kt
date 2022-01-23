package com.unifi.ibt.data

import com.unifi.ibt.models.Word

interface IWordRepository {

    fun insert(vararg word: Word)

    fun getAllWords(isOffline:Boolean,callBack: (Result) -> Unit)
}