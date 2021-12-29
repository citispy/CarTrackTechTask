package com.rapiddeploy.mobile.cartracktechtask.ui.search

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.rapiddeploy.mobile.cartracktechtask.R
import com.rapiddeploy.mobile.cartracktechtask.api.model.Title
import com.rapiddeploy.mobile.cartracktechtask.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val titlesViewModel: TitlesViewModel by viewModels()
    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)

        binding.button.setOnClickListener {
            titlesViewModel.getTitles("good", Title.Type.MOVIE)
        }

        titlesViewModel.titles.observe(this, { titles ->
            var titleString: String? = null
            if (titles != null) {
                for (title in titles) {
                    titleString += title.title!! + "\n"
                }
            }
            if (titleString != null) {
                showAlert(titleString)
            } else {
                Toast.makeText(requireContext(), "Error", Toast.LENGTH_LONG).show()
            }
        })

        return binding.root
    }

    private fun showAlert(titles: String) {
        AlertDialog.Builder(requireActivity()).setTitle("Titles")
            .setMessage(titles)
            .setPositiveButton("Okay") { _, _ ->

            }
            .setCancelable(true)
            .show()
    }
}