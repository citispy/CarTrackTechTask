package com.rapiddeploy.mobile.cartracktechtask.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rapiddeploy.mobile.cartracktechtask.R
import com.rapiddeploy.mobile.cartracktechtask.api.model.Title
import com.rapiddeploy.mobile.cartracktechtask.databinding.FragmentSearchBinding
import com.rapiddeploy.mobile.cartracktechtask.ui.viewmodel.TitlesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(), TitleResultsAdapter.OnItemClickListener {

    private lateinit var titlesViewModel: TitlesViewModel
    private val adapter = TitleResultsAdapter(this)
    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        titlesViewModel = ViewModelProviders.of(requireActivity()).get(TitlesViewModel::class.java)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        binding.seachFab.setOnClickListener {
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
        titlesViewModel.titles.observe(this) {
            val moviesFound = it != null
            if (moviesFound) {
                adapter.updateTitles(it!!)
            }

            binding.titlesList.visibility = if (moviesFound) View.VISIBLE else View.GONE
            binding.noMoviesFound.visibility = if (moviesFound) View.GONE else View.VISIBLE
        }
    }

    override fun onItemClicked(title: Title) {
        titlesViewModel.selectedTitle = title
        findNavController().navigate(R.id.action_searchFragment_to_titleDetailsFragment)
    }
}