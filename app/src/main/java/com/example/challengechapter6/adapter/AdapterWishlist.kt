package com.example.challengechapter6.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.challengechapter5.model.GetAllItemSell
import com.example.challengechapter6.databinding.DetailItemBinding
import com.example.challengechapter6.model.Wishlist

class AdapterWishlist(private val onItemClick: (Int) -> Unit): RecyclerView.Adapter<AdapterWishlist.ViewHolder>() {

    inner class ViewHolder(private val binding: DetailItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Wishlist){
            binding.apply {
                tvTitle.text = data.title
                tvCategory.text = data.category
                tvPrice.text = data.price.toString()
                Glide.with(binding.root).load(data.image).into(ivItem)
                root.setOnClickListener {
                    onItemClick(data.id)
                }
            }
        }
    }

    private val diffCallBack = object : DiffUtil.ItemCallback<Wishlist>() {

        override fun areItemsTheSame(oldItem: Wishlist, newItem: Wishlist): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Wishlist, newItem: Wishlist): Boolean = oldItem.hashCode() == newItem.hashCode()

    }

    private val differ = AsyncListDiffer(this, diffCallBack)

    fun itemsData(value: List<Wishlist>?) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(DetailItemBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = differ.currentList[position]
        data.let {
            holder.bind(data)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    interface OnClickListener {
        fun onClickItem(data: Wishlist)
    }

}