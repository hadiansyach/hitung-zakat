package com.d3if3032.hitungzakat.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.d3if3032.hitungzakat.model.HasilZakat
import com.d3if3032.hitungzakat.model.StatusZakat

class ZakatPenghasilanViewModel: ViewModel() {
    private val hasilZakat = MutableLiveData<HasilZakat?>()

    fun hitungZakat(gaji: Double, bonus: Double) {
        val pendapatanPertahun = gaji * 12 + bonus
        val zakat = pendapatanPertahun * 0.025
        val status = getStatus(pendapatanPertahun)
        hasilZakat.value = HasilZakat(pendapatanPertahun, zakat, status)
    }

    fun getHasilZakat():LiveData<HasilZakat?> = hasilZakat

    private fun getStatus(pendapatan: Double): StatusZakat {
        val nisab = 85 * 977000
        val status = if (pendapatan > nisab) {

            StatusZakat.WAJIB
        } else {
            StatusZakat.TIDAK_WAJIB
        }
        return status
    }
}