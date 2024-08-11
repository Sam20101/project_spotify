package com.example.project_spotify.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.project_spotify.Repository.SpotifyRepository

class SearchAlbumViewModelFactory(private val repository: SpotifyRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SearchAlbumViewModel::class.java))
            return SearchAlbumViewModel(repository) as T
        else throw IllegalArgumentException("wrong class in SearchAlbumViewModelFactory")
    }
}