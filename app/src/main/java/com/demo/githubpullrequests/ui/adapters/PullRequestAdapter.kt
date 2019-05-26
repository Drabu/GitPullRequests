package com.demo.githubpullrequests.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demo.githubpullrequests.data.model.api.PullRequests
import com.demo.githubpullrequests.databinding.ListAdapterBinding

class PullRequestAdapter(var pullRequests: List<PullRequests>?= ArrayList()) :
    RecyclerView.Adapter<PullRequestAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ListAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false))


    override fun getItemCount(): Int = pullRequests?.size!!

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    fun setItems(it: List<PullRequests>?) {
        pullRequests = it
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: ListAdapterBinding) : RecyclerView.ViewHolder(view.root)

}