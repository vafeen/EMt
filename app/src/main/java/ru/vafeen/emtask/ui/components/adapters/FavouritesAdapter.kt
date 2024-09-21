package ru.vafeen.emtask.ui.components.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.vafeen.emtask.R
import ru.vafeen.emtask.ui.utils.generateCountOfPeopleByCount
import ru.vafeen.emtask.ui.utils.generatePublishedDateByLocalDate
import ru.vafeen.emtask.ui.utils.parseDateFromString
import ru.vafeen.local_storage.entity.VacancyEntity
import javax.inject.Inject

class FavouritesAdapter @Inject constructor() :
    RecyclerView.Adapter<FavouritesAdapter.ViewHolder>() {
    var favourites: List<VacancyEntity> = emptyList()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val lookingNumber: TextView = itemView.findViewById(R.id.looking_number_tv)
        private val title: TextView = itemView.findViewById(R.id.title)
        private val town: TextView = itemView.findViewById(R.id.town)
        private val company: TextView = itemView.findViewById(R.id.company)
        private val experience: TextView = itemView.findViewById(R.id.experience)
        private val publishedDate: TextView = itemView.findViewById(R.id.publishedDate)


        fun bind(vacancyEntity: VacancyEntity) {
            val lookingNumberInt = vacancyEntity.lookingNumber
            lookingNumber.text =
                if (lookingNumberInt != null) generateCountOfPeopleByCount(count = lookingNumberInt) {
                    "Сейчас просматривает ${vacancyEntity.lookingNumber} $it"
                } else ""
            title.text = vacancyEntity.title
            town.text = vacancyEntity.addressTown
            company.text = vacancyEntity.company
            experience.text = vacancyEntity.experiencePreviewText
            publishedDate.text =
                generatePublishedDateByLocalDate(localDate = parseDateFromString(date = vacancyEntity.publishedDate))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.vacancy, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val vacancyEntity = favourites[position]
        holder.bind(vacancyEntity = vacancyEntity)
    }

    override fun getItemCount(): Int = favourites.size


}