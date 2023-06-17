package com.d3if3032.hitungzakat.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.d3if3032.hitungzakat.R
import com.d3if3032.hitungzakat.databinding.FragmentMenuBinding


class MenuFragment : Fragment() {
    private val viewModel by lazy {
        ViewModelProvider(this)[MenuViewModel::class.java]
    }

    private lateinit var binding: FragmentMenuBinding
    private lateinit var myAdapter: MenuAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuBinding.inflate(layoutInflater, container, false)

        myAdapter = MenuAdapter()
        with(binding.rvUpdateEmas) {
            addItemDecoration(
                DividerItemDecoration(context, RecyclerView.VERTICAL)
            )
            adapter = myAdapter
            setHasFixedSize(true)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnZakatPenghasilan.setOnClickListener {
            it.findNavController().navigate(
                R.id.action_menuFragment_to_zakatPenghasilanFragment
            )
        }
        viewModel.getData().observe(viewLifecycleOwner) {
            myAdapter.updateData(it)
        }
    }
}
