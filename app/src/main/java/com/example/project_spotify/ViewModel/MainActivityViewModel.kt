package com.example.project_spotify.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_spotify.Repository.SpotifyRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch


class MainActivityViewModel(private val spotifyRepository: SpotifyRepository) : ViewModel() {

    private val mAccessToken: MutableLiveData<String> = MutableLiveData()
    var accessToken: LiveData<String> = mAccessToken
    private val mDisposabble=CompositeDisposable()

    fun fetchAccessToken() {

        spotifyRepository.getAccessToken().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe ({token-> mAccessToken.value= token.access_token},{error-> Log.e("Token",error.message.toString())})
//        viewModelScope.launch {
//            try {
//                val response = spotifyRepository.getAccessToken()
//
//                if (response != null) {
//                    mAccessToken.value = response.getOrNull()!!.access_token
//
//                } else {
//
//                }
//
//            } catch (e: Exception) {
//
//            }
//        }
    }
}