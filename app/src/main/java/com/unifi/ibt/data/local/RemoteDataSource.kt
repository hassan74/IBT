package com.unifi.ibt.data.local

import com.unifi.ibt.data.remote.NetworkConnection
import com.unifi.ibt.data.repo.Result

class RemoteDataSource(var networkConnection: NetworkConnection){
    fun getWords(): Result {
       return networkConnection.getWebsite()
    }
}