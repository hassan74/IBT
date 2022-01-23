package com.unifi.ibt.data.remote

import com.unifi.ibt.data.repo.Result
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class NetworkConnection {
    fun getWebsite(): Result {
        val url = URL("https://instabug.com/")
        (url.openConnection() as? HttpURLConnection)?.run {
            val stream = inputStream
            // val fileInputStream =InputStreamReader(stream)
            var result = stream.bufferedReader().use { it.readText() }
            stream.close()
            return Result.Success(result)
        }
        return Result.Error(Exception("Cannot open Connection"))
    }
}