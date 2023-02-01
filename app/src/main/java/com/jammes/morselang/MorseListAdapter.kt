package com.jammes.morselang

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jammes.morselang.databinding.FragmentMorseListBinding
import com.jammes.morselang.databinding.MorseItemBinding

class MorseListAdapter: RecyclerView.Adapter<MorseListAdapter.ViewHolder>() {

    class ViewHolder(private val binding: MorseItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(morseItem: MorseItem){
            binding.textViewSavedTitle.text = morseItem.text
            binding.textViewSavedMorse.text = morseItem.morse
        }
    }

    fun updateMorseList(morse: List<MorseItem>) {
        asyncListDiffer.submitList(morse)
    }

    private val asyncListDiffer: AsyncListDiffer<MorseItem> = AsyncListDiffer(this, DiffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = MorseItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = asyncListDiffer.currentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(asyncListDiffer.currentList[position])
    }

    object DiffCallback : DiffUtil.ItemCallback<MorseItem>() {
        override fun areItemsTheSame(oldItem: MorseItem, newItem: MorseItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MorseItem, newItem: MorseItem): Boolean {
            return oldItem == newItem
        }
    }
}