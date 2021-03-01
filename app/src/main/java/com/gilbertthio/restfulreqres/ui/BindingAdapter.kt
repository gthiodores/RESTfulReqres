package com.gilbertthio.restfulreqres.ui

import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gilbertthio.restfulreqres.R
import com.gilbertthio.restfulreqres.network.Users
import com.gilbertthio.restfulreqres.ui.main.MainRecyclerAdapter

@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, url: String?) {
    Glide.with(imageView)
        .load(url)
        .placeholder(R.drawable.ic_baseline_account_circle_24)
        .error(R.drawable.ic_baseline_account_circle_24)
        .into(imageView)
}

@BindingAdapter("fullName")
fun setFullName(textView: TextView, user: Users) {
    textView.text = textView.context.getString(R.string.full_name, user.lastName, user.firstName)
}

@BindingAdapter("listItem")
fun submitListItem(recyclerView: RecyclerView, users: List<Users>?) {
    users?.let {
        val adapter = recyclerView.adapter as MainRecyclerAdapter
        adapter.submitList(users)
    }
}

@BindingAdapter("currentPage", "maxPage")
fun enableMaxButton(button: Button, currentPage: Int, maxPage: Int) {
    button.isEnabled = currentPage < maxPage
}

@BindingAdapter("page")
fun enableMinButton(button: Button, currentPage: Int) {
    button.isEnabled = currentPage > 1
}