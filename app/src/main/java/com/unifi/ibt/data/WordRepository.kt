package com.unifi.ibt.data

import android.os.Handler
import com.unifi.ibt.data.localdata.CachedLocalDataSource
import com.unifi.ibt.data.remotedata.RemoteDataSource
import java.util.concurrent.Executor

class WordRepository(
    private val remoteDataSource: IDataSource,
    private val localDataSource: IDataSource,
    private val executor: Executor,
    private val resultHandler: Handler
) : IWordRepository {

    override fun getAllWords(isConnected: Boolean, callBack: (Result<String>) -> Unit) {
        if (isConnected) {
            executor.execute {
                try {
                    val result = remoteDataSource.fetchData()
                    if (result is Result.Success)
                        saveHTML(result.data)

                    resultHandler.post {
                        callBack(result)
                    }
                } catch (e: Exception) {
                    callBack(Result.Error(e))
                }
            }

        } else {
            callBack(localDataSource.fetchData())
        }

    }


    override fun saveHTML(response: String) {
        localDataSource.saveData(response)

    }


}