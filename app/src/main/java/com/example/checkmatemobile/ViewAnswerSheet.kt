package com.example.checkmatemobile

import android.content.pm.PackageManager
import android.graphics.pdf.PdfDocument
import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.checkmatemobile.databinding.FragmentViewAnswerSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class ViewAnswerSheet(private val sheet: AnswerSheet) : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentViewAnswerSheetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentViewAnswerSheetBinding.inflate(inflater, container, false)

        binding.textViewSheetName.text = sheet.sheetName
        binding.textViewNumberOfItems.text = sheet.numberOfItems.toString()
        binding.hasMultipleChoice.isChecked = sheet.hasMultipleChoice
        binding.hasIdentification.isChecked = sheet.hasIdentification
        binding.hasWordProblem.isChecked = sheet.hasWordProblem

        binding.hasMultipleChoice.isEnabled = false
        binding.hasIdentification.isEnabled = false
        binding.hasWordProblem.isEnabled = false

        return binding.root
    }
}