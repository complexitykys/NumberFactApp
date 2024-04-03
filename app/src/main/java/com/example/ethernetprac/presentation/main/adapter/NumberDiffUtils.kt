package com.example.ethernetprac.presentation.main.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.ethernetprac.presentation.model.NumberFactUi

class NumberDiffUtils : DiffUtil.ItemCallback<NumberFactUi>() {
    override fun areItemsTheSame(oldItem: NumberFactUi, newItem: NumberFactUi): Boolean {
        return oldItem.number == newItem.number
    }

    override fun areContentsTheSame(oldItem: NumberFactUi, newItem: NumberFactUi): Boolean {
        return oldItem == newItem
    }
}