package com.example.explorethecity

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.explorethecity.model.Event
import com.example.explorethecity.ui.eventlist.EventAdapter

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri) {
            placeholder(R.drawable.baseline_downloading_24)
            error(R.drawable.baseline_broken_image_24)
        }
    }
}
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView,
                     data: LiveData<List<Event>>?) {
    val adapter = recyclerView.adapter as EventAdapter
    adapter.submitList(data?.value)
}