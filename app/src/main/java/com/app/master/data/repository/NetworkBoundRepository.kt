package com.app.master.data.repository

import com.app.master.expection.Failure
import com.app.master.extension.Either
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.Response

abstract class NetworkBoundRepository<RESULT, REQUEST> {

    fun asFlow() = flow<Either<Failure, RESULT>> {

        // Emit Loading
        emit(Either.loading())

        try {
            // Fetch latest posts from remote
            val apiResponse = fetchFromRemote()

            // Parse body
            val remotePosts = apiResponse.body()
            if (apiResponse.isSuccessful && remotePosts != null) {
                // Save posts into the persistence storage
                saveRemoteData(remotePosts)
            } else {
                // Something went wrong! Emit Error state.
                emit(Either.left(Failure.ServerError))
            }


        } catch (e: Exception) {
            // Exception occurred! Emit error
            emit(Either.left(Failure.NetWorkError))
            e.printStackTrace()
        }

        // Retrieve posts from persistence storage and emit
        emitAll(fetchFromLocal().map {
            Either.right<RESULT>(it)
        })

    }

    protected abstract suspend fun saveRemoteData(response: REQUEST)

    protected abstract suspend fun fetchFromLocal(): Flow<RESULT>

    protected abstract suspend fun fetchFromRemote(): Response<REQUEST>
}