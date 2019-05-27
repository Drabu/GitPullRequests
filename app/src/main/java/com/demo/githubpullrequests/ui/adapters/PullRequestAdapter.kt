package com.demo.githubpullrequests.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demo.githubpullrequests.R
import com.demo.githubpullrequests.data.model.api.PullRequests
import com.demo.githubpullrequests.utils.AppConstants

class PullRequestAdapter(var pullRequests: List<PullRequests?> = ArrayList()) :
    RecyclerView.Adapter<PullRequestAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return if (viewType == AppConstants.PROGRESS_VIEW) {
            CustomViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.progress_view, parent, false))
        } else {
            CustomViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_adapter, parent, false))
        }

    }


    override fun getItemCount(): Int {
        return pullRequests.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

    }

    fun setItems(it: List<PullRequests?>) {
        pullRequests = it
        notifyDataSetChanged()
    }


    override fun getItemViewType(position: Int): Int {
        return if (pullRequests[position] == null) {
            AppConstants.PROGRESS_VIEW
        } else {
            AppConstants.LIST_VIEW
        }
    }

    open class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view)

    inner class ListViewHolder(view: View) : CustomViewHolder(view)

    inner class ProgressViewHolder(view: View) : CustomViewHolder(view)

}