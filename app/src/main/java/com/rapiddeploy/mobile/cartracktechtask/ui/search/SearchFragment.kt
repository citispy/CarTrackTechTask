package com.rapiddeploy.mobile.cartracktechtask.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rapiddeploy.mobile.cartracktechtask.R
import com.rapiddeploy.mobile.cartracktechtask.api.model.Title
import com.rapiddeploy.mobile.cartracktechtask.databinding.FragmentSearchBinding
import com.rapiddeploy.mobile.cartracktechtask.ui.viewmodel.TitlesViewModel
import com.rapiddeploy.mobile.cartracktechtask.ui.viewmodel.TitlesViewModel.*
import com.rapiddeploy.mobile.cartracktechtask.ui.viewmodel.TitlesViewModel.UiState.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(), TitleResultsAdapter.OnItemClickListener {

    private val titlesViewModel: TitlesViewModel by activityViewModels()
    private val adapter = TitleResultsAdapter(this)
    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        binding.searchFab.setOnClickListener {
            SearchDialogFragment().show(childFragmentManager, "tag")
        }

        initTitlesList()
        observeViewModel()

        return binding.root
    }

    private fun initTitlesList() {
        val titlesList: RecyclerView = binding.titlesList
        titlesList.layoutManager = GridLayoutManager(requireContext(), 2)
        titlesList.adapter = adapter
    }

    private fun observeViewModel() {
        titlesViewModel.uiState.observe(this) {
            updateVisibility(it)
        }

        titlesViewModel.titles.observe(this) {
            updateTitles(it)
        }
    }

    private fun updateVisibility(it: UiState) {
        setProgressVisibility(it)
        setTitlesListVisibility(it)
        setNoTitlesVisibility(it)
    }

    private fun updateTitles(it: List<Title>?) {
        val moviesFound = it != null
        if (moviesFound) {
            adapter.updateTitles(it!!)
        }
    }

    private fun setProgressVisibility(state: UiState) {
        binding.progress.visibility =
            if (state == LOADING) View.VISIBLE else View.GONE
    }

    private fun setTitlesListVisibility(state: UiState) {
        binding.titlesList.visibility =
            if (state == NOT_LOADING_WITH_TITLES) View.VISIBLE else View.GONE
    }

    private fun setNoTitlesVisibility(state: UiState) {
        binding.noTitlesFound.visibility =
            if (state == NOT_LOADING_WITHOUT_TITLES) View.VISIBLE else View.GONE
    }

    override fun onItemClicked(title: Title) {
        titlesViewModel.selectedTitle = title
        findNavController().navigate(R.id.action_searchFragment_to_titleDetailsFragment)
    }
}