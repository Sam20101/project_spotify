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
import com.example.project_spotify.Adapter.AlbumListAdapter
import com.example.project_spotify.R
import com.example.project_spotify.Repository.SpotifyRepository
import com.example.project_spotify.ViewModel.SearchAlbumViewModel
import com.example.project_spotify.ViewModel.SearchAlbumViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class AlbumListFragment(private val search: String) : Fragment() {

    private lateinit var albumListViewModel: SearchAlbumViewModel
    private lateinit var repository:SpotifyRepository
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view: View = inflater.inflate(R.layout.fragment_album, container, false)
        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var rcAlbumList = view.findViewById<RecyclerView>(R.id.rvAlbum)
        rcAlbumList.layoutManager=LinearLayoutManager(context)
        var albumListAdapter=AlbumListAdapter()
        repository = SpotifyRepository(view.context)
        rcAlbumList.adapter=albumListAdapter

        albumListViewModel = ViewModelProvider(this, SearchAlbumViewModelFactory(repository)).get(
            SearchAlbumViewModel::class.java
        )
        albumListViewModel.getAlbums(search,20)
//       lifecycleScope.launch {
//           albumListViewModel.albumList.collectLatest {
//               albumListAdapter.setAlbumsInAdapter(it)
//           }
//       }
     lifecycleScope.launch {
         albumListViewModel.albumList.collectLatest {
             Log.e("API_RESPONSE_AL",it.toString())
             albumListAdapter.setAlbumsInAdapter(it)
         }
     }

        super.onViewCreated(view, savedInstanceState)
    }
}