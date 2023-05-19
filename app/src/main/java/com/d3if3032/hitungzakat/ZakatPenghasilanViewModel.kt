package com.d3if3032.hitungzakat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.d3if3032.hitungzakat.model.HasilZakat
import com.d3if3032.hitungzakat.model.StatusZakat

class ZakatPenghasilanViewModel: ViewModel() {
    private val hasilZakat = MutableLiveData<HasilZakat?>()

    private fun hitungZakat(gaji: Double, bonus: Double) {
        val pendapatanPertahun = gaji * 12 + bonus
        var zakat = pendapatanPertahun * 0.025
        hasilZakat.value = HasilZakat(pendapatanPertahun, zakat)
    }

    fun getHasilZakat():LiveData<HasilZakat?> = hasilZakat

    private fun getStatus(zakat: Double): StatusZakat {
        val nisab = 85 * 977000
        val status = if (zakat > nisab) {
            StatusZakat.WAJIB
        } else {
            StatusZakat.TIDAK_WAJIB
        }
        return status
    }
}