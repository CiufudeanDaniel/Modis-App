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
import com.example.modisapp.view_models.PhotoViewModel

private const val TAG = "PhotoListFragment"

class PhotoListFragment : Fragment() {
    private var _binding: FragmentPhotoListBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: PhotoViewModel

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
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.photos.observe(requireActivity()) { p ->
            Log.v(TAG, p.toString())
            binding.photoList.adapter = RecyclerViewAdapter(p)
            viewModel.addPhotos(p)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}