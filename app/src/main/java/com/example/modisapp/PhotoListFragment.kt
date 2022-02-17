package com.example.modisapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.modisapp.adapters.RecyclerViewAdapter
import com.example.modisapp.databinding.FragmentPhotoListBinding
import com.example.modisapp.models.PhotoModel
import com.example.modisapp.view_models.PhotoViewModel

private const val TAG = "PhotoListFragment"

class PhotoListFragment : Fragment() {
    private var _binding: FragmentPhotoListBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: PhotoViewModel
    private var photos = arrayListOf<PhotoModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhotoListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[PhotoViewModel::class.java]
        viewModel.photos.observe(requireActivity()) { p ->
            Log.v(TAG, p.toString())
            photos = p
            binding.photoList.adapter = RecyclerViewAdapter(photos)

            if (p.size == 0)
                binding.emptyList.visibility = View.VISIBLE
            for (photo in p)
                viewModel.addPhoto(photo)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}