package com.example.project_spotify.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project_spotify.Adapter.PlayListAdapter
import com.example.project_spotify.R
import com.example.project_spotify.Repository.SpotifyRepository
import com.example.project_spotify.ViewModel.SearchPlayListViewModel
import com.example.project_spotify.ViewModel.SearchPlayListViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PlayListFragment(private val search: String) : Fragment() {


    lateinit var searchPlayListViewModel: SearchPlayListViewModel
    lateinit var playListAdaper: PlayListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_play_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var rvPlayList = view.findViewById<RecyclerView>(R.id.rvPlayList)
        rvPlayList.layoutManager=LinearLayoutManager(context)
        playListAdaper = PlayListAdapter()
        rvPlayList.adapter = playListAdaper
        searchPlayListViewModel = ViewModelProvider(
            this, SearchPlayListViewModelFactory(
                SpotifyRepository(view.context)
            )
        ).get(SearchPlayListViewModel::class.java)

        searchPlayListViewModel.getPlayList(search, 20)

        lifecycleScope.launch {
            searchPlayListViewModel.playList.collectLatest {
                playListAdaper.setPlayList(it)
            }

        }
        super.onViewCreated(view, savedInstanceState)
    }
}