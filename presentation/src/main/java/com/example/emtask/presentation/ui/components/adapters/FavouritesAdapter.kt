package com.example.emtask.presentation.ui.components.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.emtask.data.database.entity.VacancyEntity
import com.example.emtask.presentation.R
import com.example.emtask.presentation.ui.VacationClickListener
import com.example.emtask.presentation.noui.utils.generateCountOfPeopleByCount
import com.example.emtask.presentation.noui.utils.generatePublishedDateByLocalDate
import com.example.emtask.presentation.noui.utils.parseDateFromString

internal class FavouritesAdapter(private val vacationClickListener: VacationClickListener) :
    RecyclerView.Adapter<FavouritesAdapter.ViewHolder>() {
    var favourites: List<VacancyEntity> = emptyList()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val lookingNumber: TextView = itemView.findViewById(R.id.looking_number_tv)
        private val title: TextView = itemView.findViewById(R.id.title)
        private val town: TextView = itemView.findViewById(R.id.town)
        private val company: TextView = itemView.findViewById(R.id.company)
        private val experience: TextView = itemView.findViewById(R.id.experience)
        private val publishedDate: TextView = itemView.findViewById(R.id.publishedDate)
        private val isFavourite: ImageButton = itemView.findViewById(R.id.is_favourite)

        fun bind(vacancy: VacancyEntity, position: Int) {
            val lookingNumberInt = vacancy.lookingNumber
            lookingNumber.text =
                if (lookingNumberInt != null) generateCountOfPeopleByCount(count = lookingNumberInt) {
                    "Сейчас просматривает ${vacancy.lookingNumber} $it"
                } else ""
            lookingNumber.isVisible = lookingNumber.text.isNotEmpty()
            title.text = vacancy.title
            town.text = vacancy.addressTown
            company.text = vacancy.company
            experience.text = vacancy.experiencePreviewText
            publishedDate.text =
                generatePublishedDateByLocalDate(localDate = parseDateFromString(date = vacancy.publishedDate))
            isFavourite.setImageResource(
                if (vacancy.isFavorite) R.drawable.colored_heart else R.drawable.heart
            )
            isFavourite.setOnClickListener {
                vacationClickListener.onClickUpdateVacancy(
                    vacancyEntity = favourites[position].copy(isFavorite = !vacancy.isFavorite),
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.vacancy, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val vacancy = favourites[position]
        holder.bind(vacancy = vacancy, position = position)
    }

    override fun getItemCount(): Int = favourites.size


}