package com.oheyadam.feature.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.oheyadam.feature.list.databinding.BreedItemBinding
import com.oheyadam.feature.list.model.BreedItem

private object BreedItemDiffCallback : DiffUtil.ItemCallback<BreedItem>() {
  override fun areItemsTheSame(oldItem: BreedItem, newItem: BreedItem): Boolean {
    return oldItem.id == newItem.id
  }

  override fun areContentsTheSame(oldItem: BreedItem, newItem: BreedItem): Boolean {
    return oldItem == newItem
  }
}

class BreedAdapter(
  private val itemClickListener: (BreedItem) -> Unit
) : ListAdapter<BreedItem, BreedAdapter.ViewHolder>(BreedItemDiffCallback) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val binding = BreedItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return ViewHolder(binding)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bind(getItem(position))
  }

  inner class ViewHolder(
    private val binding: BreedItemBinding
  ) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: BreedItem) {
      with(binding) {
        root.setOnClickListener {
          itemClickListener(item)
        }
        imageDog.load(item.thumbnailUrl) {
          crossfade(true)
          // Load placeholder if no thumbnail exists
          // placeholder(R.drawable.placeholder)
        }
        textDogName.text = item.name
      }
    }
  }
}
