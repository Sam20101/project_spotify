package com.example.project_spotify.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.project_spotify.Model.PlayListModel
import com.example.project_spotify.Repository.SpotifyRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SearchPlayListViewModel(private val repository: SpotifyRepository) : ViewModel() {

    private val mPlayList = MutableStateFlow<List<PlayListModel>>(emptyList())
    var playList = mPlayList.asStateFlow()

    fun getPlayList(q: String, limit: Int) {
        repository.getPlayList(q, 20).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).map { it ->
                it.map {
                    var url=""
                    if(it.images.isNotEmpty())
                        url=it.images[0].url
                    PlayListModel(url, it.name)
                }
            }.subscribe({ tList -> mPlayList.value = tList }, { error ->
                throw IllegalArgumentException("Error in searchPlayListModel ${error.message}")
            }

            )


    }


}