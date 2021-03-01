package com.gilbertthio.restfulreqres.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.gilbertthio.restfulreqres.databinding.FragmentDetailBinding

/**
 * A fragment to display the details of the selected item
 * in main fragment's recycler view
 */
class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentDetailBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner

        val user = DetailFragmentArgs.fromBundle(requireArguments()).user
        val application = requireActivity().application
        val viewModel by viewModels<DetailViewModel> { DetailViewModelFactory(application, user) }
        binding.viewModel = viewModel

        return binding.root
    }

}