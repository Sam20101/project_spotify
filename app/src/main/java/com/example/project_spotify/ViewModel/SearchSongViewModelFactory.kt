package com.example.project_spotify.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.project_spotify.Repository.SpotifyRepository

class SearchSongViewModelFactory(private val repository: SpotifyRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
       if(modelClass.isAssignableFrom(SeachSongViewModel::class.java))
           return SeachSongViewModel(repository) as T
        else throw IllegalArgumentException("Wrong class in searchsongviewModelFactory")

    }
}