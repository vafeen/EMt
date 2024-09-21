package ru.vafeen.emtask.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import ru.vafeen.emtask.R
import ru.vafeen.emtask.databinding.FragmentSearchBinding
import ru.vafeen.emtask.ui.components.modifiers.CustomItemDecoration
import ru.vafeen.emtask.ui.components.modifiers.CustomLinearLayoutManager
import ru.vafeen.emtask.ui.components.viewmodels.SearchFragmentViewModel
import ru.vafeen.emtask.ui.utils.generateMoreCountOfVacanciesByCount


@AndroidEntryPoint
class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private val viewModel: SearchFragmentViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        binding.button.text = viewModel.mainButtonText
        binding.vacanciesListview.setVacanciesAdapterSettings()
        binding.offersListview.setOffersAdapterSettings()
        binding.vacanciesScrollview.isVisible = false
        binding.offersListview.isVisible = false
        viewModel.collectDataFromGDrive { vacanciesSize, offersSize ->
            if (vacanciesSize > 0) {
                binding.vacanciesScrollview.isVisible = true
                viewModel.mainButtonText =
                    generateMoreCountOfVacanciesByCount(count = vacanciesSize) { str ->
                        "Еще $vacanciesSize $str"
                    }
                binding.button.text = viewModel.mainButtonText
            }
            if (offersSize > 0) {
                binding.offersListview.isVisible = true
            }
        }
        binding.button.setOnClickListener {
            viewModel.displayAllVacancies()
            binding.button.isVisible = false
        }
        modifyUItoDefault()
        binding.searchVacancyTv.setOnClickListener {
            modifyUItoSearch()
        }
        return binding.root
    }

    private fun modifyUItoDefault() {
        binding.searchImage.setImageResource(R.drawable.search)
        binding.searchVacancyTv.text = "Должность, ключевые слова"
        binding.offersListview.isVisible = true
        binding.vacanciesTextview.isVisible = true
        viewModel.displayPreviewVacancies()
        binding.button.isVisible = true
        binding.searchSettings.isVisible = false
        binding.countOfVacancies.isVisible = false
    }

    private fun modifyUItoSearch() {
        binding.searchImage.setImageResource(R.drawable.leftarrowt)
        binding.searchImage.setOnClickListener {
            modifyUItoDefault()
        }
        binding.searchVacancyTv.text = "Должность по подходящим вакансиям"
        binding.offersListview.isVisible = false
        binding.vacanciesTextview.isVisible = false
        viewModel.displayAllVacancies()
        binding.button.isVisible = false
        binding.searchSettings.isVisible = true
        val countOfVacancies = viewModel.vacanciesAdapter.vacancies.size
        binding.countOfVacancies.text =
            generateMoreCountOfVacanciesByCount(count = countOfVacancies) { "$countOfVacancies $it" }
        binding.countOfVacancies.isVisible = true
    }


    private fun RecyclerView.setVacanciesAdapterSettings() {
        layoutManager =
            CustomLinearLayoutManager(
                context = requireContext(),
                orientation = LinearLayoutManager.VERTICAL,
                reverseLayout = false,
                canScrollVertically = true,
                canScrollHorizontally = false
            )
        adapter = viewModel.vacanciesAdapter
        addItemDecoration(
            CustomItemDecoration(
                space = 16,
                orientation = CustomItemDecoration.CustomItemDecorationOrientation.VERTICAL
            )
        )

    }

    private fun RecyclerView.setOffersAdapterSettings() {
        layoutManager =
            CustomLinearLayoutManager(
                context = requireContext(),
                orientation = LinearLayoutManager.HORIZONTAL,
                reverseLayout = false,
                canScrollVertically = false,
                canScrollHorizontally = true
            )
        adapter = viewModel.offersAdapter
        addItemDecoration(
            CustomItemDecoration(
                8,
                orientation = CustomItemDecoration.CustomItemDecorationOrientation.HORIZONTAL
            )
        )
    }

}