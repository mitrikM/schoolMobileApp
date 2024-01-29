package com.example.schoolapp.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.schoolapp.R
import com.example.schoolapp.adapters.HistoryAdapter

class HistoryFragment : Fragment() {
    private lateinit var historyRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_history, container, false)
        historyRecyclerView = view.findViewById(R.id.historyRecyclerView)
        historyRecyclerView.layoutManager = LinearLayoutManager(context)
        displayHistory()
        return view
    }

    private fun displayHistory() {
        val sharedPref = activity?.getSharedPreferences("RecipeHistory", Context.MODE_PRIVATE) ?: return
        val historySet = sharedPref.getStringSet("HISTORY", setOf())?.toList() ?: listOf()
        val historyAdapter = HistoryAdapter(historySet)
        historyRecyclerView.adapter = historyAdapter
    }
}
