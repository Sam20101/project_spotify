package com.example.project_spotify.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.project_spotify.Model.ArtistListModel
import com.example.project_spotify.R

class ArtistListAdapter:RecyclerView.Adapter<ArtistListAdapter.ViewHolder> (){

    private var mArtistList:List<ArtistListModel> = emptyList()

    fun setArtistList(artistList:List<ArtistListModel>)
    {
        mArtistList=artistList;
        Log.e("API_REPONSE_M1a","$mArtistList")
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        var ivArtist:ImageView=itemView.findViewById(R.id.ivArtist)
        var tvArtistName:TextView=itemView.findViewById(R.id.tvArtistName)
        fun setArtist(artistModel:ArtistListModel)
        {
            tvArtistName.text=artistModel.name;
            Glide.with(itemView.context).load(artistModel.url).apply{RequestOptions.centerCropTransform()}.into(ivArtist)

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       var view:View=LayoutInflater.from(parent.context).inflate(R.layout.row_artist_list,parent,false)
        return ViewHolder(view);
    }

    override fun getItemCount(): Int {
       return mArtistList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val artistListModel=mArtistList[position]
        holder.setArtist(artistListModel)
    }
}