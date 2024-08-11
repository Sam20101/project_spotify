package com.example.project_spotify.Repository

import android.content.Context

import android.util.Base64
import android.util.Log
import com.example.project_spotify.API.RetrofitClient
import com.example.project_spotify.API.SpotifyAuthService
import com.example.project_spotify.API.SpotifyService
import com.example.project_spotify.IOModel.SongDataModel.Item
import com.example.project_spotify.IOModel.SongDataModel.TokenResponseModel
import com.example.project_spotify.Utils.CommonUtils
import com.example.project_spotify.Utils.ConstantUtils
import io.reactivex.Observable
import io.reactivex.Single


class SpotifyRepository(val context: Context) {
    val spotifyAuthService: SpotifyAuthService = RetrofitClient.spotifyAuthServiceInstance
    val spotifyService: SpotifyService = RetrofitClient.spotifySearchService


    fun getAccessToken(): Observable<TokenResponseModel> {
        val base64Credentials = "Basic " + Base64.encodeToString(
            (ConstantUtils.Constant().CLIENT_ID + ":" + ConstantUtils.Constant().CLIENT_SECRET).toByteArray(),
            Base64.NO_WRAP
        )
        return spotifyAuthService.getAccessToken(
            base64Credentials,
            "application/x-www-form-urlencoded",
            "client_credentials"
        )
//        try {
//            val base64Credentials = "Basic " + Base64.encodeToString(
//                (ConstantUtils.Constant().CLIENT_ID + ":" + ConstantUtils.Constant().CLIENT_SECRET).toByteArray(),
//                Base64.NO_WRAP
//            )
//
//            val response = spotifyAuthService.getAccessToken(
//                base64Credentials, "application/x-www-form-urlencoded", "client_credentials"
//            )
//            Result.success(response)
//        } catch (e: Exception) {
//            Result.failure(e)
//
//        }
    }


    suspend fun getSearchSong(q: String, type: String, limit: Int): Result<List<Item>> {
//       val reponse= spotifyService.getSongs("Bearer "+CommonUtils().getTokenFromPreferences(context),q,type,limit).map { it.tracks.items }
//        return reponse
        try {
            val response = spotifyService.getSongs(
                "Bearer " + CommonUtils().getTokenFromPreferences(context),
                q,
                type,
                limit
            )
            if (response != null && response.isSuccessful) {
                val lTracks = response.body()!!.tracks.items
                return Result.success(lTracks)
            } else {
                return Result.failure(Exception("error code" + response.code()))
            }
        } catch (e: Exception) {
            return Result.failure(e)
        }

    }

    fun getAlbums(
        q: String,
        limit: Int
    ): Single<List<com.example.project_spotify.IOModel.AlbumDataModel.Item>> {
        val response = spotifyService.getAlbums(
            "Bearer " + CommonUtils().getTokenFromPreferences(context),
            q,
            "album",
            limit
        ).map {
            it.albums.items
        }
        return response
//        try {
//            val response = spotifyService.getAlbums(
//                "Bearer " + CommonUtils().getTokenFromPreferences(context),
//                q,
//                "album",
//                limit
//            )
//            if (response != null && response.isSuccessful) {
//                val lAlbums = response.body()!!.albums.items
//                return Result.success(lAlbums)
//            } else {
//                return Result.failure(Exception("error code"+response.code()))
//            }
//        }catch (e:Exception)
//        {
//            return Result.failure(e)
//        }
    }

    fun getArtist(
        q: String,
        limit: Int
    ): Single<List<com.example.project_spotify.IOModel.ArtistDataModel.Item>> {
        return spotifyService.getArtists(
            "Bearer " + CommonUtils().getTokenFromPreferences(context),
            q,
            "artist",
            limit
        ).map { response ->

            if (response.isSuccessful) {
                    response.body()?.artists?.items ?: emptyList()
            } else {
                Log.e("API_RESPONSE", "${response.code()} ${response.message()}")
                throw java.lang.Exception("API error: ${response.code()} ${response.message()}")
            }
        }
//        return response

        //        val resonse = spotifyService.getArtists(
//            "Bearer " + CommonUtils().getTokenFromPreferences(context),
//            q,
//            "artist",
//            limit
//        ).map {
//                response ->
//            Log.e("API_RESPONSE","$response")
//            if (response.isSuccessful) {
//                response.body()?.artists?.items ?: emptyList()
//            } else {
//                Log.e("API_RESPONSE", "${response.code()} ${response.message()}")
//                throw java.lang.Exception("API error: ${response.code()} ${response.message()}")
//            }
//           }
//        return resonse
    }


    fun getPlayList(
        q: String,
        limit: Int
    ): Single<List<com.example.project_spotify.IOModel.PlayListDataModel.Item>> {
        return spotifyService.getPlayList(
            "Bearer " + CommonUtils().getTokenFromPreferences(context),
            q,
            "playlist",
            limit
        ).map {
            response->
             if(response.isSuccessful)
             {
                 response.body()?.playlists?.items?: emptyList()
             }
             else
             {
                 throw java.lang.Exception("API error: ${response.code()} ${response.message()}")
             }
         }
    }


}