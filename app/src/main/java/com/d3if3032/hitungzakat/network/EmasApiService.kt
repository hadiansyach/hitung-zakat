package com.d3if3032.hitungzakat.network

import com.d3if3032.hitungzakat.model.Emas
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


private const val BASE_URL =
    "https://raw.githubusercontent.com/" + "hadiansyach/hitung-zakat-api/main/hitung-zakat/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface EmasApiService {
    @GET("harga-emas.json")
    suspend fun getEmas(): List<Emas>
}

object EmasApi {
    val service: EmasApiService by lazy {
        retrofit.create(EmasApiService::class.java)
    }

    fun getEmasUrl(imageId: String): String {
        return "$BASE_URL$imageId.jpg"
    }
}

//enum class ApiStatus { LOADING, SUCCESS, FAILED }