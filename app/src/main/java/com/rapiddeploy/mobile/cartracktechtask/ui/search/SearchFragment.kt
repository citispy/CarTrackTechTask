package com.rapiddeploy.mobile.cartracktechtask.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rapiddeploy.mobile.cartracktechtask.R
import com.rapiddeploy.mobile.cartracktechtask.api.model.Title
import com.rapiddeploy.mobile.cartracktechtask.databinding.FragmentSearchBinding
import com.rapiddeploy.mobile.cartracktechtask.ui.viewmodel.TitlesViewModel
import com.rapiddeploy.mobile.cartracktechtask.ui.viewmodel.TitlesViewModel.*
import com.rapiddeploy.mobile.cartracktechtask.ui.viewmodel.TitlesViewModel.UiState.*
import com.rapiddeploy.mobile.cartracktechtask.utils.NetworkStateViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment(), TitleResultsAdapter.OnItemClickListener {

    private val titlesViewModel: TitlesViewModel by activityViewModels()
    private val networkStateViewModel: NetworkStateViewModel by viewModels()
    private val adapter = TitleResultsAdapter(this)
    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        binding.searchFab.setOnClickListener {
            openSearchDialog()
        }

        initTitlesList()
        observeViewModels()

        return binding.root
    }

    private fun openSearchDialog() {
        SearchDialogFragment().show(childFragmentManager, "tag")
    }

    private fun initTitlesList() {
        val titlesList: RecyclerView = binding.titlesList
        titlesList.layoutManager = GridLayoutManager(requireContext(), 2)

        val dividerItemDecoration = getDividerItemDecoration(titlesList, RecyclerView.VERTICAL)
        val dividerItemDecoration2 = getDividerItemDecoration(titlesList, RecyclerView.HORIZONTAL)

        titlesList.addItemDecoration(dividerItemDecoration)
        titlesList.addItemDecoration(dividerItemDecoration2)

        titlesList.adapter = adapter
    }

    private fun getDividerItemDecoration(titlesList: RecyclerView, orientation: Int): DividerItemDecoration {
        val dividerItemDecoration = DividerItemDecoration(titlesList.context, orientation)
        ResourcesCompat.getDrawable(resources, R.drawable.divider, null)?.let { drawable -> dividerItemDecoration.setDrawable(drawable) }
        return dividerItemDecoration
    }

    private fun observeViewModels() {
        titlesViewModel.uiState.observe(this) {
            updateVisibility(it)
        }

        titlesViewModel.getTitles("Good", Title.Type.MOVIE).observe(this) {
            lifecycleScope.launch {
                adapter.submitData(it)
            }
        }

        titlesViewModel.errorMessage.observe(this) {
            if (it != null) {
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
            }
        }

        networkStateViewModel.isOnline.observe(this) {
            if (it) {
                binding.searchFab.show()
            } else {
                binding.searchFab.hide()
            }
        }
    }

    private fun updateVisibility(state: UiState) {
        binding.progress.visibility =
            if (state == LOADING) View.VISIBLE else View.GONE

        binding.titlesList.visibility =
            if (state == NOT_LOADING_WITH_TITLES) View.VISIBLE else View.GONE

        binding.noTitlesFound.visibility =
            if (state == NOT_LOADING_WITHOUT_TITLES) View.VISIBLE else View.GONE
    }

    private fun updateTitles(it: List<Title>?) {
        val moviesFound = it != null
        if (moviesFound) {
            //adapter.updateTitles(it!!)
        } else if (titlesViewModel.firstLoad) {
            openSearchDialog()
        }
        titlesViewModel.firstLoad = false
    }

    override fun onItemClicked(title: Title) {
        titlesViewModel.selectedTitle = title
        findNavController().navigate(R.id.action_searchFragment_to_titleDetailsFragment)
    }
}