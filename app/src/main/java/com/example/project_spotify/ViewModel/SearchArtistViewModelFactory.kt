package com.example.project_spotify.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.project_spotify.Repository.SpotifyRepository

class SearchArtistViewModelFactory(private val repository: SpotifyRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
       if (modelClass.isAssignableFrom(SearchArtistViewModel::class.java))
           return SearchArtistViewModel(repository) as T
        else throw IllegalArgumentException("Error in searchArtistViewModelFactory")
    }

}