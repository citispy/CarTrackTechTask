package com.rapiddeploy.mobile.cartracktechtask.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.rapiddeploy.mobile.cartracktechtask.R
import com.rapiddeploy.mobile.cartracktechtask.api.model.Title
import com.rapiddeploy.mobile.cartracktechtask.databinding.TitleRowBinding
import com.squareup.picasso.Picasso

class TitleResultsAdapter(private val itemCLickListener: OnItemClickListener):
    RecyclerView.Adapter<TitleResultsAdapter.ViewHolder>() {

    private var titles: List<Title> = ArrayList()

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
        val title = titles[position]

        if (titles.isNotEmpty()) {
            //Set poster
            Picasso.get()
                .load(title.poster)
                .fit()
                .into(holder.poster)

            //Set title
            holder.title.text = title.title

            //Handle item click
            holder.rowItem.setOnClickListener {
                itemCLickListener.onItemClicked(title)
            }
        }
    }

    fun updateTitles(newTitles: List<Title>) {
        titles = newTitles
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return titles.size
    }

    interface OnItemClickListener {
        fun onItemClicked(title: Title)
    }
}