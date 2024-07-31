package com.example.checkmatemobile

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.checkmatemobile.databinding.ItemAnswerSheetBinding

class AnswerSheetAdapter(
    private var sheets: List<AnswerSheet>,
    private val onView: (AnswerSheet) -> Unit,
    private val onEdit: (AnswerSheet) -> Unit,
    private val onDelete: (AnswerSheet) -> Unit
): RecyclerView.Adapter<AnswerSheetAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemAnswerSheetBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAnswerSheetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sheet = sheets[position]
        holder.binding.sheetName.text = sheet.sheetName
        holder.binding.sheetName.setOnClickListener { onView(sheet) }
        holder.binding.editButton.setOnClickListener { onEdit(sheet) }
        holder.binding.deleteButton.setOnClickListener { onDelete(sheet) }
    }

    override fun getItemCount(): Int = sheets.size

    // Method to update the list of sheets and notify the RecyclerView
    fun updateSheets(newSheets: List<AnswerSheet>) {
        sheets = newSheets
        notifyDataSetChanged()
        // Log updated data
        Log.d("AnswerSheetAdapter", "Updated sheets: $sheets")
    }
}