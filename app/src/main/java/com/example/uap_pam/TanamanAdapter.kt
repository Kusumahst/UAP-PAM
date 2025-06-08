package com.example.uap_pam

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.uap_pam.databinding.ItemTanamanBinding

class TanamanAdapter(
    private val items: MutableList<Tanaman>,
    private val onDelete: (Int) -> Unit,
    private val onDetail: (Tanaman) -> Unit
) : RecyclerView.Adapter<TanamanAdapter.PlantViewHolder>() {

    inner class PlantViewHolder(val binding: ItemTanamanBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Tanaman) {
            binding.tvNama.text = item.nama
            binding.tvHarga.text = item.harga
            binding.imgTanaman.setImageResource(item.imageResId)

            binding.btnHapus.setOnClickListener {
                onDelete(adapterPosition)
            }

            binding.btnDetail.setOnClickListener {
                onDetail(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
        val binding = ItemTanamanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlantViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}
