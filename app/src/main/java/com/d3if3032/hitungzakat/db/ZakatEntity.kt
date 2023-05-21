package com.d3if3032.hitungzakat.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "zakat")
data class ZakatEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var tanggal: Long = System.currentTimeMillis(),
    var gaji: Double,
    var bonus: Double
)
