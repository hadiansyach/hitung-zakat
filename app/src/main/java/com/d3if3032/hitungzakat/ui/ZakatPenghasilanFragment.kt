package com.d3if3032.hitungzakat.ui

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.d3if3032.hitungzakat.R
import com.d3if3032.hitungzakat.databinding.FragmentZakatPenghasilanBinding
import com.d3if3032.hitungzakat.model.HasilZakat
import com.d3if3032.hitungzakat.model.StatusZakat
import java.text.NumberFormat
import java.util.Locale

class ZakatPenghasilanFragment : Fragment() {

    private lateinit var binding: FragmentZakatPenghasilanBinding

    private val viewModel: ZakatPenghasilanViewModel by lazy {
        ViewModelProvider(requireActivity())[ZakatPenghasilanViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentZakatPenghasilanBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnHitung.setOnClickListener { hitungZakat() }
        viewModel.getHasilZakat().observe(requireActivity(), { showResult(it) })
        binding.btnBagikan.setOnClickListener { shareData() }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_about) {
            findNavController().navigate(
                R.id.action_zakatPenghasilanFragment_to_aboutFragment
            )
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showResult(result: HasilZakat?) {
        val formatUang = NumberFormat.getCurrencyInstance(Locale("in", "ID"))
        if (result == null) return
        binding.uangZakatTextView.text =
            getString(R.string.zakat_x, formatUang.format(result.zakat))
        binding.pendapatanTextView.text =
            getString(R.string.pendapatan_x, formatUang.format(result.pendapatanPertahun))
        binding.statusTextView.text =
            getString(R.string.status_zakat_x, getStatusLabel(result.status))
        binding.btnBagikan.visibility = View.VISIBLE
    }

    private fun hitungZakat() {
        val gaji = binding.inputGajiBulanan.text.toString()
        if (TextUtils.isEmpty(gaji)) {
            Toast.makeText(context, R.string.pendapatan_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val bonus = binding.inputBonus.text.toString()
        if (TextUtils.isEmpty(bonus)) {
            Toast.makeText(context, R.string.bonus_invalid, Toast.LENGTH_LONG).show()
            return
        }
        viewModel.hitungZakat(
            gaji.toDouble(),
            bonus.toDouble()
        )
    }

    private fun getStatusLabel(status: StatusZakat): String {
        val stringRes = when (status) {
            StatusZakat.WAJIB -> R.string.zakat_wajib
            StatusZakat.TIDAK_WAJIB -> R.string.zakat_tidak_wajib
        }
        return getString(stringRes)
    }

    private fun shareData() {
        val message = getString(R.string.bagikan_template,
            binding.uangZakatTextView.text,
            binding.pendapatanTextView.text
        )
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, message)
        if (shareIntent.resolveActivity(
                requireActivity().packageManager) != null) {
            startActivity(shareIntent)
        }
    }

}