package ru.vafeen.emtask.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.vafeen.emtask.databinding.FragmentSearchBinding
import ru.vafeen.emtask.ui.components.adapters.VacanciesAdapter
import javax.inject.Inject

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding

    @Inject
    lateinit var vacanciesAdapter: VacanciesAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

}