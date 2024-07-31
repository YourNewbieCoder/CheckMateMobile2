package com.example.checkmatemobile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.checkmatemobile.databinding.FragmentHomeBinding

class Home : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var answerSheetViewModel: AnswerSheetViewModel
    private lateinit var adapter: AnswerSheetAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        answerSheetViewModel = ViewModelProvider(this)[AnswerSheetViewModel::class.java]

        binding.createSheetBtn.setOnClickListener{
            CreateAnswerSheet().show(childFragmentManager, "createSheet")
        }

        adapter = AnswerSheetAdapter(
            listOf(),
            this::viewSheetDetails,
            this::editSheet,
            this::deleteSheet
        )

        binding.recycleViewSheets.layoutManager = LinearLayoutManager(requireContext())
        binding.recycleViewSheets.adapter = adapter

        // Observe the LiveData from ViewModel
        answerSheetViewModel.sheetDataList.observe(viewLifecycleOwner) { sheets ->
            // Log observed data
            Log.d("HomeFragment", "Observed sheets: $sheets")
            // Update the adapter's data
            adapter.updateSheets(sheets)
        }
    }

    private fun viewSheetDetails(sheet: AnswerSheet){
        ViewAnswerSheet(sheet).show(childFragmentManager, "viewSheet")
    }

    private fun editSheet(sheet: AnswerSheet){
        CreateAnswerSheet(sheet).show(childFragmentManager, "editSheet")
    }

    private fun deleteSheet(sheet: AnswerSheet){
        answerSheetViewModel.deleteSheet(sheet.id)
    }
}

