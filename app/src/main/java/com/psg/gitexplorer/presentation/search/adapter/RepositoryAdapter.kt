package com.psg.gitexplorer.presentation.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.psg.gitexplorer.databinding.ItemRepoBinding
import com.psg.gitexplorer.data.model.Repository

class RepositoryAdapter(
    private val onClick: (Repository) -> Unit
) : RecyclerView.Adapter<RepositoryAdapter.VH>() {

    private val list = mutableListOf<Repository>()

    fun submitList(items: List<Repository>) {
        list.clear()
        list.addAll(items)
        notifyDataSetChanged()
    }

    inner class VH(private val b: ItemRepoBinding) : RecyclerView.ViewHolder(b.root) {
        fun bind(repo: Repository) {
            b.txtName.text = repo.name
            b.txtOwner.text = repo.owner.login
            b.txtStars.text = repo.stargazers_count.toString()
            Glide.with(b.imgAvatar).load(repo.owner.avatar_url).into(b.imgAvatar)
            b.root.setOnClickListener { onClick(repo) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(list[position])
    override fun getItemCount(): Int = list.size
}
