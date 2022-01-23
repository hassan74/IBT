package com.unifi.ibt.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.unifi.ibt.models.Word
import java.util.*

fun Context.checkInternetConnection(): Boolean {
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }
    } else {
        @Suppress("DEPRECATION") val networkInfo =
            connectivityManager.activeNetworkInfo ?: return false
        @Suppress("DEPRECATION")
        return networkInfo.isConnected
    }

}


fun ArrayList<Word>.sortWords(asc: Boolean) {
    when (asc) {
        true -> sortBy { it.count }
        false -> sortByDescending { it.count }
    }
}

fun ArrayList<Word>.filter(charSearch: String): ArrayList<Word> {
    if (charSearch.isEmpty()) {
        return this
    } else {
        return filter { it.name.contains(charSearch.lowercase(Locale.ROOT)) } as ArrayList<Word>
    }
}