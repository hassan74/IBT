package com.unifi.ibt.data

import android.os.Handler
import java.util.concurrent.Executor

class WordRepository(
    val remoteDataSource: RemoteDataSource,
    val localDataSource: CachedLocalDataSource,
    val executor: Executor,
    val resultHandler: Handler
) : IWordRepository {

    override fun getAllWords(isConnected: Boolean, callBack: (Result) -> Unit) {
        if (isConnected) {
            executor.execute {
                try {
                    val result = remoteDataSource.fetchData()
                    if (result is Result.Success)
                        saveHTML(result.response)

                    resultHandler.post {
                        callBack(result)
                    }
                } catch (e: Exception) {
                    callBack(Result.Error(e))
                }
            }

        } else {
            callBack(localDataSource.getCachedData())
        }

    }


    override fun saveHTML(response: String) {
        localDataSource.saveData(response)

    }


}