package com.example.buses

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import com.example.buses.databinding.FragmentBusPreparationBinding

class BusPreparationFragment : Fragment() {

    private var _binding: FragmentBusPreparationBinding? = null
    private val binding get() = _binding!!
    private val fromLocationList = listOf("Punto A", "Punto B", "Punto C")
    private val destinationLocationList = listOf("Punto A", "Punto B", "Punto C")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentBusPreparationBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnStartRoute.setOnClickListener {
            findNavController().navigate(R.id.action_busPreparationFragment_to_scanPassengersFragment)
        }

        setPickers()
    }

    private fun setPickers() {
        val fromAdapter = ArrayAdapter(requireContext(), R.layout.list_item_location, fromLocationList)
        binding.selectFromLocation.setAdapter(fromAdapter)

        val destinationAdapter = ArrayAdapter(requireContext(), R.layout.list_item_location, destinationLocationList)
        binding.selectDestinationLocation.setAdapter(destinationAdapter)

    }

    // formulario origen, destino, pasajeros, etc...
    // continua a escaneo pasajeros
}