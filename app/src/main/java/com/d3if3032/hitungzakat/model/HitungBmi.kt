package com.d3if3032.hitungzakat.model

import com.d3if3032.hitungzakat.db.ZakatEntity

fun ZakatEntity.hitungZakat(): HasilZakat {
    val pendapatanPertahun = gaji * 12 + bonus
    val nisab = 85 * 1063000
    val zakat = pendapatanPertahun * 0.025
    val status = when {
        pendapatanPertahun > nisab -> StatusZakat.WAJIB
        else -> StatusZakat.TIDAK_WAJIB
    }
    return HasilZakat(pendapatanPertahun, zakat, status)
}