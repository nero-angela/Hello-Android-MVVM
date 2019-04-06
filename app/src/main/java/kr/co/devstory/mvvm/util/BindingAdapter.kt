package kr.co.devstory.mvvm.util

import android.text.TextUtils
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

@BindingAdapter("bind_adapter")
fun setBindAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) {
    adapter.let {
        view.adapter = it
    }
}

@BindingAdapter("url")
fun setImageUrl(view: ImageView, profileUrl: String?) {
    if(!TextUtils.isEmpty(profileUrl)) {
        Glide.with(view.context)
                .load(profileUrl)
                .into(view)
    }
}