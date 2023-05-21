package com.d3if3032.hitungzakat.ui.histori

import android.graphics.drawable.GradientDrawable
import android.icu.text.SimpleDateFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.d3if3032.hitungzakat.R
import com.d3if3032.hitungzakat.databinding.ItemHistoriBinding
import com.d3if3032.hitungzakat.db.ZakatEntity
import com.d3if3032.hitungzakat.model.StatusZakat
import com.d3if3032.hitungzakat.model.hitungZakat
import java.util.Date
import java.util.Locale

class HistoriAdapter : ListAdapter<ZakatEntity, HistoriAdapter.ViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<ZakatEntity>() {
                override fun areItemsTheSame(oldData: ZakatEntity, newData: ZakatEntity): Boolean {
                    return oldData.id == newData.id
                }

                override fun areContentsTheSame(oldData: ZakatEntity, newData: ZakatEntity): Boolean {
                    return oldData == newData
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoriBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ItemHistoriBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val dateFormatter = SimpleDateFormat(
            "dd MMMM yyyy",
            Locale("id", "ID")
        )

        fun bind(item: ZakatEntity) = with(binding) {
            val hasilZakat = item.hitungZakat()
            statusZakat.text = hasilZakat.status.toString().substring(0, 1)
            val colorRes = when (hasilZakat.status) {
                StatusZakat.WAJIB -> R.color.wajib
                StatusZakat.TIDAK_WAJIB -> R.color.tidak_wajib
            }
            val circleBg = statusIcon.background as GradientDrawable
            circleBg.setColor(ContextCompat.getColor(root.context, colorRes))

            tanggalTextView.text = dateFormatter.format(Date(item.tanggal))
            statusZakat.text = hasilZakat.status.toString()
            zakatTextView.text = hasilZakat.zakat.toString()
            pendapatanTextView.text = hasilZakat.pendapatanPertahun.toString()
        }
    }
}