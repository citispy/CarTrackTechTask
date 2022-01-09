package com.rapiddeploy.mobile.cartracktechtask.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rapiddeploy.mobile.cartracktechtask.R
import com.rapiddeploy.mobile.cartracktechtask.api.model.Title
import com.rapiddeploy.mobile.cartracktechtask.databinding.TitleRowBinding
import com.squareup.picasso.Picasso

class TitleResultsAdapter(private val itemCLickListener: OnItemClickListener) :
    PagingDataAdapter<Title, TitleResultsAdapter.ViewHolder>(REPO_COMPARATOR) {

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<Title>() {
            override fun areItemsTheSame(oldItem: Title, newItem: Title): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Title, newItem: Title): Boolean {
                return oldItem.equals(oldItem)
            }
        }
    }

    class ViewHolder(binding: TitleRowBinding) : RecyclerView.ViewHolder(binding.root) {
        val rowItem = binding.rowItem
        val poster = binding.poster
        val title = binding.title
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: TitleRowBinding =
            DataBindingUtil.inflate(inflater, R.layout.title_row, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val title = getItem(position) ?: return
        //Set poster
        Picasso.get()
            .load(title.poster)
            .fit()
            .placeholder(R.drawable.ic_baseline_broken_image)
            .into(holder.poster)

        //Set title
        holder.title.text = title.title

        //Handle item click
        holder.rowItem.setOnClickListener {
            itemCLickListener.onItemClicked(title)
        }
    }

    interface OnItemClickListener {
        fun onItemClicked(title: Title)
    }
}