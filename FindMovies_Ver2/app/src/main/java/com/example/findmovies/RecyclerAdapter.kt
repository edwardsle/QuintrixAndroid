package com.example.findmovies

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_layout.view.*

class RecyclerAdapter(private val itemList: List<Movie>) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    private var context: Context? = null
    var listener: GalleryImageClickListener? = null

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {
            val movie = itemList[adapterPosition]

            Glide.with(context!!)
                .load(movie.poster)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(itemView.item_image)
            itemView.item_title.text = movie.title
            itemView.item_year.text = movie.year
            itemView.setOnClickListener {
                listener?.onClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.card_layout, parent,
            false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}