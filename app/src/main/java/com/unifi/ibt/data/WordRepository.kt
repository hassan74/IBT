package com.unifi.ibt.data

import android.os.Handler
import com.unifi.ibt.models.Word
import java.util.concurrent.Executor

class WordRepository(
    val remoteDataSource: RemoteDataSource,
    val cachedLocalDataSource: CachedLocalDataSource,
    val executor: Executor,
    val resultHandler: Handler
) : IWordRepository {
    override fun insert(vararg word: Word) {
    }

    override fun getAllWords(isOffline: Boolean, callBack: (Result) -> Unit) {
        if (!isOffline) {
            executor.execute {
                try {
                    val response = remoteDataSource.getWords()
                    // callBack.onSuccess(result)
                    resultHandler.post {
                        callBack(response)
                    }
                } catch (e: Exception) {
                    callBack(Result.Error(e))
                }
            }

        }

    }


}