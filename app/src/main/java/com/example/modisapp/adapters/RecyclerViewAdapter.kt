package com.example.modisapp.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.example.modisapp.PhotoListFragmentDirections
import com.example.modisapp.R
import com.example.modisapp.databinding.ItemPhotoLayoutBinding
import com.example.modisapp.models.PhotoModel


private const val TAG = "recycler_view_adapter"

class RecyclerViewAdapter(private val mPhotos: ArrayList<PhotoModel>) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d(TAG, "onCreateViewHolder: called.")

        val binding = ItemPhotoLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = mPhotos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder: called.")
        holder.bind(photo = mPhotos[position])
    }
}

class ViewHolder(private val itemBinding: ItemPhotoLayoutBinding) : RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(photo: PhotoModel) {
        val glideUrl = GlideUrl(photo.thumbnailUrl, LazyHeaders.Builder()
            .addHeader("User-Agent",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_2) AppleWebKit / 537.36(KHTML, like Gecko) Chrome  47.0.2526.106 Safari / 537.36")
            .build())

        Glide.with(itemBinding.image.context)
            .load(glideUrl)
            .placeholder(R.drawable.ic_launcher_background)
            .into(itemBinding.image)

        itemBinding.photo = photo
        itemBinding.root.setOnClickListener {
            val navController = Navigation.findNavController(itemBinding.image)
            navController.navigate(PhotoListFragmentDirections.actionPhotoListFragmentToPhotoFragment(photo))
        }
    }
}