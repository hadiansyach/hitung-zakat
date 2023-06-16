package com.d3if3032.hitungzakat.ui.histori

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.d3if3032.hitungzakat.R
import com.d3if3032.hitungzakat.databinding.FragmentHistoriBinding
import com.d3if3032.hitungzakat.db.ZakatDb
import com.d3if3032.hitungzakat.model.hitungZakat

class HistoriFragment : Fragment() {
    private val viewModel: HistoriViewModel by lazy {
        val db = ZakatDb.getInstance(requireContext())
        val factory = HistoriViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[HistoriViewModel::class.java]
    }

    private lateinit var binding: FragmentHistoriBinding
    private lateinit var myAdapter: HistoriAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoriBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        myAdapter = HistoriAdapter()
        with(binding.recyclerView) {
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            adapter = myAdapter
            setHasFixedSize(true)
        }
        viewModel.data.observe(viewLifecycleOwner){
            Log.d("HistoriFragment", "Jumlah data: ${it.size}")
            binding.dataKosong.visibility = if (it.isEmpty())
                View.VISIBLE else View.GONE
            myAdapter.submitList(it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_histori){
            findNavController().navigate(
                R.id.action_zakatPenghasilanFragment_to_historiFragment)
            return true
        }
        if (item.itemId == R.id.menu_about){
            findNavController().navigate(
                R.id.action_zakatPenghasilanFragment_to_aboutFragment)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}