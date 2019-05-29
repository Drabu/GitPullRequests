package com.demo.githubpullrequests.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demo.githubpullrequests.R
import com.demo.githubpullrequests.data.model.api.PullRequests
import com.demo.githubpullrequests.databinding.ListAdapterBinding
import com.demo.githubpullrequests.utils.AppConstants
import com.squareup.picasso.Picasso

class PullRequestAdapter(var pullRequests: List<PullRequests?> = ArrayList()) :
    RecyclerView.Adapter<PullRequestAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return if (viewType == AppConstants.PROGRESS_VIEW) {
            CustomViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.progress_view, parent, false))
        } else {
            ListViewHolder(ListAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }

    override fun getItemCount(): Int {
        return pullRequests.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        if (holder is ListViewHolder) {
            bindView(holder, position)
        }
    }

    private fun bindView(holder: ListViewHolder, position: Int) {

        holder.binding.repTitleTV.text = pullRequests[position]?.title
        holder.binding.repSubTitleTV.text = pullRequests[position]?.body

        try {
            Picasso.get().load(pullRequests[position]?.user?.avatarUrl)
                .placeholder(R.drawable.placeholder).into(holder.binding.authorAvatarCIV)
        } catch (e: IndexOutOfBoundsException) {
            e.printStackTrace()
        }
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

    inner class ListViewHolder(var binding: ListAdapterBinding) : CustomViewHolder(binding.root)

    inner class ProgressViewHolder(view: View) : CustomViewHolder(view)

}