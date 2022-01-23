package com.unifi.ibt.network

import com.unifi.ibt.data.Result
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class NetworkConnection {
    fun getWebsite(): Result<String> {
        val url = URL("https://instabug.com/")
        (url.openConnection() as? HttpURLConnection)?.run {
            val stream = inputStream
            // val fileInputStream =InputStreamReader(stream)
            val result = stream.bufferedReader().use { it.readText() }
            stream.close()
            return Result.Success(result)
        }
        return Result.Error(Exception("Cannot open Connection"))
    }
}