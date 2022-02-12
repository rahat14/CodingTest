package com.rahat.gtaf.codingtest.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.rahat.gtaf.codingtest.R
import com.rahat.gtaf.codingtest.databinding.ItemCommitBinding
import com.rahat.gtaf.codingtest.models.CommitModel
import com.rahat.gtaf.codingtest.utils.TimeConverterClass

class CommitAdapter(private val interaction: Interaction? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<CommitModel>() {

        override fun areItemsTheSame(oldItem: CommitModel, newItem: CommitModel): Boolean {
            return oldItem.sha == newItem.sha
        }

        override fun areContentsTheSame(oldItem: CommitModel, newItem: CommitModel): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, callback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return CommitViewholder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_commit,
                parent,
                false
            ),
            interaction
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CommitViewholder -> {
                holder.bind(differ.currentList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<CommitModel>) {
        differ.submitList(list)
    }

    class CommitViewholder
    constructor(
        itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: CommitModel) {
            val binding = ItemCommitBinding.bind(itemView)
            itemView.setOnClickListener {
                interaction?.onItemSelected(adapterPosition, item)
            }
            binding.commitName.text = item.commit?.message.toString()
            binding.commitAuthor.text = item.commit?.author?.name
            binding.commitDate.text =
                TimeConverterClass.convertDateToText("${item.commit?.committer?.date}")
            Glide.with(binding.root.context)
                .load(item.author?.avatar_url.toString())
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(binding.autherImage)


        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: CommitModel)
    }
}
