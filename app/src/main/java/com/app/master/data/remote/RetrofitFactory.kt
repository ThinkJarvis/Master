package com.app.master.data.remote


import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {

    inline fun <reified T> createRetrofitService(baseUrl: String): T =
        createRetrofit(baseUrl).create(T::class.java)

    fun createRetrofit(baseUrl: String): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(getClient())
        .build()

    private fun getClient(): OkHttpClient =
        OkHttpClient.Builder().addInterceptor(LoggerInterceptor()).build()
}