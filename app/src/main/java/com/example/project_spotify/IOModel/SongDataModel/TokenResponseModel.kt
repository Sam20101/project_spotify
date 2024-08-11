package com.example.project_spotify.IOModel.SongDataModel

data class TokenResponseModel(
    val access_token: String,
    val expires_in: Int,
    val token_type: String
)