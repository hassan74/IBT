package com.unifi.ibt.data

import java.lang.Exception

sealed class Result {
    data class Success(val response:String): Result()
    data class Error(val exception: Exception): Result()
}