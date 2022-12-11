package com.example.buses

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.buses.adapter.BusesListAdapter
import com.example.buses.databinding.FragmentBusesListBinding
import com.example.buses.model.Bus

class BusesListFragment : Fragment() {

    private var _binding: FragmentBusesListBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentBusesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = BusesListAdapter {
            val action = BusesListFragmentDirections
                .actionBusesListFragmentToBusPreparationFragment(it.id)
            findNavController().navigate(action)
        }

        adapter.submitList(listOf(Bus(id = 1, placa = "AAA-111"), Bus(id = 2, placa = "ABC-123")))

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