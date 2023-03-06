package com.bindingmvvm.presentation.adapter

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bindingmvvm.databinding.ItemBinding
import com.bindingmvvm.domain.model.Container
import com.bindingmvvm.presentation.activity.DetailsActivity
import com.dogmvvm.utility.ImageUtil.getPlaceholder
import com.squareup.picasso.Picasso

class ContainerAdapter:  RecyclerView.Adapter<ContainerAdapter.ContainerViewHolder>(){

    private var containerList =  emptyList<Container>()

    fun setData(list: List<Container>){
        containerList = list
        notifyItemRangeInserted(0,containerList.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContainerViewHolder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContainerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContainerViewHolder, position: Int) {
        val itemList = containerList[position]
        holder.bind(itemList)
    }

    override fun getItemCount() = containerList.size

    class ContainerViewHolder(private val binding: ItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Container) {
            Picasso.get().load(item.thumbnailUrl).placeholder(getPlaceholder()).into(binding.ivImage)
            binding.data = item

            binding.root.setOnClickListener {
                binding.root.context.startActivity(
                    Intent(binding.root.context, DetailsActivity::class.java).apply {
                         putExtra("DETAILS",Bundle().apply {
                             putString("image", item.url)
                             putString("title", item.title)
                         })
                    }
                )

            }
        }
    }
}