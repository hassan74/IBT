package com.unifi.ibt.data.repo

import com.unifi.ibt.data.models.Word

interface IWordRepository {

    fun insert(vararg word: Word)

    fun getAllWords(isOffline:Boolean,callBack: (Result) -> Unit)
}