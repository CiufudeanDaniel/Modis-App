package com.example.modisapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.example.modisapp.databinding.FragmentPhotoBinding
import com.example.modisapp.models.PhotoModel


private const val ARG_PARAM = "photo"
private const val TAG = "PhotoFragment"

class PhotoFragment : Fragment() {
    private var _binding: FragmentPhotoBinding? = null
    private val binding get() = _binding!!
    private lateinit var photo: PhotoModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            photo = it.getParcelable(ARG_PARAM)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhotoBinding.inflate(inflater, container, false)
        binding.photo = photo
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.v(TAG, photo.toString())
        val glideUrl = GlideUrl(photo.thumbnailUrl, LazyHeaders.Builder()
            .addHeader("User-Agent",
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_2) AppleWebKit / 537.36(KHTML, like Gecko) Chrome  47.0.2526.106 Safari / 537.36")
            .build())

        Glide.with(this)
            .load(glideUrl)
            .placeholder(R.drawable.ic_launcher_background)
            .into(binding.image)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}