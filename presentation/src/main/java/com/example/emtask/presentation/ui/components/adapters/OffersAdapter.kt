package com.example.emtask.presentation.ui.components.adapters

import android.content.Context
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
            when (offer.id) {
                "near_vacancies" -> {
                    id.background.setTint(ContextCompat.getColor(context, R.color.dark_blue))
                    null
                }

                "level_up_resume" -> {
                    id.background.setTint(ContextCompat.getColor(context, R.color.dark_green))
                    R.drawable.star
                }

                "temporary_job" -> {
                    id.background.setTint(ContextCompat.getColor(context, R.color.dark_green))
                    R.drawable.list_with_done
                }

                else -> null
            }?.let {
                id.setImageResource(it)
            }

            title.text = offer.title
            button.text = offer.buttonText ?: ""
            offer.link
            if (button.text.isNotEmpty()) {
                button.setOnClickListener {

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