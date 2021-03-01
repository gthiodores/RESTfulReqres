package com.gilbertthio.restfulreqres.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.gilbertthio.restfulreqres.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = MainFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner

        binding.viewModel = this.viewModel

        val adapter = MainRecyclerAdapter(MainRecyclerAdapter.MainRecyclerClickListener { user ->
            viewModel.onItemClicked(user)
        })
        binding.recycler.adapter = adapter

        viewModel.message.observe(viewLifecycleOwner, { message ->
            message?.let {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.selectedItem.observe (viewLifecycleOwner, { user ->
            user?.let {
                findNavController().navigate(
                    MainFragmentDirections.actionMainFragmentToDetailFragment(user))
                viewModel.onDoneNavigating()
            }
        })

        return binding.root
    }

}