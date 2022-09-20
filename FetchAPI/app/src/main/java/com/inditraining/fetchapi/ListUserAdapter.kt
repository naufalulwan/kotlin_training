package com.inditraining.fetchapi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.inditraining.fetchapi.databinding.UserCardItemBinding

class ListUserAdapter(private val usersData: List<DataItem>): RecyclerView.Adapter<ListUserAdapter.ViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ViewHolder(var binding: UserCardItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = UserCardItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userData = usersData[position]

        Glide.with(holder.binding.root)
            .load(userData.avatar)
            .into(holder.binding.ivUserPhoto)

        holder.binding.tvUserName.text = StringBuilder(userData.firstName).append(userData.lastName)
        holder.binding.root.setOnClickListener { onItemClickCallback.onItemClicked(usersData[holder.adapterPosition]) }
    }

    override fun getItemCount(): Int = usersData.size

    interface OnItemClickCallback {
        fun onItemClicked(userData: DataItem)
    }
}