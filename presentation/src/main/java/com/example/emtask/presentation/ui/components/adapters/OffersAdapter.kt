package com.example.emtask.presentation.ui.components.adapters

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.emtask.data.database.entity.OfferEntity
import com.example.emtask.data.network.utils.openLink
import com.example.emtask.presentation.R
import com.example.emtask.presentation.ui.components.diffUtil.OffersDiffCallback


internal class OffersAdapter :
    RecyclerView.Adapter<OffersAdapter.ViewHolder>() {
    var offers: List<OfferEntity> = emptyList()
        set(value) {
            val result = DiffUtil.calculateDiff(OffersDiffCallback(field, value))
            field = value
            result.dispatchUpdatesTo(this)
        }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val id: ImageView = itemView.findViewById(R.id.icon)
        private val title: TextView = itemView.findViewById(R.id.title)
        private val button: TextView = itemView.findViewById(R.id.button)


        fun bind(context: Context, offer: OfferEntity) {
            itemView.setOnClickListener {
                openLink(context, offer.link)
            }
            title.text = offer.title
            button.text = offer.buttonText ?: ""
            when (offer.id) {
                "near_vacancies" -> {
                    Pair(null, R.color.dark_blue)
                }

                "level_up_resume" -> {
                    Pair(R.drawable.star, R.color.dark_green)
                }

                "temporary_job" -> {
                    Pair(R.drawable.list_with_done, R.color.dark_green)
                }

                else -> null
            }?.let { pair ->
                pair.first?.let { id.setImageResource(it) }
                id.background.setTint(ContextCompat.getColor(context, pair.second))
                if (button.text.isNotEmpty()) {
                    button.setTextColor(ContextCompat.getColor(context, pair.second))
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.offer, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(context = holder.itemView.context, offer = offers[position])
    }

    override fun getItemCount(): Int = offers.size

}