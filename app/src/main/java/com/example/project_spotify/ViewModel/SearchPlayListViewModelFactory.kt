package com.example.project_spotify.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.project_spotify.IOModel.PlayListDataModel.SearchPlayListModel
import com.example.project_spotify.Repository.SpotifyRepository

class SearchPlayListViewModelFactory(private val repository: SpotifyRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SearchPlayListViewModel::class.java))
            return SearchPlayListViewModel(repository) as T
        else
             throw  IllegalArgumentException("Error in creating playlistviewmodel")
    }
}