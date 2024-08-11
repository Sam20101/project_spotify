package com.example.project_spotify.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.project_spotify.Model.AlbumListModel
import com.example.project_spotify.R

class AlbumListAdapter : RecyclerView.Adapter<AlbumListAdapter.ViewHolder>() {

     var albumList :List<AlbumListModel> = emptyList()
    fun setAlbumsInAdapter(mAlbumList: List<AlbumListModel>) {
        albumList = mAlbumList
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var ivAlbum = itemView.findViewById<ImageView>(R.id.ivAlbum)
        private var tvAlbumTitle = itemView.findViewById<TextView>(R.id.tvAlbumTitle)
        private var tvAlbumArtist = itemView.findViewById<TextView>(R.id.tvAlbumArtist)
        fun setAlbum(albumListModel: AlbumListModel)
        {
            tvAlbumTitle.text=albumListModel.albumTitle
            tvAlbumArtist.text=albumListModel.albumArtist
            Glide.with(itemView.context).load(albumListModel.url).apply(RequestOptions().centerCrop()).into(ivAlbum)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       var view:View=LayoutInflater.from(parent.context).inflate(R.layout.row_album_list,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return albumList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var albumListModel=albumList.get(position)
        holder.setAlbum(albumListModel)
    }
}