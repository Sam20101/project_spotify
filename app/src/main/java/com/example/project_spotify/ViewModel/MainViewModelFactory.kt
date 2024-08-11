package com.example.project_spotify.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.project_spotify.Repository.SpotifyRepository


class MainViewModelFactory(private val repository: SpotifyRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainActivityViewModel::class.java))
            return MainActivityViewModel(repository) as T
        else throw IllegalArgumentException("Unknown Class")
    }
}