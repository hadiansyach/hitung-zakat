package com.d3if3032.hitungzakat.ui.zakat_penghasilan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d3if3032.hitungzakat.db.ZakatDao
import com.d3if3032.hitungzakat.db.ZakatEntity
import com.d3if3032.hitungzakat.model.HasilZakat
import com.d3if3032.hitungzakat.model.StatusZakat
import com.d3if3032.hitungzakat.model.hitungZakat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ZakatPenghasilanViewModel(private val db: ZakatDao): ViewModel() {
    private val hasilZakat = MutableLiveData<HasilZakat?>()

    val data = db.getLastZakat()

    fun hitungZakat(gaji: Double, bonus: Double) {

        val dataZakat = ZakatEntity(
            gaji = gaji,
            bonus = bonus
        )
        hasilZakat.value = dataZakat.hitungZakat()
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                db.insert(dataZakat)
            }
        }
    }

    fun getHasilZakat():LiveData<HasilZakat?> = hasilZakat
}