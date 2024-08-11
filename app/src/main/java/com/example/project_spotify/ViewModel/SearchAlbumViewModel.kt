package com.example.project_spotify.ViewModel

import android.app.ActivityManager
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_spotify.Model.AlbumListModel
import com.example.project_spotify.Repository.SpotifyRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchAlbumViewModel(private var repository: SpotifyRepository):ViewModel() {

//    private val mAlbumList:MutableLiveData<List<AlbumListModel>> = MutableLiveData(emptyList())
//    val albumList:LiveData<List<AlbumListModel>> = mAlbumList

    private val mAlbumList:MutableStateFlow<List<AlbumListModel>> = MutableStateFlow(emptyList())
    val albumList:StateFlow<List<AlbumListModel>> = mAlbumList.asStateFlow()
    fun getAlbums(q:String ,limit:Int){



//        viewModelScope.launch {
//            val result=repository.getAlbums(q,limit).getOrNull()
//            try {
//                val lAlbums=result!!.map {
//                    AlbumListModel(it.images[0].url,it.name,it.artists.joinToString { it.name })
//                }
//                mAlbumList.value=lAlbums
//
//            }catch (e:Exception)
//            {
//                Log.e("songs list",e.message.toString())
//            }
//
//        }

       repository.getAlbums(q,limit).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).map { it ->
            it.map {
                AlbumListModel(it.images[0].url,it.name,it.artists.joinToString { it.name })
        }
        }.subscribe(
            {tlist->mAlbumList.value=tlist},{error->Log.e("AlbumSearch",error.message.toString())}
        )


    }



}