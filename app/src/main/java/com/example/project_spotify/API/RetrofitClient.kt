package com.example.project_spotify.API

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private val BASE_AUTH_URL = "https://accounts.spotify.com/"
    private val BASE_URL = "https://api.spotify.com/"
    val spotifyAuthServiceInstance: SpotifyAuthService by lazy {
        Retrofit.Builder().baseUrl(BASE_AUTH_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(SpotifyAuthService::class.java)
    }
    val spotifySearchService: SpotifyService by lazy {

        Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(SpotifyService::class.java)
    }
}