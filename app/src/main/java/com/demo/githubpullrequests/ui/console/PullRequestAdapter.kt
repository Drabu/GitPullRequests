package com.demo.githubpullrequests.ui.console

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demo.githubpullrequests.databinding.ListAdapterBinding

class PullRequestAdapter : RecyclerView.Adapter<PullRequestAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ListAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false))


    override fun getItemCount(): Int = 10

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }


    class ViewHolder(view: ListAdapterBinding) : RecyclerView.ViewHolder(view.root)

}