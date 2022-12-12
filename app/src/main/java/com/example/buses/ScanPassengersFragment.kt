package com.example.buses

import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.example.buses.adapter.PassengerListAdapter
import com.example.buses.databinding.FragmentScanPassengersBinding
import com.example.buses.model.Passenger
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ScanPassengersFragment : Fragment() {

    private var _binding: FragmentScanPassengersBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        (requireActivity() as AppCompatActivity).supportActionBar?.setHomeButtonEnabled(false)

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            // Handle the back button event
            Toast.makeText(requireContext(), "Deshabilitado", Toast.LENGTH_SHORT).show()

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentScanPassengersBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_scan, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_manual_input -> {
                        true
                    }

                    else -> false
                }
            }

            override fun onPrepareMenu(menu: Menu) {
                super.onPrepareMenu(menu)

                val item = menu.findItem(R.id.action_logout)
                item.isVisible = false
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        val adapter = PassengerListAdapter {

        }

        adapter.submitList(listOf(
            Passenger(id = 1, name = "Juan Perez"),
            Passenger(id = 1, name = "Juan Perez"),
            Passenger(id = 1, name = "Juan Perez"),
            Passenger(id = 1, name = "Juan Perez"),
            Passenger(id = 1, name = "Juan Perez"),
            Passenger(id = 1, name = "Juan Perez"),
            Passenger(id = 1, name = "Juan Perez"),
            Passenger(id = 1, name = "Juan Perez"),

        ))

        binding.apply {
            recyclerView.adapter = adapter
        }

        binding.btnFinishRoute.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setMessage("¿Finalizar recorrido?")
                .setPositiveButton("Finalizar") { dialog, _ ->
                    dialog.dismiss()
                    findNavController().navigate(R.id.action_scanPassengersFragment_to_busesListFragment)
                }
                .setNegativeButton("Cancelar") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }
}