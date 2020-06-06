package com.app.master.ui.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels

import com.app.master.databinding.ListFragmentBinding
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
        _listViewModel.getAllPosts()
    }


    private fun setUpAdapter() {
        _listAdapter = PostAdapter(_listViewModel)
        recycler_post.adapter = _listAdapter
    }

}
