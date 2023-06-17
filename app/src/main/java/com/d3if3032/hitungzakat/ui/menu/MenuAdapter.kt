package com.d3if3032.hitungzakat.ui.menu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.d3if3032.hitungzakat.R
import com.d3if3032.hitungzakat.databinding.ItemUpdateBinding
import com.d3if3032.hitungzakat.model.Emas

class MenuAdapter : RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

    private val data = mutableListOf<Emas>()

    fun updateData(newData: List<Emas>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemUpdateBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(emas: Emas) = with(binding) {
            tvJenisEmas.text = emas.jenisEmas
            tvHargaEmas.text = emas.hargaEmas
            tvTglUpdateHarga.text = emas.tglUpdateHarga
            ivEmas.setImageResource(R.drawable.emas_batangan)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemUpdateBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }
}