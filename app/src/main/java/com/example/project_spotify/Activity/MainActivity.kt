package com.example.project_spotify.Activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.project_spotify.Fragment.AlbumListFragment
import com.example.project_spotify.Fragment.ArtistListFragment
import com.example.project_spotify.Fragment.PlayListFragment
import com.example.project_spotify.R
import com.example.project_spotify.Repository.SpotifyRepository
import com.example.project_spotify.Fragment.SongListFragment
import com.example.project_spotify.Utils.CommonUtils
import com.example.project_spotify.ViewModel.MainActivityViewModel
import com.example.project_spotify.ViewModel.MainViewModelFactory
import com.example.project_spotify.ViewModel.SeachSongViewModel
import com.example.project_spotify.ViewModel.SearchPlayListViewModelFactory
import com.jakewharton.rxbinding4.view.clicks
import com.jakewharton.rxbinding4.widget.textChanges
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    val context: Context = this
    lateinit var mainViewModel: MainActivityViewModel
    lateinit var searchSongViewModel: SeachSongViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var etvSearch = findViewById<EditText>(R.id.etvSearch)
        var btnSongFilter = findViewById<Button>(R.id.btnSongFilter)
        var btnAlbumFilter = findViewById<Button>(R.id.btnAlbumFilter)
        var btnArtistFilter = findViewById<Button>(R.id.btnArtistFilter)
        var btnPlayListFilter = findViewById<Button>(R.id.btnPlayListFilter)
        val respository = SpotifyRepository(context);

        var search = etvSearch.text.toString()
        etvSearch.textChanges().skipInitialValue().debounce(1000, TimeUnit.MILLISECONDS)
            .subscribe { it ->
                search = it.toString()
                loadFragment(SongListFragment(it.toString()))
            }

        btnAlbumFilter.clicks().throttleFirst(2000, TimeUnit.MILLISECONDS).subscribe({
            loadFragment(AlbumListFragment(search))
        }, { it ->
            Log.e("btnAlbumFilter error", " ${it.message}")
        })
        btnSongFilter.clicks().throttleFirst(2000, TimeUnit.MILLISECONDS).subscribe({
            loadFragment(AlbumListFragment(search))
        }, {
            Log.e("btnSongFilter error", "${it.message}")
        }

        )
        btnArtistFilter.clicks().throttleFirst(2000, TimeUnit.MILLISECONDS).subscribe({
            loadFragment(ArtistListFragment(search))
        }, {
            Log.e("btnArtistFilter", "${it.message}")
        }

        )
        btnPlayListFilter.clicks().throttleFirst(2000, TimeUnit.MILLISECONDS).subscribe({
            loadFragment(PlayListFragment(search))
        }, {
            Log.e("btnPlayListFilter", "${it.message}")
        })


//        btnSongFilter.setOnClickListener(View.OnClickListener {
//            var search = etvSearch.text.toString()
//            loadFragment(SongListFragment(search))
//        })
//        btnAlbumFilter.setOnClickListener {
//            var search = etvSearch.text.toString()
//            loadFragment(AlbumListFragment(search))
//        }
//        btnArtistFilter.setOnClickListener {
//            var search = etvSearch.text.toString()
//            loadFragment(ArtistListFragment(search))
//        }
//        btnPlayListFilter.setOnClickListener {
//            var search = etvSearch.text.toString()
//            loadFragment(PlayListFragment(search))
//        }


        val mainViewModelFactory = MainViewModelFactory(respository)
        mainViewModel =
            ViewModelProvider(this, mainViewModelFactory).get(MainActivityViewModel::class.java)
        mainViewModel.fetchAccessToken()
        mainViewModel.accessToken.observe(this) {
            etvSearch.setText(it)
            CommonUtils().setTokenToPreferences(context, it)
        }


    }


    fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction();
        transaction.replace(R.id.mainContainer, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}