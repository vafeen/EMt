package ru.vafeen.emtask.ui.components.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.vafeen.emtask.R
import ru.vafeen.emtask.ui.utils.generateCountOfPeopleByCount
import ru.vafeen.emtask.ui.utils.generatePublishedDateByLocalDate
import ru.vafeen.emtask.ui.utils.parseDateFromString
import ru.vafeen.network.response.Vacancy
import javax.inject.Inject

class VacanciesAdapter @Inject constructor() : RecyclerView.Adapter<VacanciesAdapter.ViewHolder>() {
    var vacancies: List<Vacancy> = emptyList()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val lookingNumber: TextView = itemView.findViewById(R.id.looking_number_tv)
        private val title: TextView = itemView.findViewById(R.id.title)
        private val town: TextView = itemView.findViewById(R.id.town)
        private val company: TextView = itemView.findViewById(R.id.company)
        private val experience: TextView = itemView.findViewById(R.id.experience)
        private val publishedDate: TextView = itemView.findViewById(R.id.publishedDate)
        private val isFavourite: ImageButton = itemView.findViewById(R.id.is_favourite)

        fun bind(vacancy: Vacancy) {
            val lookingNumberInt = vacancy.lookingNumber
            lookingNumber.text =
                if (lookingNumberInt != null) generateCountOfPeopleByCount(count = lookingNumberInt) {
                    "Сейчас просматривает ${vacancy.lookingNumber} $it"
                } else ""
            title.text = vacancy.title
            town.text = vacancy.address.town
            company.text = vacancy.company
            experience.text = vacancy.experience.previewText
            publishedDate.text =
                generatePublishedDateByLocalDate(localDate = parseDateFromString(date = vacancy.publishedDate))
            isFavourite.setImageResource(if (vacancy.isFavorite) R.drawable.colored_heart else R.drawable.heart)
            isFavourite.setOnClickListener {
                val newVacancy = vacancy.copy(isFavorite = !vacancy.isFavorite)
                // databaseRepository.insert(newVacancy)
                this@VacanciesAdapter.notifyItemChanged(vacancies.indexOf(vacancy))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.vacancy, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val vacancy = vacancies[position]
        holder.bind(vacancy = vacancy)
    }

    override fun getItemCount(): Int = vacancies.size


}