package com.d3if3032.hitungzakat.ui.histori

import androidx.lifecycle.ViewModel
import com.d3if3032.hitungzakat.db.ZakatDao

class HistoriViewModel(db: ZakatDao): ViewModel() {
    val data = db.getLastZakat()
}