package com.example.emtask.presentation.ui.components.diffUtil

import androidx.recyclerview.widget.DiffUtil
import com.example.emtask.data.database.entity.VacancyEntity

internal class VacanciesDiffCallback(
    private val oldList: List<VacancyEntity>,
    private val newList: List<VacancyEntity>,
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id


    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].toString() == newList[newItemPosition].toString()
}
