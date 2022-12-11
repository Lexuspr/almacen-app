package com.example.buses

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.buses.adapter.BusesListAdapter
import com.example.buses.databinding.FragmentBusesListBinding
import com.example.buses.util.LoadingView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BusesListFragment : Fragment() {

    private var _binding: FragmentBusesListBinding? = null
    private val binding get() = _binding!!
    private lateinit var loader: LoadingView
    private val viewModel by viewModels<BusViewModel>()

    private val adapter = BusesListAdapter {
        val action = BusesListFragmentDirections
            .actionBusesListFragmentToBusPreparationFragment(it.id)
        findNavController().navigate(action)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentBusesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loader = LoadingView(requireContext())

        viewModel.buses.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        viewModel.loading.observe(viewLifecycleOwner) {
            if (it) {
                loader.show()
            } else {
                loader.dismiss()
            }
        }

        viewModel.getBuses()

        binding.apply {
            recyclerView.adapter = adapter
            addBusFab.setOnClickListener {
                findNavController().navigate(R.id.action_busesListFragment_to_addBusFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}