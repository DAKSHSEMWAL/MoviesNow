package com.dakshsemwal.movienow.ui.main.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.dakshsemwal.movienow.R
import com.dakshsemwal.movienow.ui.main.adapters.MainAdapter
import com.dakshsemwal.movienow.data.api.ApiHelper
import com.dakshsemwal.movienow.data.api.RetrofitBuilder
import com.dakshsemwal.movienow.databinding.MainActivityBinding
import com.dakshsemwal.movienow.model.Movie
import com.dakshsemwal.movienow.ui.base.ViewModelFactory
import com.dakshsemwal.movienow.ui.main.viewModel.MainViewModel
import com.dakshsemwal.movienow.utils.Status.*

class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController
    lateinit var navHostFragment: NavHostFragment

    lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity)
        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }

}