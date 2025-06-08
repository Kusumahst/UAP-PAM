package com.example.uap_pam.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.uap_pam.databinding.ItemTanamanBinding
import com.example.uap_pam.Tanaman

class TanamanAdapter(
    private val items: List<Tanaman>,
    private val onDelete: (Tanaman) -> Unit,
    private val onDetail: (Tanaman) -> Unit
) : RecyclerView.Adapter<TanamanAdapter.TanamanViewHolder>() {

    inner class TanamanViewHolder(val binding: ItemTanamanBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Tanaman) {
            binding.tvNama.text = item.plant_name
            binding.tvHarga.text = "Rp ${item.price}"
            binding.btnHapus.setOnClickListener { onDelete(item) }
            binding.btnDetail.setOnClickListener { onDetail(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TanamanViewHolder {
        val binding = ItemTanamanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TanamanViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TanamanViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}
