package com.unifi.ibt.data.local

import com.unifi.ibt.data.remote.NetworkConnection
import com.unifi.ibt.data.repo.ResponseListener

class RemoteDataSource(var networkConnection: NetworkConnection){
    fun getWords():String{
       return networkConnection.getWebsite()
    }
}