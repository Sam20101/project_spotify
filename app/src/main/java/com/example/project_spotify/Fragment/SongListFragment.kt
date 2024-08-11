package com.example.project_spotify.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project_spotify.R
import com.example.project_spotify.Repository.SpotifyRepository
import com.example.project_spotify.Adapter.SongListAdapter
import com.example.project_spotify.ViewModel.SeachSongViewModel
import com.example.project_spotify.ViewModel.SearchSongViewModelFactory

class SongListFragment(mSearch:String) : Fragment() {

    lateinit var searchsongViewModel:SeachSongViewModel
    lateinit var spotifyRepository: SpotifyRepository
    lateinit var songListAdapter: SongListAdapter
    lateinit var rvSonsList: RecyclerView
    var search=mSearch
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_song_list, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        rvSonsList=view.findViewById(R.id.rvSongList)
        rvSonsList.layoutManager=LinearLayoutManager(context)
        spotifyRepository= SpotifyRepository(requireContext())
        songListAdapter= SongListAdapter()
        searchsongViewModel=ViewModelProvider(this,SearchSongViewModelFactory(spotifyRepository)).get(SeachSongViewModel::class.java)
        searchsongViewModel.getSongs(search,20)
        rvSonsList.adapter=songListAdapter
        searchsongViewModel.songsList.observe(viewLifecycleOwner){
                songListAdapter.setSongsList(it)
        }
        super.onViewCreated(view, savedInstanceState)


    }
}