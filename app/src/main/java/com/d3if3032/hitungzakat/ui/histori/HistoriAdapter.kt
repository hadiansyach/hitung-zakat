package com.d3if3032.hitungzakat.ui.histori

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.icu.text.SimpleDateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.d3if3032.hitungzakat.R
import com.d3if3032.hitungzakat.databinding.ItemHistoriBinding
import com.d3if3032.hitungzakat.db.ZakatDao
import com.d3if3032.hitungzakat.db.ZakatDb
import com.d3if3032.hitungzakat.db.ZakatEntity
import com.d3if3032.hitungzakat.model.StatusZakat
import com.d3if3032.hitungzakat.model.hitungZakat
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.NumberFormat
import java.util.Date
import java.util.Locale

class HistoriAdapter : ListAdapter<ZakatEntity, HistoriAdapter.ViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<ZakatEntity>() {
                override fun areItemsTheSame(oldData: ZakatEntity, newData: ZakatEntity): Boolean {
                    return oldData.id == newData.id
                }

                override fun areContentsTheSame(
                    oldData: ZakatEntity,
                    newData: ZakatEntity
                ): Boolean {
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
        Log.d("s", "masuk sini")
        holder.bind(getItem(position), holder.itemView)
    }

    class ViewHolder(
        private val binding: ItemHistoriBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val dateFormatter = SimpleDateFormat(
            "dd MMMM yyyy",
            Locale("id", "ID")
        )

        fun bind(item: ZakatEntity,view: View) = with(binding) {
            val hasilZakat = item.hitungZakat()
            Log.d("hasil", hasilZakat.pendapatanPertahun.toString())
            statusIcon.text = hasilZakat.status.toString().substring(0, 1)
            val colorRes = when (hasilZakat.status) {
                StatusZakat.WAJIB -> R.color.wajib
                StatusZakat.TIDAK_WAJIB -> R.color.tidak_wajib
            }

            val circleBg = statusIcon.background as GradientDrawable
            circleBg.setColor(ContextCompat.getColor(root.context, colorRes))
            tanggalTextView.text = dateFormatter.format(Date(item.tanggal))
            statusZakat.text = hasilZakat.status.toString()
            val formatUang = NumberFormat.getCurrencyInstance(Locale("in", "ID"))

            zakatTextView.text = formatUang.format(hasilZakat.zakat)
            pendapatanTextView.text = formatUang.format(hasilZakat.pendapatanPertahun)

            binding.btnDelete.setOnClickListener{
                hapusData(item.id, view.context)
            }
        }

        private fun hapusData(id: Long, context: Context) {
            val db = ZakatDb.getInstance(context)
            val ZakatDao = db.dao
            MaterialAlertDialogBuilder(context)
                .setMessage(context.getString(R.string.konfirmasi_hapus))
                .setPositiveButton(context.getString(R.string.hapus)) { _, _ ->
                    CoroutineScope(Dispatchers.IO).launch {
                        withContext(Dispatchers.IO) {
                            ZakatDao.deleteHistory(id)
                        }
                    }
                }
                .setNegativeButton(context.getString(R.string.batal)) { dialog, _ ->
                    dialog.cancel()
                }
                .show()
        }
    }
}
