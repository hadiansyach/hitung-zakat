package com.d3if3032.hitungzakat.ui.menu

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d3if3032.hitungzakat.R
import com.d3if3032.hitungzakat.model.Emas
import com.d3if3032.hitungzakat.network.EmasApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class MenuViewModel : ViewModel() {
    private val data = MutableLiveData<List<Emas>>()

    init {
//        data.value = initData()
        retrieveData()
    }

    private fun retrieveData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
//                val result = EmasApi.service.getEmas()
//                Log.d("Menu ViewModel", "Success: $result")
                data.postValue(EmasApi.service.getEmas())
            } catch (e: Exception) {
                Log.d("Menu ViewModel", "Failure: ${e.message}")
            }
        }
    }

    fun getData(): LiveData<List<Emas>> = data
}