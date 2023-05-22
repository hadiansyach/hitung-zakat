package com.d3if3032.hitungzakat.ui.histori

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d3if3032.hitungzakat.db.ZakatDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HistoriViewModel(private val db: ZakatDao): ViewModel() {
    val data = db.getLastZakat()

    fun hapusData(id: Long) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            db.deleteHistory(id)
        }
    }
}