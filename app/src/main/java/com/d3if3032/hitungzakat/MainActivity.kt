package com.d3if3032.hitungzakat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.d3if3032.hitungzakat.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

//    private lateinit var binding = ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnZakatPhln.setOnClickListener(){
            val nextZakatPenghasilanIntent = Intent(this, ZakatPenghasilanActivity::class.java)
            startActivity(nextZakatPenghasilanIntent)



        }
    }
}