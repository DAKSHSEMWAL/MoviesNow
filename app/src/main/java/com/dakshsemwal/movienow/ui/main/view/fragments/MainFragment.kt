package com.dakshsemwal.movienow.ui.main.view.fragments

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.dakshsemwal.movienow.R
import com.dakshsemwal.movienow.data.api.ApiHelper
import com.dakshsemwal.movienow.data.api.RetrofitBuilder
import com.dakshsemwal.movienow.databinding.MainFragmentBinding
import com.dakshsemwal.movienow.model.Movie
import com.dakshsemwal.movienow.ui.base.ViewModelFactory
import com.dakshsemwal.movienow.ui.main.adapters.MainAdapter
import com.dakshsemwal.movienow.ui.main.viewModel.MainViewModel
import com.dakshsemwal.movienow.utils.Status


class MainFragment : Fragment() {
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MainAdapter
    private lateinit var binding: MainFragmentBinding
    private lateinit var list: ArrayList<Movie>
    private lateinit var temp:ArrayList<Movie>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayShowTitleEnabled(false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupUI()
        setupObservers()
    }

    //Initialize Viewmodel Component
    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            requireActivity(),
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(MainViewModel::class.java)
    }

    //Initialize ui Component
    private fun setupUI() {
        list = ArrayList()
        temp = ArrayList()
        binding.recyclerView.layoutManager = GridLayoutManager(activity, 2)
        adapter = MainAdapter(arrayListOf())
        adapter.SetOnItemClickListener(
            object : MainAdapter.OnItemClickListener {
                override fun onMovieClick(view: View?, itemPosition: Int, model: Movie?) {
                    viewModel.setSelectedMovie(model!!)
                    findNavController().navigate(R.id.action_mainFragment_to_detailFragment)
                }
            })
        binding.recyclerView.adapter = adapter
        //Clear Sort Filter
        binding.clearText.setOnClickListener{
            binding.clearText.visibility = View.GONE
            binding.clearText.text = "Clear Text"
            retrieveList(temp)
        }
    }

    private fun setupObservers() {
        //Fetch data from api
        viewModel.getMovie().observe(requireActivity(), {
            it.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        binding.recyclerView.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        list.addAll(resource.data!!)
                        retrieveList(list)
                    }
                    Status.ERROR -> {
                        binding.recyclerView.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.recyclerView.visibility = View.GONE
                    }
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.name_asc) {
            temp.addAll(list)
            list.sortBy { it.title }
            binding.clearText.visibility=View.VISIBLE
            binding.clearText.text = "${binding.clearText.text}  ${getString(R.string.name_asc)}"
            retrieveList(list)
        }
        if (id == R.id.name_dsc) {
            temp.addAll(list)
            list.sortByDescending { it.title }
            binding.clearText.visibility=View.VISIBLE
            binding.clearText.text = "${binding.clearText.text}  ${getString(R.string.name_dsc)}"
            retrieveList(list)
        }
        if (id == R.id.year_asc) {
            temp.addAll(list)
            list.sortBy { it.year }
            binding.clearText.visibility=View.VISIBLE
            binding.clearText.text = "${binding.clearText.text}  ${getString(R.string.year_asc)}"
            retrieveList(list)
        }
        if (id == R.id.year_dsc) {
            temp.addAll(list)
            list.sortByDescending { it.year }
            binding.clearText.visibility=View.VISIBLE
            binding.clearText.text = "${binding.clearText.text}  ${getString(R.string.year_dsc)}"
            retrieveList(list)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun retrieveList(users: List<Movie>) {
        adapter.apply {
            addUsers(users)
            notifyDataSetChanged()
        }
    }

}