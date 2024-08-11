package com.example.project_spotify.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.project_spotify.Model.AlbumListModel
import com.example.project_spotify.Model.ArtistListModel
import com.example.project_spotify.Repository.SpotifyRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SearchArtistViewModel(private val repository: SpotifyRepository) : ViewModel() {

    private val disposable=CompositeDisposable()

    private var mArtistList = MutableStateFlow<List<ArtistListModel>>(emptyList())
    var artistList = mArtistList.asStateFlow()

    fun getArtistList(q: String, limit: Int) {
        var count=0
        disposable.add(
        repository.getArtist(q, limit).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).map { it ->
            it.map {
                Log.e("API_RESPONSE_A $count", "$it")
                var url="";
                if(it.images.isNotEmpty())
                    url=it.images[0].url
                ArtistListModel(url, it.name)

            }
        }.subscribe(
            { tlist -> mArtistList.value = tlist },
            { error -> Log.e("API_REPONSE_A_error", error.message.toString()) }
        ))


    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }
}