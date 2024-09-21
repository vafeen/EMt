package ru.vafeen.emtask.ui.components.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ru.vafeen.emtask.R
import ru.vafeen.local_storage.entity.OfferEntity

class OffersAdapter(val context: Context) : RecyclerView.Adapter<OffersAdapter.ViewHolder>() {
    var offers: List<OfferEntity> = emptyList()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val id: ImageView = itemView.findViewById(R.id.icon)
        private val title: TextView = itemView.findViewById(R.id.title)
        private val button: TextView = itemView.findViewById(R.id.button)


        fun bind(offer: OfferEntity) {
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
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OffersAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.offer, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: OffersAdapter.ViewHolder, position: Int) {
        holder.bind(offer = offers[position])
    }

    override fun getItemCount(): Int = offers.size

}