package ru.vafeen.emtask.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.vafeen.emtask.databinding.FragmentProfileBinding
import ru.vafeen.emtask.databinding.FragmentResponsesBinding

class ResponsesFragment : Fragment() {

    private lateinit var binding: FragmentResponsesBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResponsesBinding.inflate(inflater, container, false)
        return binding.root
    }

}