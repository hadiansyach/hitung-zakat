package com.d3if3032.hitungzakat

import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.d3if3032.hitungzakat.databinding.ActivityZakatPenghasilanBinding
import com.d3if3032.hitungzakat.model.HasilZakat
import com.d3if3032.hitungzakat.model.StatusZakat
import java.text.NumberFormat
import java.util.*

class ZakatPenghasilanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityZakatPenghasilanBinding

    private val viewModel: ZakatPenghasilanViewModel by lazy {
        ViewModelProvider(this)[ZakatPenghasilanViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityZakatPenghasilanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.hitung.setOnClickListener { hitungZakat() }
        viewModel.getHasilZakat().observe(this, {showResult(it) })

    }

    private fun showResult(result: HasilZakat?) {
        if (result==null) return
        binding.uangZakatTextView.text = getString(R.string.zakat_x)
        binding.pendapatanTextView.text = getString(R.string.pendapatan_x)
    }

    private fun hitungZakat() {

    }





//        val hasilHitungZakatPenghasilan = rumusZakatPenghasilan(pendapatanPertahun)
//
//        val formatUang = NumberFormat.getCurrencyInstance(Locale("in", "ID"))
//
//        binding.uangZakat.text =
//            getString(R.string.rp_x, formatUang.format(hasilHitungZakatPenghasilan))
//        binding.totalPendapatan.text =
//            getString(R.string.pendapatan_x, formatUang.format(pendapatanPertahun))


    private fun getStatusLabel(status: StatusZakat): String {
        val stringRes = when (status) {
            StatusZakat.WAJIB -> R.string.zakat_wajib
            StatusZakat.TIDAK_WAJIB -> R.string.zakat_tidak_wajib
        }
        return getString(stringRes)
    }
}