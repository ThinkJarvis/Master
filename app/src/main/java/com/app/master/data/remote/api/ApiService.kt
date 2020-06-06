package com.app.master.data.remote.api

import com.app.master.mode.Post
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    companion object {
        const val API_URL = "https://patilshreyas.github.io/"
    }

    @GET("/DummyFoodiumApi/api/posts/")
    suspend fun getPosts(): Response<List<Post>>
}