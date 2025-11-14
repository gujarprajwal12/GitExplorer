package com.psg.gitexplorer.presentation.favorites.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.psg.gitexplorer.databinding.ItemRepoBinding
import com.psg.gitexplorer.data.local.FavoriteEntity

class FavoritesAdapter : RecyclerView.Adapter<FavoritesAdapter.VH>() {

    private val items = mutableListOf<FavoriteEntity>()

    fun submitList(list: List<FavoriteEntity>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    inner class VH(private val b: ItemRepoBinding) : RecyclerView.ViewHolder(b.root) {
        fun bind(item: FavoriteEntity) {
            b.txtName.text = item.name
            b.txtOwner.text = item.ownerLogin
            b.txtStars.text = item.stars.toString()
            Glide.with(b.imgAvatar).load(item.ownerAvatar).into(b.imgAvatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(items[position])
    override fun getItemCount(): Int = items.size
}
