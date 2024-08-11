package com.example.project_spotify.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.project_spotify.Model.PlayListModel
import com.example.project_spotify.R

class PlayListAdapter : RecyclerView.Adapter<PlayListAdapter.ViewHolder>() {

    private var mPlayList: List<PlayListModel> = emptyList()

    fun setPlayList(playList: List<PlayListModel>) {
        mPlayList = playList
        notifyDataSetChanged()
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivPlayList = itemView.findViewById<ImageView>(R.id.ivPlayList)
        var tvPlayListName = itemView.findViewById<TextView>(R.id.tvPlayListName)

        fun setPlayListModel(playListModel: PlayListModel) {
            tvPlayListName.text = playListModel.name
            Glide.with(itemView.context).load(playListModel.url)
                .apply(RequestOptions.centerCropTransform()).into(ivPlayList)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       var view:View=LayoutInflater.from(parent.context).inflate(R.layout.row_playllist,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
      return mPlayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      holder.setPlayListModel(mPlayList[position])
    }
}