package com.example.buses

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.buses.databinding.FragmentAddBusBinding
import com.example.buses.util.LoadingView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddBusFragment : Fragment() {

    private var _binding: FragmentAddBusBinding? = null
    private val binding get() = _binding!!
    private lateinit var loader: LoadingView
    private val viewModel by viewModels<BusViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBusBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loader = LoadingView(requireContext())

        viewModel.loading.observe(viewLifecycleOwner) {
            if (it) {
                loader.show()
            } else {
                loader.dismiss()
            }
        }

        viewModel.insertResult.observe(viewLifecycleOwner) {
            if (it != null) {
                if (it) {
                    findNavController().navigate(R.id.action_addBusFragment_to_busesListFragment)
                } else {
                    Toast.makeText(requireContext(), "Ocurrió un error. Intente más tarde.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.btnSave.setOnClickListener {
            viewModel.addBus(binding.inputPlaca.text.toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}