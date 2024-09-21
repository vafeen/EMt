package ru.vafeen.emtask.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.vafeen.emtask.databinding.FragmentFavouritesBinding
import ru.vafeen.emtask.ui.components.modifiers.CustomItemDecoration
import ru.vafeen.emtask.ui.components.modifiers.CustomLinearLayoutManager
import ru.vafeen.emtask.ui.components.viewmodels.FavouritesFragmentViewModel
import ru.vafeen.emtask.ui.utils.generateMoreCountOfVacanciesByCount

class FavouritesFragment : Fragment() {
    private lateinit var binding: FragmentFavouritesBinding
    private val vModel: FavouritesFragmentViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        binding.favouritesListview.setFavouritesAdapterSettings()
        vModel.collectDataFromDB { size ->
            binding.numberOfFavourites.text =
                generateMoreCountOfVacanciesByCount(count = size) { vacanciesWord ->
                    "$size $vacanciesWord"
                }
        }
        return binding.root
    }

    private fun RecyclerView.setFavouritesAdapterSettings() {
        layoutManager = CustomLinearLayoutManager(
            context = requireContext(),
            orientation = LinearLayoutManager.VERTICAL,
            reverseLayout = false,
            canScrollVertically = true,
            canScrollHorizontally = false
        )
        adapter = vModel.favouritesAdapter
        addItemDecoration(
            CustomItemDecoration(
                space = 16,
                orientation = CustomItemDecoration.CustomItemDecorationOrientation.VERTICAL
            )
        )

    }
}