package com.dakshsemwal.movienow.ui.main.view.fragments

import android.graphics.drawable.Drawable
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.dakshsemwal.movienow.R
import com.dakshsemwal.movienow.data.api.ApiHelper
import com.dakshsemwal.movienow.data.api.RetrofitBuilder
import com.dakshsemwal.movienow.databinding.MovieDetailFragmentBinding
import com.dakshsemwal.movienow.model.Movie
import com.dakshsemwal.movienow.ui.base.ViewModelFactory
import com.dakshsemwal.movienow.ui.main.viewModel.MainViewModel
import com.dakshsemwal.movienow.utils.Common.*
import kotlinx.android.synthetic.main.item_movie.*
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieDetailFragment : Fragment() {

    lateinit var binding: MovieDetailFragmentBinding
    lateinit var movie: Movie


    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MovieDetailFragmentBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayShowTitleEnabled(false)
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener { findNavController().popBackStack() }
        setupViewModel()
        return binding.root
    }

    private fun setUpMovie() {
       with(binding)
       {
           Glide.with(requireActivity())
               .load(movie.info!!.imageUrl)
               .error(Glide.with(requireActivity()).load(R.drawable.ic_photo))
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

               }).into(expandedImage)
           discription.text = movie.info!!.plot
           genrediscriptions.text = movie.info!!.genres.toString().replace("[","").replace("]","")
           yeardiscriptions.text = getDate(DATE_FORMAT_1,DATE_FORMAT_2,movie.info!!.releaseDate)
           directorsdesc.text = movie.info!!.directors.toString().replace("[","").replace("]","")
       }
    }

    private fun setupViewModel() {
       viewModel =  ViewModelProviders.of(
            requireActivity(),
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(MainViewModel::class.java)
        movie=viewModel.getSelectedMovie().value!!
        setUpMovie()
    }

}