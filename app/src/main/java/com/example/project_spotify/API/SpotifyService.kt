package com.example.project_spotify.API

import com.example.project_spotify.IOModel.AlbumDataModel.SearchAlbumModel
import com.example.project_spotify.IOModel.ArtistDataModel.SearchArtistModel
import com.example.project_spotify.IOModel.PlayListDataModel.SearchPlayListModel
import com.example.project_spotify.IOModel.SongDataModel.SearchSongModel
import io.reactivex.Observable
import io.reactivex.Single

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface SpotifyService {

    @GET("v1/search")
    suspend fun getSongs(
        @Header("Authorization") bearerToken: String,
        @Query("q") q: String,
        @Query("type") type: String,
        @Query("limit") limit: Int
    ): Response<SearchSongModel>

    @GET("v1/search")
    fun getAlbums(
        @Header("Authorization") bearerToken: String,
        @Query("q") q: String,
        @Query("type") type: String,
        @Query("limit") limit: Int
    ): Single<SearchAlbumModel>

    @GET("v1/search")
    fun getArtists(
        @Header("Authorization") bearerToken: String,
        @Query("q") q: String,
        @Query("type") type: String,
        @Query("limit") limit: Int
    ): Single<Response<SearchArtistModel>>

    @GET("v1/search")
    fun getPlayList(
        @Header("Authorization") bearerToken: String,
        @Query("q") q: String,
        @Query("type") type: String,
        @Query("limit") limit: Int
    ): Single<Response<SearchPlayListModel>>

}