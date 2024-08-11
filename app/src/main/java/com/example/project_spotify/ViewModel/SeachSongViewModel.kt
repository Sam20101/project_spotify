package com.example.project_spotify.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_spotify.Model.SongListModel

import com.example.project_spotify.Repository.SpotifyRepository
import kotlinx.coroutines.launch

class SeachSongViewModel(private val spotifyRepository: SpotifyRepository) : ViewModel() {

    private val mSongsList = MutableLiveData<List<SongListModel>>()
    var songsList: LiveData<List<SongListModel>> = mSongsList

    fun getSongs(q: String, limit: Int) {


        viewModelScope.launch {
            val result = spotifyRepository.getSearchSong(q, "track", limit).getOrNull()!!
            try {
                    val lSongs=result.map {
                        SongListModel(it.album.images[0].url,it.name,it.artists.joinToString { it.name })
                    }
                mSongsList.value=lSongs
            }catch (e:Exception)
            {
                Log.e("songs list",e.message.toString())
            }
        }

//        spotifyRepository.getSearchSong(q, "track", limit).subscribeOn(Schedulers.io()).observeOn(
//            AndroidSchedulers.mainThread()
//        ).map { it ->
//            it.map {
//                SongListModel(it.album.images.get(0).url, it.name, it.artists.joinToString {
//                    it.name
//                })
//            }
//        }.subscribe({ tlist ->
//            mSongsList.postValue(tlist)
//        }, { error -> Log.e("Songlist", error.message.toString()) })
    }


}


