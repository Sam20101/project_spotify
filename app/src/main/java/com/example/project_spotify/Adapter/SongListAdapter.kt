package com.example.project_spotify.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.project_spotify.Model.SongListModel
import com.example.project_spotify.R


class SongListAdapter : RecyclerView.Adapter<SongListAdapter.ViewHolder>() {

    var songList: List<SongListModel> = emptyList()
    fun setSongsList(mSongList: List<SongListModel>) {
        songList = mSongList
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var songName = itemView.findViewById<TextView>(R.id.tvsongTitle)
        var songArtist = itemView.findViewById<TextView>(R.id.tvsongArtist)
        var ivSong = itemView.findViewById<ImageView>(R.id.ivSong)

        fun bindView(songListModel: SongListModel) {
            songName.text = songListModel.songTitle
            songArtist.text = songListModel.songArtist
            var requestOptions: RequestOptions = RequestOptions().centerCrop()
            Glide.with(itemView.context).load(songListModel.url).apply(requestOptions).into(ivSong)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.row_song_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return songList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var songListModel=songList.get(position)
        holder.bindView(songListModel)

    }
}