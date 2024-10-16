package com.example.emtask.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.emtask.presentation.R
import com.example.emtask.presentation.databinding.FragmentSearchBinding
import com.example.emtask.presentation.noui.utils.generateMoreCountOfVacanciesByCount
import com.example.emtask.presentation.ui.components.modifiers.CustomItemDecoration
import com.example.emtask.presentation.ui.components.modifiers.CustomLinearLayoutManager
import com.example.emtask.presentation.ui.viewmodels.SearchFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel



class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private val vModel: SearchFragmentViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        binding.apply {
            button.text = vModel.mainButtonText
            vacanciesListview.setVacanciesAdapterSettings()
            offersListview.setOffersAdapterSettings()
            vacanciesScrollview.isVisible = false
            offersListview.isVisible = false
            vacanciesListview.itemAnimator = null
            offersListview.itemAnimator = null
        }
        vModel.collectDataFromLocalDB { vacanciesSize, offersSize ->
            if (offersSize > 0) {
                binding.offersListview.isVisible = true
            }
            if (vacanciesSize > 0) {
                binding.progressBar.isVisible = false
                binding.vacanciesScrollview.isVisible = true
                vModel.mainButtonText =
                    generateMoreCountOfVacanciesByCount(count = vacanciesSize) { str ->
                        "Еще $vacanciesSize $str"
                    }
                binding.vacanciesTextview.isVisible = true
                binding.button.text = vModel.mainButtonText
                binding.button.isVisible = !vModel.allVacanciesAreDisplayed
            }
        }
        binding.button.setOnClickListener {
            binding.button.isVisible = false
            vModel.displayVacancies(all = true)
        }
        binding.searchVacancyTv.setOnClickListener {
            modifyUItoSearch()
        }
        return binding.root
    }

    private fun modifyUItoDefault() {
        binding.apply {
            searchImage.setImageResource(R.drawable.search)
            searchVacancyTv.text = "Должность, ключевые слова"
            offersListview.isVisible = true
            vacanciesTextview.isVisible = true
            vModel.displayVacancies(all = false)
            button.isVisible = true
            searchSettings.isVisible = false
            countOfVacancies.isVisible = false
        }
    }

    private fun modifyUItoSearch() {
        binding.apply {
            searchImage.setImageResource(R.drawable.leftarrowt)
            searchImage.setOnClickListener {
                modifyUItoDefault()
            }
            searchVacancyTv.text = "Должность по подходящим вакансиям"
            offersListview.isVisible = false
            vacanciesTextview.isVisible = false
            button.isVisible = false
            searchSettings.isVisible = true
            val countOfVacanciesInt = vModel.vacanciesAdapter.vacancies.size
            countOfVacancies.text =
                generateMoreCountOfVacanciesByCount(count = countOfVacanciesInt) { "$countOfVacanciesInt $it" }
            countOfVacancies.isVisible = true
        }
        vModel.displayVacancies(all = true)
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
        adapter = vModel.vacanciesAdapter
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
        adapter = vModel.offersAdapter
        addItemDecoration(
            CustomItemDecoration(
                8,
                orientation = CustomItemDecoration.CustomItemDecorationOrientation.HORIZONTAL
            )
        )
    }

}