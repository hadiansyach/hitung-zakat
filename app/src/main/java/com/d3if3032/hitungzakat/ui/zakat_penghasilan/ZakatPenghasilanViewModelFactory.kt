package com.d3if3032.hitungzakat.ui.zakat_penghasilan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.d3if3032.hitungzakat.db.ZakatDao
import java.lang.IllegalArgumentException

class ZakatPenghasilanViewModelFactory(
    private val db: ZakatDao
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ZakatPenghasilanViewModel::class.java)) {
            return ZakatPenghasilanViewModel(db) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
