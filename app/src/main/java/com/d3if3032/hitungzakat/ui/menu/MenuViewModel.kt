package com.d3if3032.hitungzakat.ui.menu

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.d3if3032.hitungzakat.R
import com.d3if3032.hitungzakat.model.Emas
import com.d3if3032.hitungzakat.network.ApiStatus
import com.d3if3032.hitungzakat.network.EmasApi
import com.d3if3032.hitungzakat.network.UpdateWorker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.concurrent.TimeUnit

class MenuViewModel : ViewModel() {
    private val data = MutableLiveData<List<Emas>>()
    private val status = MutableLiveData<ApiStatus>()

    init {
//        data.value = initData()
        retrieveData()
    }

    private fun retrieveData() {
        viewModelScope.launch(Dispatchers.IO) {
            status.postValue(ApiStatus.LOADING)
            try {
//                val result = EmasApi.service.getEmas()
//                Log.d("Menu ViewModel", "Success: $result")
                data.postValue(EmasApi.service.getEmas())
                status.postValue(ApiStatus.SUCCESS)

            } catch (e: Exception) {
                Log.d("Menu ViewModel", "Failure: ${e.message}")
                status.postValue(ApiStatus.FAILED)
            }
        }
    }

    fun getData(): LiveData<List<Emas>> = data

    fun getStatus(): LiveData<ApiStatus> = status

    fun scheduleUpdater(app: Application) {
        val request = OneTimeWorkRequestBuilder<UpdateWorker>()
            .setInitialDelay(1, TimeUnit.MINUTES)
            .build()
        WorkManager.getInstance(app).enqueueUniqueWork(
            UpdateWorker.WORK_NAME,
            ExistingWorkPolicy.REPLACE,
            request
        )
    }

}