package com.unifi.ibt.data

import com.unifi.ibt.models.Word

interface IWordRepository {

    fun saveHTML(response:String)

    fun getAllWords(isConnected:Boolean,callBack: (Result<String>) -> Unit)
}