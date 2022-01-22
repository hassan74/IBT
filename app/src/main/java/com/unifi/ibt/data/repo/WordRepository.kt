package com.unifi.ibt.data.repo

import android.os.Handler
import com.unifi.ibt.data.local.CachedLocalDataSource
import com.unifi.ibt.data.local.RemoteDataSource
import com.unifi.ibt.data.models.Word
import java.util.concurrent.Executor

class WordRepository(
    val remoteDataSource: RemoteDataSource,
    val cachedLocalDataSource: CachedLocalDataSource,
    val executor: Executor,
    val resultHandler: Handler
) : IWordRepository {
    override fun insert(vararg word: Word) {

    }

    override fun getAllWords(isOffline: Boolean, callBack: ResponseListener) {
        if (!isOffline) {
            executor.execute {
                try {
                    val result = remoteDataSource.getWords()
                    // callBack.onSuccess(result)
                    resultHandler.post {
                        callBack.onSuccess(result)
                    }
                } catch (e: Exception) {
                    callBack.onError(e)
                }

            }

        }

    }


}