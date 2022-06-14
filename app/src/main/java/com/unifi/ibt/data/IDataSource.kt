package com.unifi.ibt.data

interface IDataSource {
    fun fetchData(): Result<String>
    fun saveData(response: String)
}