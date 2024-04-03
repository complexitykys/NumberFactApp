package com.example.ethernetprac.presentation.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.ethernetprac.presentation.model.NumberFactUi
import androidx.recyclerview.widget.ListAdapter
import com.example.ethernetprac.databinding.ItemListBinding


class NumberFactAdapter(private val listener: OnNumberClickListener) :
    ListAdapter<NumberFactUi, NumberFactViewHolder>(NumberDiffUtils()) {

    interface OnNumberClickListener {
        fun onNumberClick(number: NumberFactUi)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberFactViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListBinding.inflate(inflater, parent, false)

        return NumberFactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NumberFactViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            listener.onNumberClick(getItem(position))
        }

    }
}