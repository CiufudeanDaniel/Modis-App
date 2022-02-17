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
        viewModel.photos.observe(requireActivity()) { p ->
            Log.v(TAG, p.toString())
            binding.photoList.adapter = RecyclerViewAdapter(p)
            binding.swipeRefreshLayout.isRefreshing = false

            if (p.size == 0)
                binding.emptyList.visibility = View.VISIBLE
            else {
                binding.emptyList.visibility = View.GONE
                binding.orderAsc.visibility = View.VISIBLE
                binding.orderDesc.visibility = View.VISIBLE
            }
            viewModel.addPhotos(p)
        }

        binding.orderAsc.setOnClickListener {
            viewModel.getPhotosFromDB(true)
        }
        binding.orderDesc.setOnClickListener {
            viewModel.getPhotosFromDB(false)
        }
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getPhotos(true)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}