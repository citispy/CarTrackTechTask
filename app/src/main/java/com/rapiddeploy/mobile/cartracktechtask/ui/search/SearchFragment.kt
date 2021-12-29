package com.rapiddeploy.mobile.cartracktechtask.ui.search

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rapiddeploy.mobile.cartracktechtask.R
import com.rapiddeploy.mobile.cartracktechtask.api.model.Title
import com.rapiddeploy.mobile.cartracktechtask.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(), TitleResultsAdapter.OnItemClickListener {

    private val titlesViewModel: TitlesViewModel by viewModels()
    private val adapter = TitleResultsAdapter(this)
    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)

        initTitlesList()
        observeViewModel()

        return binding.root
    }

    private fun initTitlesList() {
        val titlesList: RecyclerView = binding.titlesList
        titlesList.layoutManager = LinearLayoutManager(requireContext())
        titlesList.adapter = adapter
        titlesViewModel.getTitles("good", Title.Type.MOVIE)
    }

    private fun observeViewModel() {
        titlesViewModel.titles.observe(this) {
            if (it != null) {
                adapter.updateTitles(it)
            }
        }
    }

    private fun showAlert(titles: String) {
        AlertDialog.Builder(requireActivity()).setTitle("Titles")
            .setMessage(titles)
            .setPositiveButton("Okay") { _, _ ->

            }
            .setCancelable(true)
            .show()
    }

    override fun onItemClicked(title: Title) {
        titlesViewModel.selectedTitle = title
        Log.d(this.tag, "Selected title set to: ${title.title}")
    }
}