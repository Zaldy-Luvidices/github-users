package com.test.githubusers.api

import com.test.githubusers.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val requestBuilder = chain.request().newBuilder()
            val token = BuildConfig.GITHUB_TOKEN
            if (token.isNotBlank()) {
                requestBuilder.addHeader("Authorization", "token $token")
            }
            chain.proceed(requestBuilder.build())
        }
        .build()
    val api: GitHubApi = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
        .create(GitHubApi::class.java)
}