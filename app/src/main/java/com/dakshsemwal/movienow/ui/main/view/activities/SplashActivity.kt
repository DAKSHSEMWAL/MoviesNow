package com.dakshsemwal.movienow.ui.main.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dakshsemwal.movienow.R
import com.dakshsemwal.movienow.model.SplashModel
import com.dakshsemwal.movienow.ui.main.viewModel.SplashViewModel

class SplashActivity : AppCompatActivity() {
    private lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        initViewModel()
        observeSplashLiveData()
    }

    private fun initViewModel() {
        splashViewModel = ViewModelProvider(this).get(SplashViewModel::class.java)
    }

    private fun observeSplashLiveData() {
        splashViewModel.initSplashScreen()
        val observer = Observer<SplashModel> {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        splashViewModel.liveData.observe(this, observer)
    }
}