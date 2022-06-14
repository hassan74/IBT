package com.unifi.ibt.data.remotedata

import com.unifi.ibt.data.IDataSource
import com.unifi.ibt.data.Result
import com.unifi.ibt.network.NetworkConnection

class RemoteDataSource(var networkConnection: NetworkConnection):IDataSource{

    override
    fun fetchData(): Result<String> {
       return networkConnection.getWebsite()
    }

    override fun saveData(response: String) {
    }
}