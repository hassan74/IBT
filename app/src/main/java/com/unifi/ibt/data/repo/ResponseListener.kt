package com.unifi.ibt.data.repo

import java.lang.Exception

interface ResponseListener {
    fun onSuccess(response:String)
    fun onError(exception: Exception)
}