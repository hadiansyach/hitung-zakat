package com.d3if3032.hitungzakat

import android.R.attr.button
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.d3if3032.hitungzakat.databinding.FragmentMenuBinding
import com.d3if3032.hitungzakat.ui.ZakatPenghasilanFragment


class MenuFragment : Fragment() {

    private lateinit var binding: FragmentMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnZakatPenghasilan.setOnClickListener {
            it.findNavController().navigate(
                R.id.action_menuFragment_to_zakatPenghasilanFragment
            )
        }
    }
}
