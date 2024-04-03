package com.example.ethernetprac.presentation.main.adapter

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.ethernetprac.databinding.ItemListBinding
import com.example.ethernetprac.presentation.model.NumberFactUi

class NumberFactViewHolder(private val binding: ItemListBinding) : ViewHolder(binding.root) {
    fun bind(numberFact: NumberFactUi) {
        binding.titleTextView.text = numberFact.number
        binding.subTitleTextView.text = numberFact.fact
    }
}