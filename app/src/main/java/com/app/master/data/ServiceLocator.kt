package com.app.master.data

import android.content.Context
import androidx.annotation.VisibleForTesting
import com.app.master.data.local.PostsDatabase
import com.app.master.data.local.dao.PostsDao
import com.app.master.data.remote.RetrofitFactory
import com.app.master.data.remote.api.ApiService
import com.app.master.data.repository.TaskRepository

object ServiceLocator {
    private lateinit var postsDao: PostsDao

    private lateinit var apiService: ApiService

    @Volatile
    var tasksRepository: TaskRepository? = null
        @VisibleForTesting set


    fun init(context: Context) {
        postsDao = PostsDatabase.getInstance(context).getPostsDao()

        apiService = RetrofitFactory.createRetrofitService(ApiService.API_URL)
    }

    fun provideTaskRepository(): TaskRepository {
        synchronized(this) {
            return tasksRepository ?: tasksRepository ?: TaskRepository(postsDao, apiService)
        }
    }
}