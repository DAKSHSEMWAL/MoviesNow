package com.dakshsemwal.movienow.ui.main.adapters

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.dakshsemwal.movienow.R
import com.dakshsemwal.movienow.model.Movie
import kotlinx.android.synthetic.main.item_movie.view.*

class MainAdapter(private val movies: ArrayList<Movie>) :
    RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    private var mItemClickListener: OnItemClickListener? = null

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie) {
            itemView.apply {
                Glide.with(context)
                    .load(movie.info!!.imageUrl)
                    .error(Glide.with(context).load(R.drawable.ic_photo))
                    .listener(object : RequestListener<Drawable?> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable?>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            moviename.text = movie.title
                            moviename.visibility = View.VISIBLE
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable?>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            moviename.visibility = View.GONE
                            return false
                        }

                    }).into(image)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        )

    override fun getItemCount(): Int = movies.size

    fun SetOnItemClickListener(mItemClickListener: OnItemClickListener) {
        this.mItemClickListener = mItemClickListener
    }

    interface OnItemClickListener {
        fun onMovieClick(view: View?, itemPosition: Int, model: Movie?)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(movies[position])
        holder.itemView.setOnClickListener {
            mItemClickListener!!.onMovieClick(
                holder.itemView,
                position,
                movies[position]
            )
        }
    }

    fun addUsers(users: List<Movie>) {
        this.movies.apply {
            clear()
            addAll(users)
        }

    }

}
