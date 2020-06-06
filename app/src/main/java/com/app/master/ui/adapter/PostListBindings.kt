package com.app.master.ui.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.master.expection.Failure
import com.app.master.extension.Either
import com.app.master.mode.Post

@BindingAdapter("items")
fun setItems(listView: RecyclerView, items: Either<Failure, List<Post>>?) {
    items?.let {
        items.either({}, {}, {
            (listView.adapter as PostAdapter).submitList(it)
        })
    }
}