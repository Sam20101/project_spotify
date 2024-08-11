package com.example.project_spotify.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project_spotify.Adapter.ArtistListAdapter
import com.example.project_spotify.R
import com.example.project_spotify.Repository.SpotifyRepository
import com.example.project_spotify.ViewModel.SearchArtistViewModel
import com.example.project_spotify.ViewModel.SearchArtistViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class ArtistListFragment(private var search: String) : Fragment() {


    private lateinit var repository: SpotifyRepository
    private lateinit var artistViewModel: SearchArtistViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_artist_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var rvArtist = view.findViewById<RecyclerView>(R.id.rvArtist)
        rvArtist.layoutManager=LinearLayoutManager(context)
        var artistListAdapter = ArtistListAdapter()
        repository = SpotifyRepository(view.context)
        artistViewModel = ViewModelProvider(this, SearchArtistViewModelFactory(repository)).get(
            SearchArtistViewModel::class.java
        )
        rvArtist.adapter=artistListAdapter

        artistViewModel.getArtistList(search, 20)

        lifecycleScope.launch {
            artistViewModel.artistList.collectLatest {
                Log.e("API_RESPONSE_A1","$it")
                artistListAdapter.setArtistList(it)
            }
        }

        super.onViewCreated(view, savedInstanceState)
    }
}