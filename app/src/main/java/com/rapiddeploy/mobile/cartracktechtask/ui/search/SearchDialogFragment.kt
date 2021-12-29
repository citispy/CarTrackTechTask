package com.rapiddeploy.mobile.cartracktechtask.ui.search

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.rapiddeploy.mobile.cartracktechtask.R
import com.rapiddeploy.mobile.cartracktechtask.api.model.Title
import com.rapiddeploy.mobile.cartracktechtask.databinding.FragmentSearchDialogBinding
import com.rapiddeploy.mobile.cartracktechtask.ui.viewmodel.TitlesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchDialogFragment : DialogFragment() {

    private val titlesViewModel: TitlesViewModel by viewModels()
    private lateinit var binding: FragmentSearchDialogBinding
    private var checkedItemType: Title.Type = Title.Type.MOVIE

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext());
        builder.setTitle(getString(R.string.search_for_title))
        builder.setPositiveButton(getString(R.string.search)) { _, _ ->
                val searchTitle = binding.searchTitle.text.toString()
                if (searchTitle.isNotEmpty()) {
                    titlesViewModel.getTitles(searchTitle, checkedItemType)
                }
            }
        builder.setNegativeButton(getString(R.string.cancel)) { _, _ ->
            //Dismiss dialog
        }
        val inflater = LayoutInflater.from(requireContext())
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search_dialog, null, false)
        builder.setView(binding.root)
        setSingleChoiceItems(builder)
        return builder.create()
    }

    private fun setSingleChoiceItems(builder: AlertDialog.Builder) {
        val alertItems = arrayOf(getString(R.string.movies), getString(R.string.series))
        builder.setSingleChoiceItems(alertItems, 0) { _: DialogInterface?, checkedItem: Int ->
            setCheckedItem(alertItems, checkedItem)
        }
    }

    private fun setCheckedItem(alertItems: Array<String>, checkedItem: Int) {
        val checkedItemString = alertItems[checkedItem]
        if(checkedItemString == getString(R.string.movies)) {
            checkedItemType = Title.Type.MOVIE
        } else if (checkedItemString == getString(R.string.series)) {
            checkedItemType = Title.Type.SERIES
        }
    }
}