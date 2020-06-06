package com.app.master

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.app.master.data.ServiceLocator
import com.app.master.ui.list.ListViewModel
import com.app.master.ui.main.MainViewModel

class ViewModelFactory constructor(
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T = with(modelClass) {
        when {
            isAssignableFrom(MainViewModel::class.java) ->
                MainViewModel(ServiceLocator.provideTaskRepository())


            isAssignableFrom(ListViewModel::class.java) ->
                ListViewModel(ServiceLocator.provideTaskRepository())

            else ->
                throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        } as T
    }


}