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

    @Query("SELECT * FROM zakat ORDER BY id DESC LIMIT 1")
    fun getLastHistoryData(): LiveData<ZakatEntity>

    @Query("DELETE FROM zakat")
    fun deleteAllHistory()

    @Query("DELETE FROM zakat WHERE id = :id")
    fun deleteHistory(id: Long)

    @Query("SELECT * FROM zakat WHERE id = :id")
    fun getHistoryById(id: Long): LiveData<ZakatEntity>
}