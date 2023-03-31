package com.d3if3032.hitungzakat

import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.d3if3032.hitungzakat.databinding.ActivityZakatPenghasilanBinding
import java.text.NumberFormat
import java.util.*

class ZakatPenghasilanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityZakatPenghasilanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityZakatPenghasilanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.hitung.setOnClickListener { hitungZakatPenghasilan() }
    }

    fun hitungZakatPenghasilan() {

        val gajiBulanan = binding.inputGajiBulanan.text.toString()
        if (TextUtils.isEmpty(gajiBulanan)) {
            Toast.makeText(this, R.string.pendapatan_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val bonus = binding.inputBonus.text.toString()
        if (TextUtils.isEmpty(bonus)) {
            Toast.makeText(this, R.string.bonus_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val totalBonus = bonus.toDouble()
        val gajiTahunan = gajiBulanan.toDouble() * 12
        fun jumlahPendapatan(a: Double, b: Double): Double {
            return a + b
        }

        val totalPendapatan = jumlahPendapatan(gajiTahunan, totalBonus)

        fun rumusZakatPenghasilan(penghasilanBersih: Double): Double {
            val nisab = 85 * 977000 // 85 gram emas * harga emas (per 26 Maret 2023)
            var zakat = 0.0

//          output wajib bayar zakat

            if (penghasilanBersih > nisab) {
                zakat = penghasilanBersih * 0.025
                binding.kondisi.text = getString(R.string.zakat_wajib)
            }else {
                binding.kondisi.text = getString(R.string.zakat_tidak_wajib)

            }
            return zakat
        }
        val hasilHitungZakatPenghasilan = rumusZakatPenghasilan(totalPendapatan)

        val formatUang = NumberFormat.getCurrencyInstance(Locale("in", "ID"))

        binding.uangZakat.text =
            getString(R.string.rp_x, formatUang.format(hasilHitungZakatPenghasilan))
        binding.totalPendapatan.text =
            getString(R.string.pendapatan_x, formatUang.format(totalPendapatan))
    }
//        binding.uangZakat.text = getString(R.string.rp_x, rumusZakatPenghasilan)
//        binding.totalPendapatan.text = getString(R.string.pendapatan_x, totalPendapatan)

}