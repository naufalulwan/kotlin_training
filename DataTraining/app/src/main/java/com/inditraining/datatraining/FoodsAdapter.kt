package com.inditraining.datatraining

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FoodsAdapter(private val foods: ArrayList<Foods>): RecyclerView.Adapter<FoodsAdapter.ViewHolder>() {

    private lateinit var  onItemClickCallBack: OnItemClickCallBack

    fun setOnItemClickCallBack(onItemClickCallback: OnItemClickCallBack) {
        this.onItemClickCallBack = onItemClickCallback
    }


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tv_food_name)
        var tvPrice: TextView = itemView.findViewById(R.id.tv_food_price)
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_food_photo)
    }

    interface OnItemClickCallBack {
        fun onItemClicked(data: Foods)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_food, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val food = foods[position]
        val aFood = foods[holder.adapterPosition]

        holder.tvName.text = food.name
        holder.tvPrice.text = food.price
        holder.imgPhoto.setImageResource(food.pic)

        holder.itemView.setOnClickListener{
            onItemClickCallBack.onItemClicked(aFood)
        }
    }

    override fun getItemCount(): Int = foods.size



}