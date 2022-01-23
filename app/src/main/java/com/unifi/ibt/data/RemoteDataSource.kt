package com.unifi.ibt.data

import com.unifi.ibt.data.Result
import com.unifi.ibt.network.NetworkConnection

class RemoteDataSource(var networkConnection: NetworkConnection){
    fun fetchData(): Result<String> {
       return networkConnection.getWebsite()
    }
}