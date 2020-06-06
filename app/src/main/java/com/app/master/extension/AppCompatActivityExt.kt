package com.app.master.extension

import androidx.appcompat.app.AppCompatActivity
import com.app.master.ViewModelFactory

fun AppCompatActivity.getViewModelFactory(): ViewModelFactory {
    return ViewModelFactory(this)
}
