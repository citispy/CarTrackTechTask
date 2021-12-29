package com.rapiddeploy.mobile.cartracktechtask.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.rapiddeploy.mobile.cartracktechtask.R
import com.rapiddeploy.mobile.cartracktechtask.databinding.FragmentTitleDetailsBinding
import com.rapiddeploy.mobile.cartracktechtask.ui.viewmodel.TitlesViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TitleDetailsFragment : Fragment() {

    lateinit var binding: FragmentTitleDetailsBinding
    private val titlesViewModel: TitlesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_title_details, container, false)
        setMovieDetails()

        return binding.root
    }

    private fun setMovieDetails() {
        val title = titlesViewModel.selectedTitle ?: return

        Picasso.get()
            .load(title.poster)
            .into(binding.poster)

        binding.title.text = title.title
        binding.type.text = getString(R.string.type, title.type)
        binding.year.text = title.year
        binding.imdb.text = getString(R.string.imdb_rating, title.imdbID)
    }
}