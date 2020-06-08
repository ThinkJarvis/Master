package com.app.master.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.master.data.repository.TaskRepository


class MainViewModel constructor(private val taskRepository: TaskRepository) : ViewModel() {

    val mainViewEventBus = MutableLiveData<Boolean>()

}