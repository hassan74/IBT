package com.unifi.ibt.data.remote

import androidx.loader.content.AsyncTaskLoader
import com.unifi.ibt.data.repo.ResponseListener
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

class NetworkConnection {
    fun getWebsite():String {
            val url = URL("https://instabug.com/")
            val urlcon = url.openConnection()
            val stream = urlcon.getInputStream()
            val fileInputStream =InputStreamReader(stream)
            var result =stream.bufferedReader().use { it.readText() }
            stream.close()
            return result
    }
}