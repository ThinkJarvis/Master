package com.app.master.ui.list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer

import com.app.master.databinding.ListFragmentBinding
import com.app.master.expection.Failure
import com.app.master.extension.getViewModelFactory
import com.app.master.ui.adapter.PostAdapter
import kotlinx.android.synthetic.main.list_fragment.*

class ListFragment : Fragment() {

    private lateinit var _viewDataBinding: ListFragmentBinding
    private val _listViewModel: ListViewModel by viewModels<ListViewModel> { getViewModelFactory() }

    private lateinit var _listAdapter: PostAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _viewDataBinding = ListFragmentBinding.inflate(inflater, container, false).apply {
            listViewModel = _listViewModel
        }
        return _viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        _viewDataBinding.lifecycleOwner = this
        setUpAdapter()
        _listViewModel.postLiveData.observe(this.viewLifecycleOwner, Observer {
            it.either(::handleLoading, ::handleFailure) {}
        })
        _listViewModel.getAllPosts()
    }


    private fun setUpAdapter() {
        _listAdapter = PostAdapter(_listViewModel)
        recycler_post.adapter = _listAdapter
    }

    private fun handleLoading() {
        Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
    }

    private fun handleFailure(failure: Failure) {
        when (failure) {
            is Failure.NetWorkError -> {
                Toast.makeText(context, "NetWorkError", Toast.LENGTH_SHORT).show()
            }
        }
    }

}
