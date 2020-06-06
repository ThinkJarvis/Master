package com.app.master.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.master.data.repository.TaskRepository
import com.app.master.expection.Failure
import com.app.master.extension.Either
import com.app.master.mode.Post
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ListViewModel constructor(private val taskRepository: TaskRepository) : ViewModel() {

    private val _postLiveData = MutableLiveData<Either<Failure, List<Post>>>()
    val postLiveData = _postLiveData


    fun getAllPosts() {
        viewModelScope.launch {
            taskRepository.getAllPosts().collect {
                postLiveData.value = it
            }
        }
    }
}
