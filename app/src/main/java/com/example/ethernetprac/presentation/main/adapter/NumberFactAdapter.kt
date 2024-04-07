package com.example.ethernetprac.presentation.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.ethernetprac.databinding.ItemListBinding
import com.example.ethernetprac.presentation.model.NumberFactUi


class NumberFactAdapter(private val listener: OnNumberClickListener) :
    PagingDataAdapter<NumberFactUi, NumberFactViewHolder>(NumberDiffUtils()) {

    interface OnNumberClickListener {
        fun onNumberClick(number: NumberFactUi)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberFactViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListBinding.inflate(inflater, parent, false)

        return NumberFactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NumberFactViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
        holder.itemView.setOnClickListener {
            getItem(position)?.let { listener.onNumberClick(it) }
        }

    }
}