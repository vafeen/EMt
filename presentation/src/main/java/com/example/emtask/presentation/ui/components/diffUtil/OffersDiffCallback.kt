package com.example.emtask.presentation.ui.components.diffUtil

import androidx.recyclerview.widget.DiffUtil
import com.example.emtask.data.database.entity.OfferEntity

internal class OffersDiffCallback(
    private val oldList: List<OfferEntity>,
    private val newList: List<OfferEntity>,
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].uid == newList[newItemPosition].uid


    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]
}
