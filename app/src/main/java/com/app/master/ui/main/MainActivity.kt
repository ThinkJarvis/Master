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
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        _mainViewModel.mainViewEventBus.value = hasFocus
    }

}
