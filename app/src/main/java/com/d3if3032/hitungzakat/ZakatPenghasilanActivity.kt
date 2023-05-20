package com.d3if3032.hitungzakat

import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.d3if3032.hitungzakat.databinding.ActivityZakatPenghasilanBinding
import com.d3if3032.hitungzakat.model.HasilZakat
import com.d3if3032.hitungzakat.model.StatusZakat
import java.text.NumberFormat
import java.util.Locale

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
        val formatUang = NumberFormat.getCurrencyInstance(Locale("in", "ID"))
        if (result==null) return
        binding.uangZakatTextView.text = getString(R.string.zakat_x, formatUang.format(result.zakat))
        binding.pendapatanTextView.text = getString(R.string.pendapatan_x, formatUang.format(result.pendapatanPertahun))
        binding.statusTextView.text = getString(R.string.status_zakat_x, getStatusLabel(result.status))
    }

    private fun hitungZakat() {
        val gaji = binding.inputGajiBulanan.text.toString()
        if (TextUtils.isEmpty(gaji)) {
            Toast.makeText(this, R.string.pendapatan_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val bonus = binding.inputBonus.text.toString()
        if (TextUtils.isEmpty(bonus)) {
            Toast.makeText(this, R.string.bonus_invalid, Toast.LENGTH_LONG).show()
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
}