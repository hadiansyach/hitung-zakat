package com.d3if3032.hitungzakat.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ZakatDao {
    @Insert
    fun insert(zakat: ZakatEntity)

    @Query("SELECT * FROM zakat ORDER BY id DESC")
    fun getLastZakat(): LiveData<List<ZakatEntity?>>
}