package com.example.emtask.presentation.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.forEach
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


internal class SearchFragment : Fragment() {
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
            vacanciesListview.itemAnimator = null
            offersListview.itemAnimator = null
        }
        vModel.collectDataFromLocalDB { vacanciesSize, offersSize ->
            if (vModel.isSearchInProcess) {
                modifyUItoSearch(vacanciesSize = vacanciesSize, offersSize = offersSize)
            } else {
                modifyUItoDefault(vacanciesSize = vacanciesSize, offersSize = offersSize)
            }
        }

        binding.button.setOnClickListener {
            vModel.displayVacancies(all = true)
            binding.button.isVisible = !vModel.allVacanciesAreDisplayed
        }

        binding.searchVacancyTv.setOnClickListener {
            modifyUItoSearch(
                offersSize = vModel.offersAdapter.offers.size,
                vacanciesSize = vModel.vacanciesAdapter.vacancies.size
            )
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vModel.displayVacancies(false)
        binding.vacanciesListview.scrollToPosition(0)
    }

    private fun modifyUItoDefault(offersSize: Int, vacanciesSize: Int) {
        vModel.isSearchInProcess = false
        binding.apply {
            searchImage.setImageResource(R.drawable.search)
            searchVacancyTv.text = "Должность, ключевые слова"
            vacanciesTextview.isVisible = true
            vModel.displayVacancies(all = vModel.allVacanciesAreDisplayed)
            searchSettings.isVisible = false
            countOfVacancies.isVisible = false
        }
        if (offersSize > 0) {
            binding.offersListview.isVisible = true
        }
        if (vacanciesSize > 0) {
            vModel.mainButtonText =
                generateMoreCountOfVacanciesByCount(count = vModel.vacanciesRealSize) { str ->
                    "Еще $vacanciesSize $str"
                }
            binding.button.text = vModel.mainButtonText
            binding.button.isVisible = !vModel.allVacanciesAreDisplayed
            binding.vacanciesScrollview.isVisible = true
            binding.progressBar.isVisible = false
        }
    }

    private fun modifyUItoSearch(vacanciesSize: Int, offersSize: Int) {
        vModel.isSearchInProcess = true
        binding.progressBar.isVisible = false
        binding.apply {
            vacanciesScrollview.isVisible = true
            searchImage.setImageResource(R.drawable.leftarrowt)
            searchImage.setOnClickListener {
                vModel.allVacanciesAreDisplayed = false
                modifyUItoDefault(offersSize = offersSize, vacanciesSize = vacanciesSize)
            }
            searchVacancyTv.text = "Должность по подходящим вакансиям"
            offersListview.isVisible = false
            vacanciesTextview.isVisible = false
            button.isVisible = false
            searchSettings.isVisible = true
            countOfVacancies.text =
                generateMoreCountOfVacanciesByCount(count = vModel.vacanciesRealSize) { "${vModel.vacanciesRealSize} $it" }
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