package com.app.master.data.repository

import com.app.master.data.local.dao.PostsDao
import com.app.master.data.remote.api.ApiService
import com.app.master.expection.Failure
import com.app.master.extension.Either
import com.app.master.mode.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

class TaskRepository constructor(val postsDao: PostsDao, val apiService: ApiService) {

    fun getAllPosts(): Flow<Either<Failure, List<Post>>> {

        return object : NetworkBoundRepository<List<Post>, List<Post>>() {

            override suspend fun saveRemoteData(response: List<Post>) =
                postsDao.insertPosts(response)

            override suspend fun fetchFromLocal(): Flow<List<Post>> = postsDao.getAllPosts()

            override suspend fun fetchFromRemote(): Response<List<Post>> = apiService.getPosts()

        }.asFlow().flowOn(Dispatchers.IO)
    }
}