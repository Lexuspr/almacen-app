package com.example.buses

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.example.buses.adapter.PassengerListAdapter
import com.example.buses.databinding.FragmentScanPassengersBinding
import com.example.buses.model.Passenger

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
                    R.id.action_finish -> {

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
            Passenger(id = 1, name = "Jesus Palo"),
            Passenger(id = 1, name = "Jesus Palo"),
            Passenger(id = 1, name = "Jesus Palo"),
            Passenger(id = 1, name = "Jesus Palo"),
            Passenger(id = 1, name = "Jesus Palo"),
            Passenger(id = 1, name = "Jesus Palo"),
            Passenger(id = 1, name = "Jesus Palo"),
            Passenger(id = 1, name = "Jesus Palo"),
            Passenger(id = 1, name = "Jesus Palo"),
            Passenger(id = 1, name = "Jesus Palo"),
            Passenger(id = 1, name = "Jesus Palo"),
            Passenger(id = 1, name = "Jesus Palo"),
            Passenger(id = 1, name = "Jesus Palo"),
            Passenger(id = 1, name = "Jesus Palo"),
            Passenger(id = 1, name = "Jesus Palo"),
            Passenger(id = 1, name = "Jesus Palo"),
            Passenger(id = 1, name = "Jesus Palo"),
            Passenger(id = 1, name = "Jesus Palo"),
            Passenger(id = 1, name = "Jesus Palo"),

        ))

        binding.apply {
            recyclerView.adapter = adapter

        }
    }



}