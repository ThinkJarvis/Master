package com.app.master.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.app.master.expection.Failure
import com.app.master.R
import com.app.master.databinding.ActivityMainBinding
import com.app.master.extension.getViewModelFactory
import com.app.master.mode.Post
import com.orhanobut.logger.Logger

class MainActivity : AppCompatActivity() {

    private val _mainViewModel by viewModels<MainViewModel> { getViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataBindingUtil.setContentView<ActivityMainBinding>(
            this,
            R.layout.activity_main
        )
            .apply {
                lifecycleOwner = this@MainActivity
                mainViewModel = _mainViewModel
            }
//        _mainViewModel.getAllPosts()
//
//        _mainViewModel.postLiveData.observe(this, Observer {
//            it.either(::handleLoading, ::handleFailure, ::handleSuccess)
//        })
    }


    private fun handleLoading() {
        Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show()
    }


    private fun handleSuccess(posts: List<Post>) {
        posts.forEach {
            Logger.d("title = ${it.title}")
        }

    }


    private fun handleFailure(failure: Failure) {
        when (failure) {
            is Failure.NetWorkError -> {
                Toast.makeText(this, "NetWorkError", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
