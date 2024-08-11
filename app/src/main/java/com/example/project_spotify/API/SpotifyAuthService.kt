package com.example.project_spotify.API


import com.example.project_spotify.IOModel.SongDataModel.TokenResponseModel
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface SpotifyAuthService {

    //    Purpose of @FormUrlEncoded:
    //    This annotation is used when you want to send form-encoded data in the request body.
    //    It tells Retrofit to encode the request body using the MIME type application/x-www-form-urlencoded.
    @FormUrlEncoded
    @POST("api/token")
     fun getAccessToken(
        @Header("Authorization") auth:String,
        @Header("Content-Type") content:String,
        @Field("grant_type")  grant_type:String
    ): Observable<TokenResponseModel>

}